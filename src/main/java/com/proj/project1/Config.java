package com.proj.project1;

import feign.codec.Decoder;
import feign.jackson.JacksonDecoder;
import feign.okhttp.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public Decoder feignDecoder(){
        return new JacksonDecoder();
    }
    @Bean
    public OkHttpClient client() {
        return new OkHttpClient();
    }
    }
