package com.xuanner.dt.core.impl;

import com.xuanner.dt.common.redis.JedisBase;
import com.xuanner.dt.core.DtList;

/**
 * 分布式列表
 * Created by xuan on 2018/8/29.
 */
public class JedisDtList extends JedisBase implements DtList {

    public JedisDtList(String name) {
        setKeyName(name + ":DtList");
    }

    @Override
    public void pushLeft(String... elements) {
        getJedis().lpush(getKeyName(), elements);
    }

    @Override
    public void pushRight(String... elements) {
        getJedis().rpush(getKeyName(), elements);
    }

    @Override
    public String popLeft() {
        return getJedis().lpop(getKeyName());
    }

    @Override
    public String popRight() {
        return getJedis().rpop(getKeyName());
    }

    @Override
    public long size() {
        return getJedis().llen(getKeyName());
    }

}
