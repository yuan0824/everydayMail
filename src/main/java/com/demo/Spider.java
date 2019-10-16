package com.demo;

import com.demo.pojo.One;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

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

        Elements elements1 = doc.select("meta[name=description]");
        String content = elements1.get(0).attributes().get("content");
        String[] contents = content.split("\n");

        Elements elements2 = doc.select("meta[property=og:image]");
        String img = elements2.get(0).attributes().get("content");

        One one = new One();
        one.setContents(contents);
        one.setImg(img);
        return one;
    }
}
