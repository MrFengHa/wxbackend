package com.home;

import com.home.util.WeiXinUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

/**
 * 文件描述
 *
 * @author 冯根源
 * @date 2020/12/17 1:18
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TemplateMessageTest {
    @Autowired
    WeiXinUtil weiXinUtil;

    @Test
    public void testSendMessage(){
        HashMap data = new HashMap();
        HashMap value = new HashMap();
        value.put("value","测试");
        value.put("color","#ff0000");
        data.put("name",value);

        boolean result = weiXinUtil.sendTemplateMessage("of-Z25huSxircdcoRw5MekrqhaWI","8lmr4seamPyGNyl5pY9SkkC8qP0ji5Nk4aC8-qj5K5o","http://www.baidu.com",data);
        System.out.println(result);
       // Assert.assertEquals(true,result);
    }
}
