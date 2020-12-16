package com.home;

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
 * @author 冯根源
 * @date 2020/12/17 0:58
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AccessTokenTest {
    @Autowired
    WeiXinUtil weiXinUtil;
    @Test
    public void testGetAccessToken(){
        String accessToken = weiXinUtil.getAccessToken();
        Assert.assertNotNull(accessToken);
        System.out.println(accessToken);

    }
}
