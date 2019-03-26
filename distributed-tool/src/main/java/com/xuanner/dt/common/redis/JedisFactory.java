package com.xuanner.dt.common.redis;

import redis.clients.jedis.Jedis;

/**
 * Jedis工厂接口
 * Created by xuan on 2018/8/29.
 */
public interface JedisFactory {

    void init();

    Jedis getJedis();

    void destroy();
}
