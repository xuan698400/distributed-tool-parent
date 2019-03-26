package com.xuanner.dt.common.redis;

import com.xuanner.dt.common.DtConfig;

/**
 * Created by xuan on 2018/8/27.
 */
public class JedisDirectConfig implements DtConfig {

    /**
     * 地址
     */
    private String host;
    /**
     * 端口
     */
    private int    port;
    /**
     * 权限，可空，取决于你的redis服务器有没有配置auth
     */
    private String auth;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

}
