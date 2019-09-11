package com.demo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

class Api {

    static String request(String httpUrl, String httpArg) {
        BufferedReader reader;
        String result = null;
        StringBuilder sbf = new StringBuilder();
        httpUrl = httpUrl + httpArg;

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String strRead;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    static Weather parse(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json);
        JsonNode today = root.get("newslist").get(0);
        String type = today.get("weather").asText();
        String low = today.get("lowest").asText();
        String high = today.get("highest").asText();
        int air = today.get("air").asInt();
        String air_level = today.get("air_level").asText();
        String weatherimg = today.get("weatherimg").asText();

        Weather weather = new Weather();
        weather.setType(type);
        weather.setTemperature(low + "/" + high);
        weather.setAir(air);
        weather.setAir_level(air_level);

        weather.setWeatherimg("https://yuangaopeng.com/img/weather/" + weatherimg);
        return weather;
    }
}