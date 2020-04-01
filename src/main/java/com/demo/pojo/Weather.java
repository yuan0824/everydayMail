package com.demo.pojo;

import lombok.Data;

/**
 * @author yuan
 */
@Data
public class Weather {
    private String notice;
    private String type;
    private String temperature;
    private int aqi;
    private String quality;
    private String weatherImg;
    private String city;

    public void setAqi(int aqi) {
        this.aqi = aqi;
        if(aqi <= 50){
            quality = "优";
        }
        if(aqi > 50 && aqi <= 100){
            quality = "良";
        }
        if(aqi > 100 && aqi <= 150){
            quality = "轻度污染";
        }
        if(aqi > 150 && aqi <= 200){
            quality = "中度污染";
        }
        if(aqi > 200 && aqi <= 300){
            quality = "重度污染";
        }
        if(aqi > 300){
            quality = "严重污染";
        }
    }
}
