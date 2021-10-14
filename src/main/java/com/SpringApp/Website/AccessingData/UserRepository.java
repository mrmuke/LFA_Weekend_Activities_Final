package com.SpringApp.Website.AccessingData;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmailAddress(String emailAddress);
    User findById(long id);
}