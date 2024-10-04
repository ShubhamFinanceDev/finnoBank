package com.kemal.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleMail(String to,String password) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject("Registration for Fino Bank");
        message.setText("Dear Sir/Madam,\n" +
                " \n" +
                "User has been created successfully for "+ to +" . Please use below credentials to login FINNO Application.\n" +
                " \n" +
                "user-name:" + to + "\n" +
                "password:"+password+"\n" +
                " \n" +
                "application-url: \n" +
                " \n" +
                "Regards,\n" +
                "It Support.");

        mailSender.send(message);
    }
}
