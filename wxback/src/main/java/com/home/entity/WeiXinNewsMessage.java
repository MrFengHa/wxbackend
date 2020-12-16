package com.home.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件描述
 * 微信图文回复
 * @author 冯根源
 * @date 2020/12/17 0:12
 */
public class WeiXinNewsMessage {
    private String ToUserName;
    private String FromUserName;
    private String CreateTime;
    private String MsgType;
    private Integer ArticleCount;
    private List<WeiXinNewsMessageItem> Articles = new ArrayList<>();

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public Integer getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(Integer articleCount) {
        ArticleCount = articleCount;
    }

    public List<WeiXinNewsMessageItem> getArticles() {
        return Articles;
    }

    public void setArticles(List<WeiXinNewsMessageItem> articles) {
        Articles = articles;
    }
}
