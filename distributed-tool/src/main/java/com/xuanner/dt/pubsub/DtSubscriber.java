package com.xuanner.dt.pubsub;

import com.xuanner.dt.common.DtCloseable;

/**
 * 消息订阅者
 * Created by xuan on 2018/8/16.
 */
public interface DtSubscriber extends DtCloseable {

    /**
     * 新增消息监听
     *
     * @param listener 监听
     */
    void addListener(DtMessageListener listener);

    /**
     * 订阅初始化
     */
    void init();
}