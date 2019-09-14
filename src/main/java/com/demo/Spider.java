package com.demo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yuan
 */
class Spider {

    static One getOne() throws IOException, ParseException {
        Date now = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date day = dateFormat.parse("2019-09-01");
        int days = (int) ((now.getTime() - day.getTime()) / (1000*3600*24));
        int value = 2562 + days - 6 - 1;
        String url = "http://wufazhuce.com/one/" + value;
        Document document = Jsoup.connect(url).get();

        Elements elements1 = document.select("meta[name=description]");
        String content = elements1.get(0).attributes().get("content");

        Elements elements2 = document.select("meta[property=og:image]");
        String img = elements2.get(0).attributes().get("content");

        One one = new One();
        one.setContext(content);
        one.setImg(img);
        return one;
    }

}
