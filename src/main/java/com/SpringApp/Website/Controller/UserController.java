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
import java.util.Optional;

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
            if (email.indexOf("@lfanet.org") == -1 && email.indexOf("@students.lfanet.org") == -1||email.equals("eglazer@imsa.edu")) {
                return new ResponseEntity<String>("Sign in With LFA Email", HttpStatus.EXPECTATION_FAILED);
            }
            String name = (String) payload.get("name");
            String pictureUrl = (String) payload.get("picture");
            User user = userRepository.findByEmailAddress(email);
            if (user == null) {

                User _user = userRepository.save(new User(email, email.indexOf("@lfanet.org") > -1||email.equals("conghoang.le@students.lfanet.org")||email.equals("eglazer@imsa.edu"), pictureUrl, name, 0));
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

    @PostMapping("/users/strike")
    public ResponseEntity<?> strikeUser(@RequestBody int Id) {
        User userData = userRepository.findById(Id);
        userData.setStrikes(userData.getStrikes() + 1);
        return new ResponseEntity<>(userRepository.save(userData), HttpStatus.OK);
    }

    @GetMapping("/users/all")
    public ResponseEntity<?> getAllUsers(){
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/users/resetStrike")
    public ResponseEntity<?> resetStrike(@RequestBody int Id){
        User userData = userRepository.findById(Id);
        userData.setStrikes(0);
        return new ResponseEntity<>(userRepository.save(userData), HttpStatus.OK);
    }
}
