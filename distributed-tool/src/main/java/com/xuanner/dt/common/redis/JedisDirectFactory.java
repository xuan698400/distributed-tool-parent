package com.xuanner.dt.common.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 直接连Master
 * Created by xuan on 2018/8/29.
 */
public class JedisDirectFactory implements JedisFactory {

    private JedisDirectConfig config;
    private JedisPool         jedisPool;

    @Override
    public Jedis getJedis() {
        Jedis jedis = jedisPool.getResource();
        if (null != config.getAuth()) {
            jedis.auth(config.getAuth());
        }
        return jedis;
    }

    @Override
    public void init() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(500);
        jedisPoolConfig.setMaxIdle(5);
        jedisPoolConfig.setMaxWaitMillis(1000 * 10);
        jedisPool = new JedisPool(jedisPoolConfig, config.getHost(), config.getPort(), 10000);
    }

    @Override
    public void destroy() {
        if (null != jedisPool) {
            jedisPool.destroy();
        }
    }

    public JedisDirectConfig getConfig() {
        return config;
    }

    public void setConfig(JedisDirectConfig config) {
        this.config = config;
    }

}
