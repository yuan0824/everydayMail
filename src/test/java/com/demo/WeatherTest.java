package com.demo;

import com.demo.pojo.Weather;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * @author yuan
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class WeatherTest {
    @Autowired
    private Call call;
    @Autowired
    private Weather weather;

    @Test
    public void getWeather() throws IOException {
        call.setWeather();
        log.info(String.valueOf(weather));
    }
}
