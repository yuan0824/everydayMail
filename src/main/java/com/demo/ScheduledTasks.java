package com.demo;

import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.IOException;
import java.text.ParseException;


/**
 * @author yuan
 */
@Slf4j
@Component
public class ScheduledTasks {
    private final SendEmail sendEmail;

    @Autowired
    public ScheduledTasks(SendEmail sendEmail) {
        this.sendEmail = sendEmail;
    }

    @Scheduled(cron = "0 0 8 * * ?")
    public void reportCurrentTime() throws TemplateException, IOException, MessagingException, ParseException {
        log.info("start send");
        sendEmail.send();
        log.info("end send");
    }
}
