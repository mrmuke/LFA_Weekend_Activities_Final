package com.SpringApp.Website.Controller;

import com.SpringApp.Website.AccessingData.*;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@RestController
@RequestMapping("/api")

public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        User userData = userRepository.findById(id);

            return new ResponseEntity<User>(userData, HttpStatus.OK);
    }
    @PostMapping("/users/auth")
    public ResponseEntity<?> createUser(@RequestHeader("id") String idTokenString) throws GeneralSecurityException, IOException {
        HttpTransport transport = new NetHttpTransport();
        JsonFactory jsonFactory = new JacksonFactory();

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                .setAudience(Collections.singletonList("978419002714-0ngcjc58363k85n3a6fpmrdl0tome13b.apps.googleusercontent.com"))
                .build();
        GoogleIdToken idToken = verifier.verify(idTokenString);
        if (idToken != null) {
            Payload payload = idToken.getPayload();

            String email = payload.getEmail();
            //joanna.ashlock@finalsite.com
            if (email.indexOf("@lfanet.org") == -1 && email.indexOf("@students.lfanet.org") == -1) {
                return new ResponseEntity<String>("Sign in With LFA Email", HttpStatus.EXPECTATION_FAILED);
            }
            String name = (String) payload.get("name");
            String pictureUrl = (String) payload.get("picture");
            User user = userRepository.findByEmailAddress(email);
            if (user == null) {

                User _user = userRepository.save(new User(email, email.indexOf("@lfanet.org") > -1||email.equals("michael.xing@finalsite.com"), pictureUrl, name));
                String token = jwtTokenUtil.generateToken(_user);


                return new ResponseEntity<>(new JwtResponse(_user, token), HttpStatus.CREATED);
            } else if (user != null) {
                String token = jwtTokenUtil.generateToken(user);
                return new ResponseEntity<>(new JwtResponse(user, token), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.OK);


        } else {
            return new ResponseEntity<String>("Invalid ID Token", HttpStatus.EXPECTATION_FAILED);
        }

    }
    /*@PostMapping("/users/upvote/{id}")
    public ResponseEntity<?> upvoteEvent(@PathVariable("id") long id, @RequestBody VoteEvent event) {
        User user = userRepository.findById(id);
        VoteEvent[] array = new VoteEvent[user.getUpvotes().length+1];

        for (int i = 0; i < user.getUpvotes().length; i++) {
            array[i]=user.getUpvotes()[i];
        }
        array[array.length]=event;
        user.setUpvotes(array);
        return new ResponseEntity<User>(userRepository.save(user), HttpStatus.OK);
    }
    @PostMapping("/users/downvote/{id}")
    public ResponseEntity<?> downvoteEvent(@PathVariable("id") long id, @RequestBody VoteEvent event) {
       User user = userRepository.findById(id);
        VoteEvent[] list = new VoteEvent[user.getUpvotes().length-1];
        for (int i =0,j=0;i<user.getUpvotes().length-1;i++) {
            if(event.getId()==user.getUpvotes()[i].getId()) {
               continue;
            }
            list[j++]=user.getUpvotes()[i];
        }
        user.setUpvotes(list);

        return new ResponseEntity<User>(userRepository.save(user), HttpStatus.OK);
    }*/




}
