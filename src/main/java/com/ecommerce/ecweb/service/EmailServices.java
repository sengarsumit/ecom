package com.ecommerce.ecweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailServices {
    @Autowired
    JavaMailSender javaMailSender;

    public void sendMail(String receiver, String token) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("sengar.sumit.2000@gmail.com");
        simpleMailMessage.setTo(receiver);
        simpleMailMessage.setSubject("Account activation");
        simpleMailMessage.setText("Your account is registered please activate the account by clicking below link: " + token);
        javaMailSender.send(simpleMailMessage);
    }
}

