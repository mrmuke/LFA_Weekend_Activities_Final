package com.SpringApp.Website.Controller;

import com.SpringApp.Website.AccessingData.Schedule;
import com.SpringApp.Website.AccessingData.ScheduleDay;
import com.SpringApp.Website.AccessingData.ScheduleEvent;
import com.SpringApp.Website.AccessingData.ScheduleRepository;
import com.SpringApp.Website.PostSchemas.Drop;
import com.SpringApp.Website.PostSchemas.EditUsersSignedUp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")

public class ScheduleController {

    @Autowired
    ScheduleRepository scheduleRepository;

    @GetMapping("/schedules")
    public ResponseEntity<List<Schedule>> getSchedules() {


        List<Schedule> schedules =getAllSchedules();
        if (schedules.isEmpty()){
            return new ResponseEntity<>(schedules,HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(schedules, HttpStatus.OK);

    }

    @GetMapping("/schedules/{id}")
    public ResponseEntity<Schedule> getScheduleById(@PathVariable("id") long id) {
        Optional<Schedule> scheduleData = scheduleRepository.findById(id);
        if (scheduleData.isPresent()) {
            return new ResponseEntity<>(scheduleData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/schedules/current")
    public ResponseEntity<Schedule> getCurrentSchedule(){
        //Schedule schedule = scheduleRepository.findFirstByOrderByIdDesc();
        Optional<Schedule> schedule = scheduleRepository.findByDisplayed(true);
        return new ResponseEntity<>(schedule.get(), HttpStatus.OK);
    }
    
    public List<Schedule> getAllSchedules(){
        List<Schedule> schedules = new ArrayList<Schedule>();

        scheduleRepository.findByOrderByIdDesc().forEach(schedules::add);
        if (schedules.isEmpty()){
            return schedules;
        }

        return schedules;
    }
    @PostMapping("/admin/schedules/publish/{id}")
    public ResponseEntity<List<Schedule>> publishSchedule(@PathVariable("id") long id ){
        Optional<Schedule> s = scheduleRepository.findByDisplayed(true);
        if(s.isPresent()){
            Schedule sch = s.get();
            sch.setDisplayed(false);
            scheduleRepository.save(sch);
        }
        Optional<Schedule> scheduleData = scheduleRepository.findById(id);
        Schedule _schedule = scheduleData.get();
        _schedule.setDisplayed(true);
        scheduleRepository.save(_schedule);
        List<Schedule> schedules = getAllSchedules();
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }
    @PostMapping("/admin/schedules")
    public ResponseEntity<?> createSchedule(@RequestBody Schedule schedule) {
        try {
            Schedule _schedule = scheduleRepository.save(new Schedule(schedule.getDate(), schedule.getPhoneNumbers(), schedule.getScheduleDays()));
            return new ResponseEntity<Schedule>(_schedule, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>("Error Message"+e, HttpStatus.EXPECTATION_FAILED);
        }
    }
    @PutMapping("/schedules/{id}")
    public ResponseEntity<?> updateSchedule(@PathVariable("id") long id, @RequestBody Schedule schedule) {
        Optional<Schedule> scheduleData = scheduleRepository.findById(id);
        if (scheduleData.isPresent()) {
            Schedule _schedule = scheduleData.get();
            _schedule.setDate(schedule.getDate());
            _schedule.setPhoneNumbers(schedule.getPhoneNumbers());
            _schedule.setScheduleDays(schedule.getScheduleDays());

            return new ResponseEntity<Schedule>(scheduleRepository.save(_schedule), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/schedules/{id}/add")
    public ResponseEntity<?> addUser(@PathVariable("id") long id, @RequestBody EditUsersSignedUp edit){
        Optional<Schedule> scheduleData = scheduleRepository.findById(id);
        if (scheduleData.isPresent() && edit.getDay() != -1 && edit.getEvent() != -1) {
            Schedule _schedule = scheduleData.get();
            ScheduleDay scheduleDay = _schedule.getScheduleDays()[edit.getDay()];
            ScheduleEvent scheduleEvent = scheduleDay.getEvents()[edit.getEvent()];
            if(edit.getAction().equals("waitlist")){
                scheduleEvent.addWaitlist(edit.getUser());
            } else if (edit.getAction().equals("usersSignedUp")){
                scheduleEvent.addUsersSignedUp(edit.getUser());
            }
            return new ResponseEntity<Schedule>(scheduleRepository.save(_schedule), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/admin/schedules/{id}/remove")
    public ResponseEntity<?> removeUser(@PathVariable("id") long id, @RequestBody EditUsersSignedUp edit){
        Optional<Schedule> scheduleData = scheduleRepository.findById(id);
        if (scheduleData.isPresent()) {
            Schedule _schedule = scheduleData.get();
            ScheduleDay scheduleDay = _schedule.getScheduleDays()[edit.getDay()];
            ScheduleEvent scheduleEvent = scheduleDay.getEvents()[edit.getEvent()];
            scheduleEvent.removeUser(edit.getUser());
            return new ResponseEntity<Schedule>(scheduleRepository.save(_schedule), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/schedules/{id}/bump")
    public ResponseEntity<?> bumpUser(@PathVariable("id") long id, @RequestBody EditUsersSignedUp edit){
        Optional<Schedule> scheduleData = scheduleRepository.findById(id);
        if (scheduleData.isPresent()) {
            Schedule _schedule = scheduleData.get();
            ScheduleDay scheduleDay = _schedule.getScheduleDays()[edit.getDay()];
            ScheduleEvent scheduleEvent = scheduleDay.getEvents()[edit.getEvent()];
            scheduleEvent.bumpUser(edit.getUser());
            return new ResponseEntity<Schedule>(scheduleRepository.save(_schedule), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/schedules/{id}/swap")
    public ResponseEntity<?> swapUser(@PathVariable("id") long id, @RequestBody Drop edit){
        Optional<Schedule> scheduleData = scheduleRepository.findById(id);
        if (scheduleData.isPresent()) {
            Schedule _schedule = scheduleData.get();
            ScheduleDay scheduleDay = _schedule.getScheduleDays()[edit.getDay()];
            ScheduleEvent scheduleEvent = scheduleDay.getEvents()[edit.getEvent()];
            //scheduleEvent.bumpUser(edit.getUser());
            return new ResponseEntity<Schedule>(scheduleRepository.save(_schedule), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/admin/schedules/{id}")
    public ResponseEntity<HttpStatus> deleteSchedule(@PathVariable("id") long id) {
        try {
            scheduleRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
