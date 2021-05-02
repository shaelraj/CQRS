package com.javamonks.user.query.repositories;

import com.javamonks.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
