package com.demo;

import com.demo.pojo.One;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import java.io.IOException;

/**
 * @author yuan
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OneTest {
    @Autowired
    private Spider spider;

    @Test
    public void getOne() throws IOException {
        One one = spider.getOne();
        log.info(String.valueOf(one));
    }
}
