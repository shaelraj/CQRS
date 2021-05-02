package com.javamonks.user.query.handlers;


import com.javamonks.events.UserRegisteredEvent;
import com.javamonks.events.UserRemovedEvent;
import com.javamonks.events.UserUpdatedEvent;

public interface UserEventHandler {
    void on(UserRegisteredEvent event);
    void on(UserUpdatedEvent event);
    void on(UserRemovedEvent event);
}
