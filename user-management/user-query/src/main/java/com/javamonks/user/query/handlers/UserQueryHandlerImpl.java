package com.javamonks.user.query.handlers;


import com.javamonks.models.User;
import com.javamonks.user.query.dto.UserLookupResponse;
import com.javamonks.user.query.queries.FindAllUsersQuery;
import com.javamonks.user.query.queries.FindUserByIdQuery;
import com.javamonks.user.query.queries.SearchUsersQuery;
import com.javamonks.user.query.repositories.UserRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserQueryHandlerImpl implements UserQueryHandler {
    private final UserRepository userRepository;

    @Autowired
    public UserQueryHandlerImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @QueryHandler
    @Override
    public UserLookupResponse getUserById(FindUserByIdQuery query) {
        Optional<User> user = userRepository.findById(query.getId());
        List<User> users = new ArrayList<>();
        if(user.isPresent()){
            users.add(user.get());
        }

        return  new UserLookupResponse(users);
    }

    @QueryHandler
    @Override
    public UserLookupResponse searchUsers(SearchUsersQuery query) {
        Optional<User> user = userRepository.findByFilterRegex(query.getFilter());
        List<User> users = new ArrayList<>();
        if(user.isPresent()){
            users.add(user.get());
        }
        return new UserLookupResponse(users);
    }

    @QueryHandler
    @Override
    public UserLookupResponse getAllUsers(FindAllUsersQuery query) {
        List<User> users = new ArrayList<>(userRepository.findAll());
        return new UserLookupResponse(users);
    }
}
