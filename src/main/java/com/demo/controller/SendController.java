package com.demo.controller;

import com.demo.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author yuan
 */
@RestController
@RequestMapping("/send")
public class SendController {

    @Autowired
    private SendEmail sendEmail;

    @GetMapping
    public String sendMail(){
        try {
            sendEmail.send();
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
    }
}
