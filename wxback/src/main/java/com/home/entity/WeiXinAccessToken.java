package com.home.entity;

/**
 * 文件描述
 * 微信AccessToken
 * @author 冯根源
 * @date 2020/12/17 0:51
 */
public class WeiXinAccessToken {
    private String access_token;
    private Integer expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Integer getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Integer expires_in) {
        this.expires_in = expires_in;
    }
}
