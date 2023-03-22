package com.github.kazuhitom.kazuhitravel.infrastructure.datasource;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * このアプリケーション中、RedisでJSONを扱う場合の統一変換則。
 */
public class CustomRedisObjectMapper extends ObjectMapper {
    public CustomRedisObjectMapper() {
        enableDefaultTyping(DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY); // GenericJackson2JsonRedisSerializer のコンストラクタ未指定時のデフォルト
        // JSR310 Date and Time API 変換対応 (LocalDate系)
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(formatter));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(formatter));
        registerModule(javaTimeModule);
        // class/recordにて「@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)」を書かなくても、
        // "@CLASS"定義が出力されるように、全体設定。(@CLASSが無いとデシリアライズ出来ない。)
        StdTypeResolverBuilder trb = new DefaultTypeResolverBuilder(DefaultTyping.EVERYTHING);
        trb.init(JsonTypeInfo.Id.CLASS, null).inclusion(JsonTypeInfo.As.WRAPPER_OBJECT);
        setDefaultTyping(trb);
    }
}
