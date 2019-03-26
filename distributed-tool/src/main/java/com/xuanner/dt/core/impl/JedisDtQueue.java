package com.xuanner.dt.core.impl;

import com.xuanner.dt.Dt;
import com.xuanner.dt.common.redis.JedisBase;
import com.xuanner.dt.core.DtList;
import com.xuanner.dt.core.DtQueue;

/**
 * Created by xuan on 2018/8/25.
 */
public class JedisDtQueue extends JedisBase implements DtQueue {

    private final static long LENGTH_NO_LIMIT = -1L;

    /**
     * 队列最大长度
     */
    private long   maxSize;
    /**
     * 使用DtList来实现Queue
     */
    private DtList list;

    public JedisDtQueue(String name, long maxSize) {
        this.maxSize = maxSize;
        list = Dt.newDtList(name);
    }

    public JedisDtQueue(String name) {
        this(name, LENGTH_NO_LIMIT);
    }

    @Override
    public boolean push(String... elements) {
        //如果用户限制了队列的长度，在push之前check队列是否满了
        if (LENGTH_NO_LIMIT != this.maxSize && (list.size() + elements.length) > this.maxSize) {
            return false;
        }
        list.pushLeft(elements);
        return true;
    }

    @Override
    public String pop() {
        return list.popRight();
    }

    @Override
    public long size() {
        return list.size();
    }

}
