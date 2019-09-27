package com.demo;

import com.demo.pojo.One;
import com.demo.pojo.Weather;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.io.IOException;

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

    @Bean
    public Spider Spider() {
        return new Spider();
    }

}
