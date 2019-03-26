package com.xuanner.dt.common.redis;

/**
 * 支持JedisFactory工厂的设置接口能力
 * Created by xuan on 2018/8/29.
 */
public interface JedisFactoryAware {

    void setJedisFactory(JedisFactory jedisFactory);
}
