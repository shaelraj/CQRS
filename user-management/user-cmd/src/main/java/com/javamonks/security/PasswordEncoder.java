package com.javamonks.security;

public interface PasswordEncoder {
    String hashPassword(String password);
}
