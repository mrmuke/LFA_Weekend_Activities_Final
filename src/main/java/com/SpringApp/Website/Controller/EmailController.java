package com.SpringApp.Website.Controller;

import com.SpringApp.Website.AccessingData.User;
import com.SpringApp.Website.Service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/api")
public class EmailController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    NotificationService notificationService;
    @GetMapping("/sendEmail")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<String> sendEmail(@RequestParam(required = true, value = "params") List<String> params){
        User user = new User(params.get(0), false);
        try{
            notificationService.sendNotification(user, params);
        }catch (MailException e){
            logger.info("Error sending email: " + e.getMessage());
        }
        return new ResponseEntity<>("Sending Email", HttpStatus.OK);

    }
}
