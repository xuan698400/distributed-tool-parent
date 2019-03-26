package com.xuanner.dt.pubsub.impl;

import com.xuanner.dt.common.redis.JedisBase;
import com.xuanner.dt.pubsub.DtPublisher;

/**
 * Created by xuan on 2018/8/16.
 */
public class JedisDtPublisher extends JedisBase implements DtPublisher {

    @Override
    public void publish(String topic, String message) {
        getJedis().publish(topic, message);
    }

}
