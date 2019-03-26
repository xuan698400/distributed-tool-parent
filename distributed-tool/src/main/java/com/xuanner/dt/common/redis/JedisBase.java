package com.xuanner.dt.common.redis;

import com.xuanner.dt.Dt;
import com.xuanner.dt.common.DtCloseable;
import redis.clients.jedis.Jedis;

/**
 * Created by xuan on 2018/8/29.
 */
public abstract class JedisBase implements JedisFactoryAware, DtCloseable {

    /**
     * jedis生产工厂
     */
    private JedisFactory jedisFactory;
    /**
     * 一般一个组件实例子一个jedis连接
     */
    private Jedis        jedis;
    /**
     * 组件的key
     */
    private String       keyName;

    @Override
    public void setJedisFactory(JedisFactory jedisFactory) {
        this.jedisFactory = jedisFactory;
    }

    @Override
    public void close() {
        if (null != getJedis()) {
            getJedis().close();
        }
    }

    protected String getKeyName() {
        return keyName;
    }

    protected void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    protected Jedis getJedis() {
        initJedis();
        return jedis;
    }

    private void initJedis() {
        //如果是空，使用指定工厂初始化
        if (null == jedis) {
            if (null == jedisFactory) {
                this.jedis = Dt.getInstance().getDefaultJedisFactory().getJedis();
            } else {
                this.jedis = jedisFactory.getJedis();
            }
        }
    }

}
