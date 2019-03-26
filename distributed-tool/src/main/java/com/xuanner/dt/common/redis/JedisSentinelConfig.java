package com.xuanner.dt.common.redis;

import com.xuanner.dt.common.DtConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by xuan on 2018/8/27.
 */
public class JedisSentinelConfig implements DtConfig {

    /**
     * 哨兵地址
     */
    private Set<String> sentineSet = new HashSet<>();
    /**
     * master名称
     */
    private String masterName;

    private String auth;

    public Set<String> getSentineSet() {
        return sentineSet;
    }

    public void setSentineSet(Set<String> sentineSet) {
        this.sentineSet = sentineSet;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }
}
