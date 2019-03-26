package com.xuanner.dt.core;

import com.xuanner.dt.common.DtCloseable;

/**
 * 分布式队列,用来实现生产者消费者
 * Created by xuan on 2018/8/25.
 */
public interface DtQueue extends DtCloseable {

    /**
     * 批量在队尾添加元素，如果设置了队列的长度，当队列满时，返回false
     *
     * @param elements 元素
     * @return true/false
     */
    boolean push(String... elements);

    /**
     * 队头弹出一个元素，如果队列是空，返回NULL
     *
     * @return 队头元素
     */
    String pop();

    /**
     * 队列长度
     *
     * @return long
     */
    long size();
}
