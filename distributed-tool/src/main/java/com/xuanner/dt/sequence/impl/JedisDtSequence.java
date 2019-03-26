package com.xuanner.dt.sequence.impl;

import com.xuanner.dt.common.redis.JedisBase;
import com.xuanner.dt.sequence.DtSequence;
import com.xuanner.dt.sequence.DtSequenceException;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xuan on 2018/8/12.
 */
public class JedisDtSequence extends JedisBase implements DtSequence {

    private final static int DEFAULT_STEP       = 1000;//默认区间跨度
    private final static int DEFAULT_STEP_START = 0;//序列号起始位置

    /**
     * 获取区间是加一把独占锁防止资源冲突
     */
    private final Lock lock = new ReentrantLock();

    /**
     * 区间步长
     */
    private int  step      = DEFAULT_STEP;
    /**
     * 区间起始位置，真实从stepStart+1开始
     */
    private long stepStart = DEFAULT_STEP_START;
    /**
     * 标记业务key是否存在，如果false，在取nextRange时，会取check一把
     * 这个boolean只为提高性能，不用每次都取redis check
     */
    private volatile boolean keyAlreadyExist;

    /**
     * 当前序列号区间
     */
    private volatile SequenceRange currentRange;

    @Override
    public long nextId(String name) throws DtSequenceException {
        String realName = getRealName(name);

        //当前区间不存在，重新获取一个区间
        if (null == currentRange) {
            lock.lock();
            try {
                if (null == currentRange) {
                    currentRange = nextRange(realName);
                }
            } finally {
                lock.unlock();
            }
        }

        //当value值为-1时，表明区间的序列号已经分配完，需要重新获取区间
        long value = currentRange.getAndIncrement();
        if (value == -1) {
            lock.lock();
            try {
                for (; ; ) {
                    if (currentRange.isOver()) {
                        currentRange = nextRange(realName);
                    }

                    value = currentRange.getAndIncrement();
                    if (value == -1) {
                        continue;
                    }

                    break;
                }
            } finally {
                lock.unlock();
            }
        }

        if (value < 0) {
            throw new DtSequenceException("Sequence value overflow, value = " + value);
        }

        return value;
    }

    private SequenceRange nextRange(String name) {
        if (!keyAlreadyExist) {
            Boolean isExists = getJedis().exists(name);
            if (!isExists) {
                //第一次不存在，进行初始化,setnx不存在就set，存在就忽略
                getJedis().setnx(name, String.valueOf(stepStart));
            }
            keyAlreadyExist = true;
        }

        Long max = getJedis().incrBy(name, step);
        Long min = max - step + 1;
        return new SequenceRange(min, max);
    }

    public void setStep(int step) {
        this.step = step;
    }

    public void setStepStart(long stepStart) {
        this.stepStart = stepStart;
    }

    private String getRealName(String name) {
        return name + ":DtSequence";
    }

}
