package com.SpringApp.Website.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.SpringApp.Website.AccessingData.ScheduleEvent;
import com.SpringApp.Website.AccessingData.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.MimeMessageHelper;

@Service
public class NotificationService {
    private JavaMailSender javaMailSender;

    @Autowired
    public NotificationService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendNotification(User user, ScheduleEvent event) throws MessagingException{
        int place = 0;
        for(int i = 0;i<event.getUsersSignedUp().length;i++)
        {
            if(event.getUsersSignedUp()[i].getId()==user.getId())
            {
                place=i+1;
                break;
            }
        }
        MimeMessage message = javaMailSender.createMimeMessage();

        message.setSubject("REMINDER: " + event.getName());
        MimeMessageHelper helper;
        helper = new MimeMessageHelper(message, true);
        helper.setFrom("lfaweekendactivities@gmail.com");
        helper.setText("<strong><u>You have an activity soon:</u></strong><br/><b>Activity Name:</b> " + event.getName() + "<br/><b>Activity Time:</b> " + event.getTimeSlot() +"<br/>On this activity you are number " +((event.getPersonLimit()-place<0)?"WAITLIST":place)+ " on the list.<br/>If you no longer plan on coming, go to wa.lfaapps.com to cancel your sign up.", true);
        helper.setTo(user.getEmailAddress());
        
        javaMailSender.send(message);
    }
}
