package com.SpringApp.Website.AccessingData;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ScheduleRepository extends CrudRepository<Schedule, Long> {
    List<Schedule> findByDate(String date);
    Schedule findFirstByOrderByIdDesc();
}
