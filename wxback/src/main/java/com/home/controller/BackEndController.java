package com.home.controller;

import com.home.config.WeiXinConfig;
import com.home.entity.*;
import com.home.util.AesException;
import com.home.util.SHA1;
import com.thoughtworks.xstream.XStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 文件描述
 *
 * @author 冯根源
 * @date 2020/12/16 22:00
 */
@RestController
@RequestMapping("wx")
public class BackEndController {
    @Autowired
    WeiXinConfig weiXinConfig;

    @GetMapping("test")
    public String test() {
        return "你好";
    }

    @GetMapping("connecotr")
    public String test(@RequestParam String signature, @RequestParam String timestamp, @RequestParam String nonce, @RequestParam String echostr) {
        try {
            String msgSignature = SHA1.getSHA1(weiXinConfig.getToken(), timestamp, nonce, "");
            System.out.println("进入");
            if (msgSignature.equals(signature)) {
                System.out.println("成功");
                return echostr;
            } else {
                System.out.println("失败");
            }
        } catch (AesException e) {
            e.printStackTrace();
        }
        return "你好失败了";

    }

    @PostMapping("connecotr")
    public String getNotify(HttpServletRequest request) {
        XStream xStream = new XStream();
        xStream.aliasType("xml", WeiXinNotify.class);

        try {

            WeiXinNotify notify = (WeiXinNotify) xStream.fromXML(request.getInputStream());
            System.out.println(notify.toString());
            if (notify.getMsgType().equals("text")) {
                //return createTextMessage(notify);

                //return createNewMessage(notify);
            } else if (notify.getMsgType().equals("image")) {
                return createImageMessage(notify);
            }else if (notify.getMsgType().equals("event")&&"subscribe".equals(notify.getEvent())) {
                return createNewMessage(notify);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    private String createImageMessage(WeiXinNotify notify) {
        WeiXinImageMessage imageMessage = new WeiXinImageMessage();
        imageMessage.setToUserName(notify.getFromUserName());
        imageMessage.setFromUserName(notify.getToUserName());
        imageMessage.setMsgType("image");
        imageMessage.setCreateTime(String.valueOf(System.currentTimeMillis() / 1000));
        WeiXinImageMessageItem imageMessageItem = new WeiXinImageMessageItem();
        imageMessageItem.setMediaId(notify.getMediaId());
        imageMessage.setImage(imageMessageItem);
        XStream xStream = new XStream();
        xStream.aliasType("xml", WeiXinImageMessage.class);

        return xStream.toXML(imageMessage);
    }

    private String createTextMessage(WeiXinNotify notify) {
        WeiXinTextMessage textMessage = new WeiXinTextMessage();
        textMessage.setToUserName(notify.getFromUserName());
        textMessage.setFromUserName(notify.getToUserName());
        textMessage.setMsgType("text");
        textMessage.setCreateTime(String.valueOf(System.currentTimeMillis() / 1000));
        textMessage.setContent("公众号建设中");

        XStream xStream = new XStream();
        xStream.aliasType("xml", WeiXinTextMessage.class);

        return xStream.toXML(textMessage);
    }

    private String createNewMessage(WeiXinNotify notify) {
        WeiXinNewsMessage newsMessage = new WeiXinNewsMessage();
        newsMessage.setToUserName(notify.getFromUserName());
        newsMessage.setFromUserName(notify.getToUserName());
        newsMessage.setMsgType("news");
        newsMessage.setCreateTime(String.valueOf(System.currentTimeMillis() / 1000));
        newsMessage.setArticleCount(2);

        WeiXinNewsMessageItem item1 = new WeiXinNewsMessageItem();
        item1.setTitle("图文消息1");
        item1.setDescription("图文描述1");
        item1.setUrl("http://www.baidu.com");
        item1.setPicUrl("http://mmbiz.qpic.cn/mmbiz_jpg/u6NJ6Ufmbgu3JDorGMYISFFY7Q8aKsvLSp39KXA0I47FlVmZPtxj6zvrEZzHJBibJicLLA2BLadmC15ibGDs67veg/0");
        WeiXinNewsMessageItem item2 = new WeiXinNewsMessageItem();
        item2.setTitle("图文消息1");
        item2.setDescription("图文描述1");
        item2.setUrl("http://www.baidu.com");
        item2.setPicUrl("http://mmbiz.qpic.cn/mmbiz_jpg/u6NJ6Ufmbgu3JDorGMYISFFY7Q8aKsvLSp39KXA0I47FlVmZPtxj6zvrEZzHJBibJicLLA2BLadmC15ibGDs67veg/0");

        newsMessage.getArticles().add(item1);
        newsMessage.getArticles().add(item2);

        XStream xStream = new XStream();
        xStream.aliasType("xml", WeiXinNewsMessage.class);
        xStream.aliasType("item", WeiXinNewsMessageItem.class);

        return xStream.toXML(newsMessage);
    }
}
