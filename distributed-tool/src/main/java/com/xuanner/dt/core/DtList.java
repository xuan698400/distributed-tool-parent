package com.xuanner.dt.core;

import com.xuanner.dt.common.DtCloseable;

/**
 * 分布式list
 * Created by xuan on 2018/8/29.
 */
public interface DtList extends DtCloseable {

    /**
     * 左边插入元素
     *
     * @param elements 元素
     */
    void pushLeft(String... elements);

    /**
     * 右边插入元素
     *
     * @param elements 元素
     */
    void pushRight(String... elements);

    /**
     * 左边弹出元素
     *
     * @return 元素
     */
    String popLeft();

    /**
     * 右边弹出元素
     *
     * @return 元素
     */
    String popRight();

    /**
     * 列表长度
     *
     * @return 元素个数
     */
    long size();
}
