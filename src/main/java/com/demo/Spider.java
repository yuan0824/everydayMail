package com.demo;

import com.demo.pojo.One;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * @author yuan
 */
class Spider {

    One getOne() throws IOException {
        String url = "http://wufazhuce.com/";
        Document doc = Jsoup.connect(url).get();

        String newUrl = doc.getElementsByClass("item active").get(0).child(0).attr("href");
        doc = Jsoup.connect(newUrl).get();

        String content = doc.select("meta[name=description]").get(0).attributes().get("content");
        String[] contents = content.split("\n");

        String img = doc.select("meta[property=og:image]").get(0).attributes().get("content");

        One one = new One();
        one.setContents(contents);
        one.setImg(img);
        return one;
    }
}
