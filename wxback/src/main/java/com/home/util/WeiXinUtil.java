package com.home.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.home.config.WeiXinConfig;
import com.home.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * 文件描述
 *
 * @author 冯根源
 * @date 2020/12/17 0:44
 */
@Component
public class WeiXinUtil {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    WeiXinConfig weiXinConfig;

    /**
     * 获取微信普通接口的AccessToken
     *
     * @return
     */
    public String getAccessToken() {
        String accessToken = (String) redisTemplate.opsForValue().get("access_token");
        if (StringUtils.isEmpty(accessToken)) {
            String response = HttpUtil.get("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + weiXinConfig.getAppid() + "&secret=" + weiXinConfig.getAppsecret());
            WeiXinAccessToken token = JSONObject.parseObject(response, WeiXinAccessToken.class);

            System.out.println(token.getAccess_token() + "......................" + token.getExpires_in());
            if (token.getAccess_token() == null) {
                return null;
            }
            System.out.println("内部没有");
            redisTemplate.opsForValue().set("access_token", token.getAccess_token(), token.getExpires_in() - 5, TimeUnit.SECONDS);
            return token.getAccess_token();
        } else {
            System.out.println("有");
            return accessToken;
        }

    }


    /**
     * 获取网页收取的AccessToken
     *
     * @return
     */
    public WeiXinTokenAuth getAccessTokenAuth(String code) {
        String accessTokenJson = (String) redisTemplate.opsForValue().get("access_token_auth");
        if (StringUtils.isEmpty(accessTokenJson)) {
            System.out.println(code);
            String response = HttpUtil.get("https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + weiXinConfig.getAppid()
                    + "&secret=" + weiXinConfig.getAppsecret()
                    + "&code=" + code
                    + "&grant_type=authorization_code");
            System.out.println(response);
            WeiXinTokenAuth token = JSONObject.parseObject(response, WeiXinTokenAuth.class);

            System.out.println(token.getAccess_token() + "......................" + token.getExpires_in());
            if (token.getAccess_token() == null) {
                return null;
            }
            System.out.println("内部没有"+token.getAccess_token());
            redisTemplate.opsForValue().set("access_token_auth", JSON.toJSONString(token), token.getExpires_in() - 5, TimeUnit.SECONDS);
            return token;
        } else {
            System.out.println("有");
            return JSONObject.parseObject(accessTokenJson, WeiXinTokenAuth.class);
        }

    }

    /**
     * 发送模板消息
     *
     * @param toUser
     * @param templateId
     * @param url
     * @param data
     * @return
     */
    public boolean sendTemplateMessage(String toUser, String templateId, String url, Object data) {
        WeiXinTemplateMessage templateMessage = new WeiXinTemplateMessage();
        templateMessage.setTouser(toUser);
        templateMessage.setTemplate_id(templateId);
        templateMessage.setUrl(url);
        templateMessage.setData(data);

        String response = HttpUtil.post("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + getAccessToken(), JSON.toJSONString(templateMessage));

        JSONObject jsonObject = JSONObject.parseObject(response);
        return jsonObject.getInteger("errcode") == 0;

    }

    /**
     * 创建菜单
     *
     * @param menu
     * @return
     */
    public boolean createMenu(WeiXinMenu menu) {
        String postData = JSON.toJSONString(menu);
        System.out.println(postData);
        String response = HttpUtil.post("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + getAccessToken(), postData);
        JSONObject jsonObject = JSONObject.parseObject(response);
        System.out.println(response);
        return jsonObject.getInteger("errcode") == 0;
    }

    public WeiXinUserInfoAuth getUSerInfoAuth(String accessToken, String openId) {
        String response = HttpUtil.get("https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken + "&openid=" + openId + "&lang=zh_CN");

        WeiXinUserInfoAuth userInfoAuth = JSONObject.parseObject(response, WeiXinUserInfoAuth.class);
        if (userInfoAuth.getOpenid() == null) {
            System.out.println("没有获取到");
            return null;
        }
        return userInfoAuth;
    }
}
