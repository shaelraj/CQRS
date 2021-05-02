package com.javamonks.user.query.dto;


import com.javamonks.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserLookupResponse {
    private List<User> users;

    private String message;

    public UserLookupResponse(User user) {
        this.users = new ArrayList<>();
        this.users.add(user);
    }

    public UserLookupResponse(String message) {
        this.message = message;
    }
}
