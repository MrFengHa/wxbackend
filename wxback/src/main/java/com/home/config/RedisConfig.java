package com.home.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 文件描述
 *
 * @Author 冯根源
 * @create 2020/12/17 10:46
 */
@Configuration
public class RedisConfig {
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Bean
    RedisConnectionFactory connectionFactory(){
        RedisStandaloneConfiguration standaloneConfiguration = new RedisStandaloneConfiguration(host,port);
        LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory(standaloneConfiguration);
        return connectionFactory;
    }

    @Bean
    RedisTemplate redisTemplate(){
        return new StringRedisTemplate(connectionFactory());

    }
}
