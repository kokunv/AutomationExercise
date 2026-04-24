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

        return SignUp.builder()
                .password(provider.getUserPassword())
                .firstName(provider.getUserFirstName())
                .lastName(provider.getUserLastName())
                .address1(provider.getUserAddress())
                .country(provider.getUserCountry())
                .state(provider.getUserState())
                .city(provider.getUserCity())
                .zipCode(provider.getUserZipcode())
                .mobile(provider.getUserMobile())
                .gender(Gender.Mr)
                .day(provider.getUserDay())
                .month(provider.getUserMonth())
                .year(provider.getUserYear())
                .build();
    }

    public ISignUp userWithEmptyField() {
        logger.info("Build user with empty field.");
        return SignUp.builder()
                .password("")
                .firstName("")
                .lastName("")
                .address1("")
                .country(null)
                .state("")
                .city("")
                .zipCode("")
                .mobile("")
                .build();
    }
}
