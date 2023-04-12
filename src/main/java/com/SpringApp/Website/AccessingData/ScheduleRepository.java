package com.SpringApp.Website.AccessingData;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends CrudRepository<Schedule, Long> {
    List<Schedule> findByDate(String date);
    Optional<Schedule> findByDisplayed(boolean displayed);
    List<Schedule> findByOrderByIdDesc();
}
