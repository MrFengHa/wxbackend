package com.home.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 文件描述
 *
 * @author 冯根源
 * @date 2020/12/16 22:27
 */
@Component
@ConfigurationProperties(prefix = "weixin")
public class WeiXinConfig {
    private String appid;
    private String appsecret;
    private String token;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
