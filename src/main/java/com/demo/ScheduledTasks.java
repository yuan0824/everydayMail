package com.demo;

import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.IOException;


/**
 * @author yuan
 */
@Slf4j
@Component
public class ScheduledTasks {
    @Autowired
    private SendEmail sendEmail;

    @Scheduled(cron = "${cron}")
    public void reportCurrentTime() throws TemplateException, IOException, MessagingException {
        log.info("start send");
        sendEmail.send();
        log.info("end send");
    }
}
