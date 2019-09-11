package com.demo;

import freemarker.template.TemplateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import java.io.IOException;
import java.text.ParseException;

/**
 * @author yuan
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailTest {
    @Autowired
    private SendEmail sendEmail;

    @Test
    public void send() throws MessagingException, IOException, TemplateException, ParseException {
        sendEmail.send();
    }
}