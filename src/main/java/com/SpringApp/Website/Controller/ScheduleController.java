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
@CrossOrigin(origins = "http://localhost:8081")

public class ScheduleController {

    @Autowired
    ScheduleRepository scheduleRepository;

    @GetMapping("/schedules")

    public ResponseEntity<List<Schedule>> getSchedules(@RequestParam(required = false) String date) {
        try {
            List<Schedule> schedules = new ArrayList<Schedule>();

            if (date == null)
                scheduleRepository.findAll().forEach(schedules::add);
            else
                scheduleRepository.findByDate(date).forEach(schedules::add);

            if (schedules.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(schedules, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
        Schedule schedule = scheduleRepository.findFirstByOrderByIdDesc();
        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }


    @PostMapping("/schedules")
    public ResponseEntity<?> createSchedule(@RequestBody Schedule schedule) {
        try {

            Schedule _schedule = scheduleRepository.save(new Schedule(schedule.getDate(), schedule.getFriday(), schedule.getSaturday(), schedule.getSunday()));
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
            _schedule.setFriday(schedule.getFriday());
            _schedule.setSaturday(schedule.getSaturday());
            _schedule.setSunday(schedule.getSunday());


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
