package com.xuanner.dt.common.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

/**
 * Jedis工厂接口实现
 * Created by xuan on 2018/8/29.
 */
public class JedisSentinelFactory implements JedisFactory {

    private JedisSentinelConfig config;
    private JedisSentinelPool   jedisSentinelPool;

    @Override
    public Jedis getJedis() {
        Jedis jedis = jedisSentinelPool.getResource();
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

        jedisSentinelPool = new JedisSentinelPool(config.getMasterName(), config.getSentineSet(), jedisPoolConfig,
                                                  config.getAuth());
    }

    @Override
    public void destroy() {
        if (null != jedisSentinelPool) {
            jedisSentinelPool.destroy();
        }
    }

    public JedisSentinelConfig getConfig() {
        return config;
    }

    public void setConfig(JedisSentinelConfig config) {
        this.config = config;
    }

}
