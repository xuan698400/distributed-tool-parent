package com.xuanner.dt.lock.impl;

import com.xuanner.dt.common.UuidUtil;
import com.xuanner.dt.common.redis.JedisBase;
import com.xuanner.dt.lock.DtLock;

import java.util.Collections;

/**
 * Created by xuan on 2018/8/30.
 */
public class JedisDtLock extends JedisBase implements DtLock {

    private static final String LOCK_SUCCESS         = "OK";
    private static final String SET_IF_NOT_EXIST     = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    private static final int    DEFAULT_OWN_TIMEOUT  = 30;

    /**
     * 锁失效超时时间，单位：毫秒
     */
    private int ownTimeoutSecond = DEFAULT_OWN_TIMEOUT;

    public JedisDtLock(String name) {
        setKeyName(name + ":DtLock");
    }

    @Override
    public String tryLock() {
        String kId = UuidUtil.uuid();
        String result = getJedis().set(getKeyName(), kId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME,
                                       ownTimeoutSecond * 1000);
        return LOCK_SUCCESS.equals(result) ? kId : null;
    }

    @Override
    public void unLock(String kId) {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return "
                        + "0 end";
        getJedis().eval(script, Collections.singletonList(getKeyName()), Collections.singletonList(kId));
    }

    public int getOwnTimeoutSecond() {
        return ownTimeoutSecond;
    }

    public void setOwnTimeoutSecond(int ownTimeoutSecond) {
        this.ownTimeoutSecond = ownTimeoutSecond;
    }
}
