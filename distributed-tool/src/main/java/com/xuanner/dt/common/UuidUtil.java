package com.xuanner.dt.common;

import java.util.UUID;

/**
 * Created by xuan on 2018/8/30.
 */
public abstract class UuidUtil {

    public static String uuid() {
        return UUID.randomUUID().toString();
    }
}
