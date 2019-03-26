package com.xuanner.dt.pubsub;

import com.xuanner.dt.common.DtCloseable;

/**
 * 消息发布者
 * Created by xuan on 2018/8/16.
 */
public interface DtPublisher extends DtCloseable {

    /**
     * 发布消息
     *
     * @param topic   消息主题
     * @param message 消息内容
     */
    void publish(String topic, String message);
}
