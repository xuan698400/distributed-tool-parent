package com.xuanner.dt.core.impl;

import com.xuanner.dt.common.redis.JedisBase;
import com.xuanner.dt.core.DtSortedSet;
import redis.clients.jedis.ZParams;

/**
 * Created by xuan on 2018/8/29.
 */
public class JedisDtSortedSet extends JedisBase implements DtSortedSet {

    public JedisDtSortedSet(String name) {
        setKeyName(name + ":DtSortedSet");
    }

    @Override
    public String getName() {
        return getKeyName();
    }

    @Override
    public void add(double score, String element) {
        getJedis().zadd(getKeyName(), score, element);
    }

    @Override
    public void removeByScoreRange(double scoreFrom, double scoreTo) {
        getJedis().zremrangeByScore(getKeyName(), scoreFrom, scoreTo);
    }

    @Override
    public void remove(String element) {
        getJedis().zrem(getKeyName(), element);
    }

    @Override
    public long rank(String element) {
        return getJedis().zrank(getKeyName(), element);
    }

    @Override
    public long size() {
        return getJedis().zcard(getKeyName());
    }

    @Override
    public void intersectFrom(DtSortedSet fromSet, DtSortedSet resultSet) {
        ZParams params = new ZParams();
        params.weightsByDouble(1D, 0D);
        getJedis().zinterstore(resultSet.getName(), params, this.getName(), fromSet.getName());
    }

    @Override
    public double getScore(String element) {
        return getJedis().zscore(getKeyName(), element);
    }

}
