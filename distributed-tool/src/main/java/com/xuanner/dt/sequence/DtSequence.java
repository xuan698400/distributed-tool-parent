package com.xuanner.dt.sequence;

import com.xuanner.dt.common.DtCloseable;

/**
 * 序列号组件
 * Created by xuan on 2018/8/12.
 */
public interface DtSequence extends DtCloseable {

    /**
     * 获取下一个序列号
     *
     * @param name 业务名称，同一个业务名称的序列号不会重复
     * @return Long
     */
    long nextId(String name);
}
