package com.demo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Value("${city}")
    private String city;

    private String getNotice() throws IOException {
        String httpUrl = "http://t.weather.sojson.com/api/weather/city/";
        String httpArg = JsonParse.parse(city);
        String json = Api.request(httpUrl, httpArg);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json);
        return root.get("data").get("forecast").get(0).get("notice").asText();
    }

    Weather weather() throws IOException {
        String notice = getNotice();
        String httpUrl = "http://api.tianapi.com/txapi/tianqi/?";
        String httpArg = "key=af9157730a6fad5f8942a375598f44e8&city=" + city;
        String json = Api.request(httpUrl, httpArg);
        Weather weather = Api.parse(json);
        weather.setNotice(notice);
        weather.setCity(city);
        return weather;
    }

}
