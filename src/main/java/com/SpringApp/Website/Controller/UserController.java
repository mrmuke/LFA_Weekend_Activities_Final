package com.SpringApp.Website.Controller;

import com.SpringApp.Website.AccessingData.User;
import com.SpringApp.Website.AccessingData.UserRepository;
import com.SpringApp.Website.AccessingData.VoteEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8081")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserRepository userRepository;


    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        User userData = userRepository.findById(id);

            return new ResponseEntity<User>(userData, HttpStatus.OK);
    }
    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            if(userRepository.findByEmailAddress(user.getEmailAddress())==null) {
                User _user = userRepository.save(new User(user.getEmailAddress(), user.getAdmin()));




                return new ResponseEntity<>(_user,  HttpStatus.CREATED);
            }
            else if(userRepository.findByEmailAddress(user.getEmailAddress())!=null)
            {
                User _user = userRepository.findByEmailAddress(user.getEmailAddress());
                return new ResponseEntity<> (_user, HttpStatus.OK);
            }


            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Error Message"+e, HttpStatus.EXPECTATION_FAILED);
        }
    }
    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        User userData = userRepository.findById(id);

            User _user = userData;
            _user.setEmailAddress(user.getEmailAddress());
            _user.setUserName(user.getEmailAddress().substring(0,user.getEmailAddress().indexOf("@")));
            _user.setUpvotes(user.getUpvotes());
            logger.info("update user:{}", _user);
            //VoteEvent[] userUpvotes = new VoteEvent[];
            //for(int i = 0;i<user.getUpvotes().length;i++)
            //{
            //    userUpvotes.
            //}

            return new ResponseEntity<User>(userRepository.save(_user), HttpStatus.OK);

    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




}
