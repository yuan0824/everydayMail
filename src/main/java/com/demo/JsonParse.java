package com.demo;

import com.demo.pojo.City;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author yuan
 */
class JsonParse {

    static String parse(String name) {
        String content = "";
        ClassPathResource cpr = new ClassPathResource("static/city.json");
        try {
            byte[] bdata = FileCopyUtils.copyToByteArray(cpr.getInputStream());
            content = new String(bdata, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            TypeFactory typeFactory = objectMapper.getTypeFactory();
            CollectionType collectionType = typeFactory.constructCollectionType(List.class, City.class);
            List<City> list = objectMapper.readValue(content, collectionType);
            for (City cityEntity : list) {
                if (cityEntity.getCity_name().equals(name)) {
                    return cityEntity.getCity_code();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
