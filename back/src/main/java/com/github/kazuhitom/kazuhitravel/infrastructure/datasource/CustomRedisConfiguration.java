package com.github.kazuhitom.kazuhitravel.infrastructure.datasource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

/**
 * 設定ファイルに記述しきれない設定・Bean定義などを定義する。
 */
@Configuration
public class CustomRedisConfiguration {
    /**
     * RedisTemplate定義。
     * オブジェクト保存汎用。オブジェクト保存時に「JSONで保存」するよう、そこだけカスタム。
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer(new CustomObjectMapper())); // ここだけカスタム
        template.setHashKeySerializer(template.getKeySerializer());
        template.setHashValueSerializer(template.getValueSerializer());
        return template;
    }
}
