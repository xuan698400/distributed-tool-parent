package com.xuanner.dt.lock;

import com.xuanner.dt.common.DtCloseable;

/**
 * Created by xuan on 2018/8/30.
 */
public interface DtLock extends DtCloseable {

    /**
     * 试图获取锁
     *
     * @return 成功后返回锁id，失败返回NULL
     */
    String tryLock();

    /**
     * 开锁
     *
     * @param kId tryLock返回的钥匙
     */
    void unLock(String kId);

}
