package com.xuanner.dt.core;

import com.xuanner.dt.common.DtCloseable;

/**
 * 分布式Long类型
 * Created by xuan on 2018/8/29.
 */
public interface DtLong extends DtCloseable {

    /**
     * Long类型原子增长
     *
     * @param l 增长幅度
     * @return 增长后的值
     */
    long incr(long l);

    /**
     * Long类型原子减
     *
     * @param l 减幅度
     * @return 减后的值
     */
    long decr(long l);

    /**
     * 获取当前值
     *
     * @return long
     */
    long getValue();

    /**
     * 设置值
     *
     * @param l 值
     */
    void setValue(long l);

}
