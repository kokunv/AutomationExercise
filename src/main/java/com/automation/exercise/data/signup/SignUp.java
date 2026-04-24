package com.automation.exercise.data.signup;

import com.automation.exercise.data.gender.Gender;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUp implements ISignUp {

    private final Gender gender;
    private final String password;
    private final String day;
    private final String month;
    private final String year;
    private final String firstName;
    private final String lastName;
    private final String address1;
    private final String country;
    private final String state;
    private final String city;
    private final String zipCode;
    private final String mobile;


}






























