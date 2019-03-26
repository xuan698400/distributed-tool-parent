package com.xuanner.dt.pubsub;

/**
 * 消息监听
 * Created by xuan on 2018/8/16.
 */
public interface DtMessageListener {

    /**
     * 消息对应的主题
     */
    String topic();

    /**
     * 收到消息回调
     *
     * @param message 消息内容
     */
    void onMessage(String message);
}
