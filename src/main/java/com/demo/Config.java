package com.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author yuan
 */
@Configuration
@EnableAspectJAutoProxy
public class Config {

    @Bean
    public Api api(){
        return new Api();
    }

    @Bean
    public Weather weather(){
        return new Weather();
    }
}
