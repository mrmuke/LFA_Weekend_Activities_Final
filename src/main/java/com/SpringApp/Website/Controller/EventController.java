package com.SpringApp.Website.Controller;

import com.SpringApp.Website.AccessingData.ScheduleEventRepository;
import com.SpringApp.Website.AccessingData.VoteEvent;
import com.SpringApp.Website.AccessingData.VoteEventRepository;
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
    VoteEventRepository voteEventRepository;
    @Autowired
    ScheduleEventRepository scheduleEventRepository;
    @GetMapping("/voteEvents")
    public ResponseEntity<List<VoteEvent>> getVoteEvents() {
        try {
            List<VoteEvent> events = new ArrayList<VoteEvent>();
            voteEventRepository.findAll().forEach(events::add);
            if (                                                                                                                                                                                                                                                                                                                                                        events.isEmpty()) {
                return new ResponseEntity<>(events,HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(events, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/voteEvents/{id}")

    public ResponseEntity<VoteEvent> getVoteEventById(@PathVariable("id") long id) {
        Optional<VoteEvent> eventData = voteEventRepository.findById(id);

        if (eventData.isPresent()) {
            return new ResponseEntity<>(eventData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/voteEvents/upvote/{id}")
    public ResponseEntity<?> upvoteEvent(@PathVariable("id") long id) {
        Optional<VoteEvent> eventData = voteEventRepository.findById(id);

        if (eventData.isPresent()) {
            VoteEvent _event = eventData.get();
            _event.setUpVotes(_event.getUpVotes()+1);

            return new ResponseEntity<VoteEvent>(voteEventRepository.save(_event), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/voteEvents/downvote/${id}")
    public ResponseEntity<?> downvoteEvent(@PathVariable("id") long id) {
        Optional<VoteEvent> eventData = voteEventRepository.findById(id);

        if (eventData.isPresent()) {
            VoteEvent _event = eventData.get();
            _event.setUpVotes(_event.getUpVotes()-1);

            return new ResponseEntity<VoteEvent>(voteEventRepository.save(_event), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/voteEvents")
    public ResponseEntity<?> createVoteEvent(@RequestBody VoteEvent event) {
        try {
            event.setTimeSlot(event.getTimeSlot().substring(0,10));
            VoteEvent result = new VoteEvent(event.getName(), event.getTimeSlot());
            result.setRequested(event.getRequested());
            result.setDescription(event.getDescription());
            VoteEvent _event = voteEventRepository.save(result);
            return new ResponseEntity<VoteEvent>(_event, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>("Error Message", HttpStatus.EXPECTATION_FAILED);
        }
    }




    @PutMapping("/voteEvents/{id}")
    public ResponseEntity<?> updateVoteEvent(@PathVariable("id") long id, @RequestBody VoteEvent event) {
        Optional<VoteEvent> eventData = voteEventRepository.findById(id);

        if (eventData.isPresent()) {
            VoteEvent _event = eventData.get();
            _event.setName(event.getName());
            _event.setTimeSlot(event.getTimeSlot());
            _event.setUpVotes(event.getUpVotes());
            _event.setRequested(event.getRequested());

            return new ResponseEntity<VoteEvent>(voteEventRepository.save(_event), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/voteEvents/{id}")
    public ResponseEntity<HttpStatus> deleteVoteEvent(@PathVariable("id") long id) {
        try {
            voteEventRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }



}
