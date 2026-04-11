package com.automation.exercise.data.signup;

import com.automation.exercise.data.gender.Gender;
import com.automation.exercise.utils.TestValueProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SignUpRepository {

    private static volatile SignUpRepository instance;
    protected final Logger logger = LogManager.getLogger(this.getClass());

    private SignUpRepository() {
    }

    public static SignUpRepository get() {
        if (instance == null){
            synchronized (SignUpRepository.class){
                if (instance == null){
                    instance = new SignUpRepository();
                }
            }
        }
        return instance;
    }

    public ISignUp validUser() {

        logger.info("Build valid user.");
        TestValueProvider provider = TestValueProvider.get();

        return SignUp.get()
                .setPassword(provider.getUserPassword())
                .setFirstName(provider.getUserFirstName())
                .setLastName(provider.getUserLastName())
                .setAddress1(provider.getUserAddress())
                .setCountry(provider.getUserCountry())
                .setState(provider.getUserState())
                .setCity(provider.getUserCity())
                .setZipcode(provider.getUserZipcode())
                .setMobileNumber(provider.getUserMobile())
                .setGender(Gender.Mr)
                .setDay(provider.getUserDay())
                .setMonth(provider.getUserMonth())
                .setYear(provider.getUserYear())
                .build();
    }

    public ISignUp userWithEmptyField() {
        logger.info("Build user with empty field.");
        return SignUp.get()
                .setPassword("")
                .setFirstName("")
                .setLastName("")
                .setAddress1("")
                .setCountry(null)
                .setState("")
                .setCity("")
                .setZipcode("")
                .setMobileNumber("")
                .build();
    }
}
