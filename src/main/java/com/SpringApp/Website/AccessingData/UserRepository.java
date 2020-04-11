package com.SpringApp.Website.AccessingData;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByEmailAddress(String emailAddress);
    User findByUserName(String userName);
}
