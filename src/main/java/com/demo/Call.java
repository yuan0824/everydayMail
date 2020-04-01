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
@PropertySource(value="classpath:application.yml",encoding = "utf-8")
public class Call {
    @Autowired
    private Api api;
    @Autowired
    private Weather weather;

    @Value("${city}")
    private String city;
    @Value("${tianxingkey}")
    private String tianxingkey;

    void setWeather1() throws IOException {
        String httpUrl = "http://t.weather.sojson.com/api/weather/city/";
        String httpArg = JsonParse.parse(city);
        String json = api.request(httpUrl, httpArg);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json).get("data").get("forecast").get(0);
        String notice = root.get("notice").asText();
        String low = root.get("low").asText().substring(3);
        String high = root.get("high").asText().substring(3);
        int aqi = root.get("aqi").asInt();
        weather.setNotice(notice);
        weather.setTemperature(low + "/" + high);
        weather.setAqi(aqi);
    }

    void setWeather2() throws IOException {
        String httpUrl = "http://api.tianapi.com/txapi/tianqi/?";
        String httpArg = "key=" + tianxingkey + "&city=" + city;
        String json = api.request(httpUrl, httpArg);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json);
        JsonNode today = root.get("newslist").get(0);
        String type = today.get("weather").asText();
        String weatherImg = today.get("weatherimg").asText();
        weather.setType(type);
        weather.setWeatherImg("https://yuangaopeng.com/img/weather/" + weatherImg);
        weather.setCity(city);
    }

    void setWeather() throws IOException {
        setWeather1();
        setWeather2();
    }
}
