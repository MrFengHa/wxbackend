package com.home.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.home.config.WeiXinConfig;
import com.home.entity.WeiXinAccessToken;
import com.home.entity.WeiXinTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 文件描述
 *
 * @author 冯根源
 * @date 2020/12/17 0:44
 */
@Component
public class WeiXinUtil {
    @Autowired
    WeiXinConfig weiXinConfig;

    public String getAccessToken() {
        String response = HttpUtil.get("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + weiXinConfig.getAppid() + "&secret=" + weiXinConfig.getAppsecret());
        WeiXinAccessToken token = JSONObject.parseObject(response, WeiXinAccessToken.class);

        System.out.println(token.getAccess_token() + "......................" + token.getExpires_in());
        if (token.getAccess_token() == null) {
            return null;
        }
        return token.getAccess_token();
    }

    /**
     * 发送模板消息
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
}
