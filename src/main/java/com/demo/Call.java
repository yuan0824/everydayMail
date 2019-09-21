package com.demo;

import com.demo.pojo.Weather;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 天行API（能用1000次）（https://www.tianapi.com）
 * 天气API（免费）（https://www.sojson.com/api/weather.html）
 * @author yuan
 */
@Component
@PropertySource(value="classpath:application.properties",encoding = "utf-8")
public class Call {
    private final Api api;
    private final Weather weather;

    @Value("${city}")
    private String city;
    @Value("${tianxingkey}")
    private String tianxingkey;

    @Autowired
    public Call(Api api, Weather weather) {
        this.api = api;
        this.weather = weather;
    }

    private void getNotice() throws IOException {
        String httpUrl = "http://t.weather.sojson.com/api/weather/city/";
        String httpArg = JsonParse.parse(city);
        String json = api.request(httpUrl, httpArg);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json).get("data").get("forecast").get(0);
        String notice = root.get("notice").asText();
        String low = root.get("low").asText().substring(3);
        String high = root.get("high").asText().substring(3);
        weather.setNotice(notice);
        weather.setTemperature(low + "/" + high);
    }

    void weather() throws IOException {
        getNotice();
        String httpUrl = "http://api.tianapi.com/txapi/tianqi/?";
        String httpArg = "key=" + tianxingkey + "&city=" + city;
        String json = api.request(httpUrl, httpArg);
        api.parse(json);
        weather.setCity(city);
    }

}
