package com.xuanner.dt.core.impl;

import com.xuanner.dt.common.redis.JedisBase;
import com.xuanner.dt.core.DtLong;

/**
 * Created by xuan on 2018/8/29.
 */
public class JedisDtLong extends JedisBase implements DtLong {

    public JedisDtLong(String name, long initValue) {
        setKeyName(name + ":DtLong");
        getJedis().setnx(getKeyName(), String.valueOf(initValue));
    }

    public JedisDtLong(String name) {
        this(name, 0L);
    }

    @Override
    public long incr(long l) {
        return getJedis().incrBy(getKeyName(), l);
    }

    @Override
    public long decr(long l) {
        return getJedis().decrBy(getKeyName(), l);
    }

    @Override
    public long getValue() {
        return Long.valueOf(getJedis().get(getKeyName()));
    }

    @Override
    public void setValue(long l) {
        getJedis().set(getKeyName(), String.valueOf(l));
    }

}
