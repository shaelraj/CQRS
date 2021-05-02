package com.javamonks.user.query.handlers;


import com.javamonks.user.query.dto.UserLookupResponse;
import com.javamonks.user.query.queries.FindAllUsersQuery;
import com.javamonks.user.query.queries.FindUserByIdQuery;
import com.javamonks.user.query.queries.SearchUsersQuery;

public interface UserQueryHandler {
    UserLookupResponse getUserById(FindUserByIdQuery query);
    UserLookupResponse searchUsers(SearchUsersQuery query);
    UserLookupResponse getAllUsers(FindAllUsersQuery query);
}
