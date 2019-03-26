package com.xuanner.dt.core.impl;

import com.xuanner.dt.common.UuidUtil;
import com.xuanner.dt.common.redis.JedisBase;
import com.xuanner.dt.core.DtSemaphore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by xuan on 2018/8/29.
 */
public class JedisDtSemaphore extends JedisBase implements DtSemaphore {

    private final static int DEFAULT_TIMEOUT_SECOND = 30;

    /**
     * 信号量允许值
     */
    private long   permits;
    /**
     * 用时间戳设置score（主要用来清理信号量超时未归还清理）
     */
    private String semaphoreSetName;
    /**
     * 信号量超过一定时间没有释放的，就自动释放，防止获取信号量后系统奔溃等原因没有释放信号量
     */
    private int    timeoutSecond;

    public JedisDtSemaphore(String name, long permits, int timeoutSecond) {
        this.permits = permits;
        this.semaphoreSetName = name + ":DtSemaphore";
        this.timeoutSecond = timeoutSecond;
    }

    public JedisDtSemaphore(String name, long permits) {
        this(name, permits, DEFAULT_TIMEOUT_SECOND);
    }

    @Override
    public String acquire() {
        //KEYS[1]=semaphoreSetName
        //ARGV[1]=endTime, ARGV[2]=now, ARGV[3]=sId, ARGV[4]=permits

        String success = "1";
        String zremrangeByScore = "redis.call('zremrangeByScore', KEYS[1], 0, tonumber(ARGV[1])) ";
        String zcard = "local count = redis.call('zcard', KEYS[1]) ";
        String zadd = "redis.call('zadd', KEYS[1], tonumber(ARGV[2]), ARGV[3]) ";

        //清理过期的信号量，并获取当前合法信号量
        String script = zremrangeByScore + zcard;
        //判断信号量是否超限，如果是返回0，不是就新增并返回1
        script += "if count < tonumber(ARGV[4]) then " + zadd + "return " + success + " ";
        script += "else return 0 end";

        List<String> keyList = Collections.singletonList(semaphoreSetName);

        String sId = UuidUtil.uuid();
        long now = System.currentTimeMillis();
        long endTime = now - timeoutSecond * 1000;
        List<String> argvList = new ArrayList<>();
        argvList.add(String.valueOf(endTime));
        argvList.add(String.valueOf(now));
        argvList.add(sId);
        argvList.add(String.valueOf(permits));

        Object result = getJedis().eval(script, keyList, argvList);
        return success.equals(result.toString()) ? sId : null;
    }

    @Override
    public void release(String sId) {
        getJedis().zrem(semaphoreSetName, sId);
    }

}
