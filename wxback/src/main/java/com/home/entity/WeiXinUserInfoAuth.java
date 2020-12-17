package com.home.entity;

/**
 * 文件描述
 * 微信获取网页授权用户信息
 * @Author 冯根源
 * @create 2020/12/17 14:49
 */
public class WeiXinUserInfoAuth {
    private String openid;
    private String nickname;
    private Integer sex;
    private String province;
    private String city;
    private String country;
    private String headimgurl;
    private String[] privilege;
    private String unionid;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String[] getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String[] privilege) {
        this.privilege = privilege;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
}
