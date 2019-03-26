package com.xuanner.dt.cache.impl;

import com.xuanner.dt.cache.DtCacheClient;
import com.xuanner.dt.common.MapUtil;
import com.xuanner.dt.common.redis.JedisBase;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import java.util.Collection;
import java.util.Map;

/**
 * Created by xuan on 2018/8/12.
 */
public class JedisDtCacheClient extends JedisBase implements DtCacheClient {

    @Override
    public String get(String key) {
        return getJedis().get(key);
    }

    @Override
    public Map<String, String> getBulk(Collection<String> keys) {
        Pipeline pip = getJedis().pipelined();

        Map<String, Response<String>> key2ResponseMap = MapUtil.newHashMap(keys.size());
        keys.forEach(key -> key2ResponseMap.put(key, pip.get(key)));
        pip.sync();

        Map<String, String> key2ValueMap = MapUtil.newHashMap(keys.size());
        key2ResponseMap.forEach((key, response) -> key2ValueMap.put(key, response.get()));
        return key2ValueMap;
    }

    @Override
    public void set(String key, String value, int seconds) {
        getJedis().setex(key, seconds, value);
    }

    @Override
    public void setBulk(Map<String, String> map, int seconds) {
        Pipeline pip = getJedis().pipelined();
        map.forEach((key, value) -> pip.setex(key, seconds, value));
        pip.sync();
    }

    @Override
    public void del(String key) {
        getJedis().del(key);
    }

    @Override
    public void delBulk(Collection<String> keys) {
        Pipeline pip = getJedis().pipelined();
        keys.forEach(pip::del);
        pip.sync();
    }

}
