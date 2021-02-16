package com.SpringApp.Website.Controller;

import com.SpringApp.Website.Service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class WeeklyEmail {

    private static final Logger log = LoggerFactory.getLogger(WeeklyEmail.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Autowired
    NotificationService notificationService;
    @Scheduled(fixedRate = 50000)
    public void reportCurrentTime() {
        try {
            notificationService.sendWeekly("michael.xing@students.lfanet.org");
        } catch (MessagingException e) {
        }
        log.info("The time is now {}", dateFormat.format(new Date()));
    }
}