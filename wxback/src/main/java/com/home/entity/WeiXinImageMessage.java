package com.home.entity;

/**
 * 文件描述
 *
 * @author 冯根源
 * @date 2020/12/16 23:41
 */
public class WeiXinImageMessage {
    private String ToUserName;
    private String FromUserName;
    private String CreateTime;
    private String MsgType;
    private WeiXinImageMessageItem Image;

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

    public WeiXinImageMessageItem getImage() {
        return Image;
    }

    public void setImage(WeiXinImageMessageItem image) {
        Image = image;
    }
}
