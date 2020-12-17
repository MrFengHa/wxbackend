package com.home;

import com.home.entity.WeiXinMenu;
import com.home.entity.WeiXinMenuItem;
import com.home.util.WeiXinUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 文件描述
 *
 * @Author 冯根源
 * @create 2020/12/17 11:20
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class WeiXinMenuTest {
    @Autowired
    WeiXinUtil weiXinUtil;
    @Test
    public void testCreateMenu(){
        WeiXinMenu menu = new WeiXinMenu();

        WeiXinMenuItem item1 = new WeiXinMenuItem();
        item1.setName("菜单");
        item1.setType("click");
        item1.setKey("CLICK_ID_01");

        WeiXinMenuItem item2 =new WeiXinMenuItem();
        item2.setName("菜单2");

        WeiXinMenuItem item2_1 =new WeiXinMenuItem();
        item2_1.setName("扫描waitmsg");
        item2_1.setType("scancode_waitmsg");
        item2_1.setKey("rselfmenu_0_0");

        WeiXinMenuItem item2_2 =new WeiXinMenuItem();
        item2_2.setName("扫描push");
        item2_2.setType("scancode_push");
        item2_2.setKey("rselfmenu_0_1");

        WeiXinMenuItem item2_3 =new WeiXinMenuItem();
        item2_3.setName("进入百度");
        item2_3.setType("view");
        item2_3.setUrl("http://8163af579cb4.ngrok.io/aaa");

        item2.getSub_button().add(item2_1);
        item2.getSub_button().add(item2_2);
        item2.getSub_button().add(item2_3);

        WeiXinMenuItem item3 =new WeiXinMenuItem();
        item3.setName("菜单3");

        WeiXinMenuItem item3_1 =new WeiXinMenuItem();
        item3_1.setName("选择图片");
        item3_1.setType("pic_photo_or_album");
        item3_1.setKey("SELECT_PIC_01");

        WeiXinMenuItem item3_2 =new WeiXinMenuItem();
        item3_2.setName("进入百度");
        item3_2.setType("view");
        item3_2.setUrl("http://www.baidu.com");

        item3.getSub_button().add(item3_1);
        item3.getSub_button().add(item3_2);

        menu.getButton().add(item1);
        menu.getButton().add(item2);
        menu.getButton().add(item3);



        boolean result =weiXinUtil.createMenu(menu);
        System.out.println(result);
        //Assert.assertEquals(true,result);

    }
}
