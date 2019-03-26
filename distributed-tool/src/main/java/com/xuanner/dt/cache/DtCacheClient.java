package com.xuanner.dt.cache;

import com.xuanner.dt.common.DtCloseable;

import java.util.Collection;
import java.util.Map;

/**
 * 一个简单的分布式缓存客户端，为了API简洁，目前暂时只支持值为String，如果要缓存对象，请自行使用JSON序列化到String
 * Created by xuan on 2018/8/12.
 */
public interface DtCacheClient extends DtCloseable {

    /**
     * 单个获取缓存
     *
     * @param key key
     * @return value
     */
    String get(String key);

    /**
     * 批量获取缓存
     *
     * @param keys keys
     * @return KV Map
     */
    Map<String, String> getBulk(Collection<String> keys);

    /**
     * 单个设置KEY
     *
     * @param key     key
     * @param value   value
     * @param seconds 过期时间，单位：秒
     */
    void set(String key, String value, int seconds);

    /**
     * 批量设置缓存
     *
     * @param map     KV Map
     * @param seconds 过期时间，单位：秒
     */
    void setBulk(Map<String, String> map, int seconds);

    /**
     * 单个删除缓存
     *
     * @param key key
     */
    void del(String key);

    /**
     * 批量删除缓存
     *
     * @param keys keys
     */
    void delBulk(Collection<String> keys);
}
