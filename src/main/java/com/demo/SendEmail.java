package com.demo;

import com.demo.pojo.One;
import com.demo.pojo.Weather;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yuan
 */
@Service
public class SendEmail {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private Configuration cfg;
    @Autowired
    private Call call;
    @Autowired
    private Weather weather;
    @Autowired
    private Spider spider;

    @Value("${day}")
    private Date day;
    @Value("${spring.mail.username}")
    private String mailfrom;
    @Value("${mailto}")
    private String mailto;

    public void send() throws IOException, TemplateException, MessagingException {
        //FreeMarker 数据模型+模板
        Map<String,Object> root = new HashMap<>();
        call.setWeather();
        root.put("weather",weather);
        One one = spider.getOne();
        root.put("one",one);
        Date now = new Date();
        int days = (int) ((now.getTime() - day.getTime()) / (1000*3600*24));
        root.put("day",days);
        Template temp = cfg.getTemplate("email.ftl");
        String mail = FreeMarkerTemplateUtils.processTemplateIntoString(temp, root);

        //JavaMailSender
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper help = new MimeMessageHelper(message , true);
        help.setFrom(mailfrom);
        help.setTo(mailto);
        help.setSubject("一封小邮件");
        help.setText(mail,true);
        mailSender.send(message);
    }
}
