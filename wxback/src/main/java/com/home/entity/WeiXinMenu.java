package com.home.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件描述
 * 微信自定义菜单
 * @Author 冯根源
 * @create 2020/12/17 11:06
 */
public class WeiXinMenu {
        private List<WeiXinMenuItem> button = new ArrayList<>();

    public List<WeiXinMenuItem> getButton() {
        return button;
    }

    public void setButton(List<WeiXinMenuItem> button) {
        this.button = button;
    }

    @Override
    public String toString() {
        return "WeiXinMenu{" +
                "button=" + button +
                '}';
    }
}
