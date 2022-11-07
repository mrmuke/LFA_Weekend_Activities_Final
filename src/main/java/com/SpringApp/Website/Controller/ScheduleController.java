package com.SpringApp.Website.Controller;

import com.SpringApp.Website.AccessingData.Schedule;
import com.SpringApp.Website.AccessingData.ScheduleRepository;
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
    @PostMapping("/schedules/publish/{id}")
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
    @PostMapping("/schedules")
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
    @DeleteMapping("/schedules/{id}")
    public ResponseEntity<HttpStatus> deleteSchedule(@PathVariable("id") long id) {
        try {
            scheduleRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
