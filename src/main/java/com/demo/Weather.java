package com.demo;

import lombok.Data;

/**
 * @author yuan
 */
@Data
public class Weather {
    private String notice;
    private String type;
    private String temperature;
    private int air;
    private String air_level;
    private String weatherimg;
    private String city;
}
