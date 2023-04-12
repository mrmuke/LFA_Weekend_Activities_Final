package com.SpringApp.Website.Socket;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.SpringApp.Website.AccessingData.Schedule;
import com.google.gson.Gson;

@Controller
public class SocketController {
    
    @MessageMapping("/send")
    @SendTo("/topic/messages")
    public ResponseEntity<Schedule> send(@Payload String string){
        Gson gson = new Gson();
        Schedule schedule = gson.fromJson(string, Schedule.class);
        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }
}
