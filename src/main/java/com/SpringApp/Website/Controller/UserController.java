package com.SpringApp.Website.Controller;
import com.SpringApp.Website.AccessingData.User;
import com.SpringApp.Website.AccessingData.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @RequestMapping("/home")
    @CrossOrigin(origins = "http://localhost:8081")
    public String home() {

        return "welcome";
    }
    @GetMapping("/users")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String emailAddress) {
        try {
            List<User> users = new ArrayList<User>();

            if (emailAddress == null)
                userRepository.findAll().forEach(users::add);
            else
                userRepository.findByEmailAddress(emailAddress).forEach(users::add);

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/users/{id}")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        Optional<User> userData = userRepository.findById(id);

        if (userData.isPresent()) {
            return new ResponseEntity<>(userData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/users")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> createUser(@RequestBody String emailAddress) {
        try {
            if(userRepository.findByEmailAddress(emailAddress).size()==0) {
                User _user = userRepository.save(new User(emailAddress));
                return new ResponseEntity<User>(_user, HttpStatus.CREATED);
            }

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Error Message", HttpStatus.EXPECTATION_FAILED);
        }
    }
    @PutMapping("/users/{id}")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        Optional<User> userData = userRepository.findById(id);

        if (userData.isPresent()) {
            User _user = userData.get();
            _user.setEmailAddress(user.getEmailAddress());
            String[] words = user.getEmailAddress().split("\\.");
            _user.setUserName(words[0]);
            return new ResponseEntity<User>(userRepository.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/users")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<HttpStatus> deleteAllUsers() {
        try {
            userRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

    }

}
