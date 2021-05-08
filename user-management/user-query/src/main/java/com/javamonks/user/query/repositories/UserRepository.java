package com.javamonks.user.query.repositories;

import com.javamonks.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByFilterRegex(String filter);
}
