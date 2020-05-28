package com.SpringApp.Website.Service;

import com.SpringApp.Website.AccessingData.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    private JavaMailSender javaMailSender;
    @Autowired
    public NotificationService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendNotification(User user, List<String> params) throws MailException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmailAddress());
        mail.setFrom("lfaweekendactivities@gmail.com");
        mail.setSubject("REMINDER:" + params.get(1));
        mail.setText("You have an activity soon:\nActivity Name: " + params.get(1) + "\nActivity Time: " + params.get(2) + "\nIf you no longer plan on coming, go to this link to cancel your sign up: " + "localhost:8081");
        javaMailSender.send(mail);
    }
}
