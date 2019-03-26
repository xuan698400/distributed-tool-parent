package com.xuanner.dt.sequence.impl;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 序列号区间（为了提高生成性能，每次获取一个区间，然后在内存里面分配）
 * Created by xuan on 2018/1/10.
 */
class SequenceRange {

    /**
     * 区间的序列号开始值
     */
    private final long       min;
    /**
     * 区间的序列号结束值
     */
    private final long       max;
    /**
     * 区间的序列号当前值
     */
    private final AtomicLong value;
    /**
     * 区间的序列号是否分配完毕，每次分配完毕就会去重新获取一个新的区间
     */
    private volatile boolean over = false;

    SequenceRange(long min, long max) {
        this.min = min;
        this.max = max;
        this.value = new AtomicLong(min);
    }

    /**
     * 返回并递增下一个序列号
     *
     * @return 下一个序列号，如果返回-1表示序列号分配完毕
     */
    long getAndIncrement() {
        long currentValue = value.getAndIncrement();
        if (currentValue > max) {
            over = true;
            return -1;
        }

        return currentValue;
    }

    boolean isOver() {
        return over;
    }

    @Override
    public String toString() {
        return "max: " + max + ", min: " + min + ", value: " + value;
    }

}
