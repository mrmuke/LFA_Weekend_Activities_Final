package com.SpringApp.Website.AccessingData;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventRepository extends CrudRepository<Event, Long> {

    List<Event> findByName(String name);
    List<Event> findByTimeSlot(String timeSlot);


}
