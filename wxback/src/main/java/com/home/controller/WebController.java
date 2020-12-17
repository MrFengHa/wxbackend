package com.home.controller;

import com.home.config.WeiXinConfig;
import com.home.entity.WeiXinTokenAuth;
import com.home.entity.WeiXinUserInfo;
import com.home.entity.WeiXinUserInfoAuth;
import com.home.util.WeiXinUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 文件描述
 *
 * @Author 冯根源
 * @create 2020/12/17 13:57
 */
@Controller
public class WebController {
    @Autowired
    WeiXinConfig weiXinConfig;
    @Autowired
    WeiXinUtil weiXinUtil;

    @RequestMapping("aaa")
    public String aaa() {
        System.out.println("进来了aaaa");
        return "redirect:/getAuthorize";
    }

    @RequestMapping("auth_back")
    public String bbb(@RequestParam String code, @RequestParam String state, Model model) {
        WeiXinTokenAuth accessToken = weiXinUtil.getAccessTokenAuth(code);
        //拉去用户信息
        WeiXinUserInfoAuth userInfoAuth = weiXinUtil.getUserInfoAuth(accessToken.getAccess_token(), accessToken.getOpenid());
        System.out.println("userinfo.toString" + userInfoAuth.toString());
        WeiXinUserInfo userInfo = weiXinUtil.getUserInfo(userInfoAuth.getOpenid());
        model.addAttribute("userInfo", userInfoAuth);
        if (userInfo != null & userInfo.getSubscribe() == 1){
            return "bbb";
        }else{
            return "ccc";
        }

    }


    @RequestMapping("ccc")
    public String ccc() {

        return "aaa";
    }

    @GetMapping("getAuthorize")
    public String getAuthorize() {
        String redirectURL = "http://lszxydh.cn/auth_back";
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + weiXinConfig.getAppid()
                + "&redirect_uri=" + redirectURL
                + "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
        System.out.println(url);

        return "redirect:" + url;
    }
}
