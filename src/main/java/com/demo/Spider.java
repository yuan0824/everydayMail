package com.demo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;

import java.io.IOException;

/**
 * @author yuan
 */
class Spider {

    static One getOne() throws IOException {
        String url = "http://wufazhuce.com/";
        Document doc = Jsoup.connect(url).get();

        Node root = doc.childNode(1).childNode(2).childNode(3).childNode(1).childNode(1).childNode(1).childNode(1).childNode(1).childNode(1);

        String content = root.childNode(5).childNode(3).childNode(1).childNode(0).toString();
        String img = root.childNode(1).childNode(0).attr("src");

        One one = new One();
        one.setContext(content);
        one.setImg(img);
        return one;
    }

}
