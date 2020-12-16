package com.home.util;

import com.github.kevinsawicki.http.HttpRequest;

/**
 * 文件描述
 *
 * @author 冯根源
 * @date 2020/12/17 0:41
 */
public class HttpUtil {
    public static String post(String url, String data) {
        HttpRequest request = HttpRequest.get(url);
        request.trustAllCerts();
        request.trustAllHosts();
        return request.send(data).body("utf-8");
    }

    public static String get(String url) {
        HttpRequest request = HttpRequest.get(url);
        request.trustAllCerts();
        request.trustAllHosts();
        return request.body("utf-8");
    }
}

