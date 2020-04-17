package com.SpringApp.Website.Controller;

import com.SpringApp.Website.AccessingData.Event;
import com.SpringApp.Website.AccessingData.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EventController {

    @Autowired
    EventRepository eventRepository;

    @GetMapping("/events")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<List<Event>> getAllEvents(@RequestParam(required = false) String name) {
        try {
            List<Event> events = new ArrayList<Event>();

            if (name == null)
                eventRepository.findAll().forEach(events::add);
            else
                eventRepository.findByName(name).forEach(events::add);

            if (events.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(events, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/events/{id}")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<Event> getEventById(@PathVariable("id") long id) {
        Optional<Event> eventData = eventRepository.findById(id);

        if (eventData.isPresent()) {
            return new ResponseEntity<>(eventData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @PostMapping("/events")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> createEvent(@RequestBody Event event) {
        try {
            event.setTimeSlot(event.getTimeSlot().substring(0,10));
            Event _event = eventRepository.save(new Event(event.getName(), event.getTimeSlot()));
            return new ResponseEntity<Event>(_event, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>("Error Message", HttpStatus.EXPECTATION_FAILED);
        }
    }



    @PutMapping("/events/{id}")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> updateEvent(@PathVariable("id") long id, @RequestBody Event event) {
        Optional<Event> eventData = eventRepository.findById(id);

        if (eventData.isPresent()) {
            Event _event = eventData.get();
            _event.setName(event.getName());
            _event.setTimeSlot(event.getTimeSlot());
            _event.setUpVotes(event.getUpVotes());

            return new ResponseEntity<Event>(eventRepository.save(_event), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/events/{id}")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<HttpStatus> deleteEvent(@PathVariable("id") long id) {
        try {
            eventRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("/events")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<HttpStatus> deleteAllEvents() {
        try {
            eventRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

    }



}
