package com.automation.exercise.ui.data.user;

import lombok.Getter;

@Getter
public class UserCredentials {
    private final String email;
    private final String password;

    public UserCredentials(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
