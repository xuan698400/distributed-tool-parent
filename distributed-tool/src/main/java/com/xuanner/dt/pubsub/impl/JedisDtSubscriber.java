package com.xuanner.dt.pubsub.impl;

import com.xuanner.dt.common.redis.JedisBase;
import com.xuanner.dt.pubsub.DtMessageListener;
import com.xuanner.dt.pubsub.DtSubscriber;
import redis.clients.jedis.JedisPubSub;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by xuan on 2018/8/16.
 */
public class JedisDtSubscriber extends JedisBase implements DtSubscriber {

    //订阅的消息监听
    private Map<String, List<DtMessageListener>> listenerMap = new ConcurrentHashMap<>();

    @Override
    public void addListener(DtMessageListener listener) {
        List<DtMessageListener> listenerList = listenerMap.putIfAbsent(listener.topic(), new ArrayList<>());
        if (null == listenerList) {
            listenerList = listenerMap.get(listener.topic());
        }
        listenerList.add(listener);
    }

    @Override
    public void init() {
        Set<String> topicSet = listenerMap.keySet();
        String[] topics = topicSet.toArray(new String[topicSet.size()]);
        new Thread(() -> getJedis().subscribe(new JedisPubSub() {

            @Override
            public void onMessage(String channel, String message) {
                List<DtMessageListener> lList = listenerMap.get(channel);
                if (null != lList) {
                    lList.forEach(l -> l.onMessage(message));
                }
            }
        }, topics)).start();
    }

}
