package com.xuanner.dt.core;

import com.xuanner.dt.common.DtCloseable;

/**
 * 分布式信号量
 * Created by xuan on 2018/8/29.
 */
public interface DtSemaphore extends DtCloseable {

    /**
     * 获取信号量id，这个id后面释放时需要用，如果获取失败，返回NULL
     *
     * @return 信号量id，
     */
    String acquire();

    /**
     * 释放
     *
     * @param sId 信号量id
     */
    void release(String sId);

}
