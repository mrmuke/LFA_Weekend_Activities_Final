package com.SpringApp.Website.Controller;

import javax.mail.MessagingException;

import com.SpringApp.Website.AccessingData.ScheduleEvent;
import com.SpringApp.Website.AccessingData.User;
import com.SpringApp.Website.AccessingData.UserRepository;
import com.SpringApp.Website.Service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class EmailController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    NotificationService notificationService;
    @Autowired
    UserRepository userRepository;
    @PostMapping("/sendEmail")
    public ResponseEntity<String> sendEmail(@RequestParam(required = true, value = "userId") long userId, @RequestBody ScheduleEvent event) {
        User userData = userRepository.findById(userId);
        try {
            notificationService.sendNotification(userData, event);
        } catch (MessagingException e) {
            logger.info("Error sending email: " + e.getMessage());
        }
        return new ResponseEntity<>("Sending Email", HttpStatus.OK);

    }
}
