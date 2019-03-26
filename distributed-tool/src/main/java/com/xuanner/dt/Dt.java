package com.xuanner.dt;

import com.xuanner.dt.cache.DtCacheClient;
import com.xuanner.dt.cache.impl.JedisDtCacheClient;
import com.xuanner.dt.common.redis.JedisDirectConfig;
import com.xuanner.dt.common.redis.JedisDirectFactory;
import com.xuanner.dt.common.redis.JedisFactory;
import com.xuanner.dt.common.redis.JedisSentinelConfig;
import com.xuanner.dt.common.redis.JedisSentinelFactory;
import com.xuanner.dt.core.DtList;
import com.xuanner.dt.core.DtLong;
import com.xuanner.dt.core.DtQueue;
import com.xuanner.dt.core.DtSemaphore;
import com.xuanner.dt.core.DtSortedSet;
import com.xuanner.dt.core.impl.JedisDtList;
import com.xuanner.dt.core.impl.JedisDtLong;
import com.xuanner.dt.core.impl.JedisDtQueue;
import com.xuanner.dt.core.impl.JedisDtSemaphore;
import com.xuanner.dt.core.impl.JedisDtSortedSet;
import com.xuanner.dt.lock.DtLock;
import com.xuanner.dt.lock.impl.JedisDtLock;
import com.xuanner.dt.pubsub.DtPublisher;
import com.xuanner.dt.pubsub.DtSubscriber;
import com.xuanner.dt.pubsub.impl.JedisDtPublisher;
import com.xuanner.dt.pubsub.impl.JedisDtSubscriber;
import com.xuanner.dt.sequence.DtSequence;
import com.xuanner.dt.sequence.impl.JedisDtSequence;

import java.util.Set;

/**
 * 装一些全局初始化的对象，单例
 * Created by xuan on 2018/8/27.
 */
public class Dt {

    private final static Dt instance = new Dt();

    public static Dt getInstance() {
        return instance;
    }

    private JedisFactory defaultJedisFactory;

    public void initJedis(String host, int port, String auth) {
        JedisDirectConfig config = new JedisDirectConfig();
        config.setHost(host);
        config.setPort(port);
        config.setAuth(auth);
        //
        JedisDirectFactory jedisDirectFactory = new JedisDirectFactory();
        jedisDirectFactory.setConfig(config);
        jedisDirectFactory.init();
        this.defaultJedisFactory = jedisDirectFactory;
    }

    public void initJedisSentinel(String masterName, Set<String> sentinelSet, String auth) {
        JedisSentinelConfig config = new JedisSentinelConfig();
        config.setMasterName(masterName);
        config.setSentineSet(sentinelSet);
        config.setAuth(auth);
        //
        JedisSentinelFactory jedisSentinelFactory = new JedisSentinelFactory();
        jedisSentinelFactory.setConfig(config);
        jedisSentinelFactory.init();
        this.defaultJedisFactory = jedisSentinelFactory;
    }

    public JedisFactory getDefaultJedisFactory() {
        return defaultJedisFactory;
    }

    public void setDefaultJedisFactory(JedisDirectFactory defaultJedisFactory) {
        this.defaultJedisFactory = defaultJedisFactory;
    }

    //========== core
    public static DtList newDtList(String name) {
        return new JedisDtList(name);
    }

    public static DtLong newDtLong(String name, long initValue) {
        return new JedisDtLong(name, initValue);
    }

    public static DtLong newDtLong(String name) {
        return new JedisDtLong(name);
    }

    public static DtQueue newDtQueue(String name, long maxSize) {
        return new JedisDtQueue(name, maxSize);
    }

    public static DtQueue newDtQueue(String name) {
        return new JedisDtQueue(name);
    }

    public static DtSortedSet newDtSortedSet(String name) {
        return new JedisDtSortedSet(name);
    }

    public static DtSemaphore newDtSemaphore(String name, long permits) {
        return new JedisDtSemaphore(name, permits);
    }

    public static DtSemaphore newDtSemaphore(String name, long permits, int timeoutSecond) {
        return new JedisDtSemaphore(name, permits, timeoutSecond);
    }

    //==========cache

    public static DtCacheClient newDtCacheClient() {
        return new JedisDtCacheClient();
    }

    //==========sequence

    public static DtSequence newDtSequence(int step, long stepStart) {
        JedisDtSequence jedisDtSequence = new JedisDtSequence();
        jedisDtSequence.setStep(step);
        jedisDtSequence.setStepStart(stepStart);
        return jedisDtSequence;
    }

    public static DtSequence newDtSequence() {
        return new JedisDtSequence();
    }

    //==========pubsub

    public static DtPublisher newDtPublisher() {
        return new JedisDtPublisher();
    }

    public static DtSubscriber newDtSubscriber() {
        return new JedisDtSubscriber();
    }

    //=========lock
    public static DtLock newDtLock(String name) {
        return new JedisDtLock(name);
    }

    public static DtLock newDtLock(String name, int ownTimeoutSecond) {
        JedisDtLock dtLock = new JedisDtLock(name);
        dtLock.setOwnTimeoutSecond(ownTimeoutSecond);
        return dtLock;
    }

}
