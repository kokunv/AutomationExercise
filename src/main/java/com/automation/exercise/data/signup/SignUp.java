package com.automation.exercise.data.signup;

import com.automation.exercise.data.gender.Gender;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


interface IPassword {
    IFirstName setPassword(String password);
}

interface IFirstName {
    ILastName setFirstName(String firstName);
}

interface ILastName {
    IAddress setLastName(String lastName);
}

interface IAddress {
    ICountry setAddress1(String address);
}

interface ICountry {
    IState setCountry(String country);
}

interface IState {
    ICity setState(String state);
}

interface ICity {
    IZipcode setCity(String city);
}

interface IZipcode {
    IMobileNumber setZipcode(String zipcode);
}

interface IMobileNumber {
    IBuild setMobileNumber(String number);
}

interface IBuild {
    IBuild setGender(Gender gender);
    IBuild setDay(String day);
    IBuild setMonth(String month);
    IBuild setYear(String year);

    ISignUp build();
}
@Data
public class SignUp implements  IPassword, IFirstName, ILastName, IAddress, ICountry, IState, ICity, IZipcode, IMobileNumber, IBuild, ISignUp {

    protected final Logger logger = LogManager.getLogger(this.getClass());

    private Gender gender;
    private String password;
    private String day;
    private String month;
    private String year;
    private String firstName;
    private String lastName;
    private String address1;
    private String country;
    private String state;
    private String city;
    private String zipCode;
    private String mobile;

    private SignUp(){}

    public static IPassword get() {
        return new SignUp();
    }

    @Override
    public IFirstName setPassword(String password) {
        this.password = password;
        logger.trace("Builder: password set");
        return this;
    }

    @Override
    public ILastName setFirstName(String firstName) {
        this.firstName = firstName;
        logger.trace("Builder: firstname set: {}", firstName);
        return this;
    }

    @Override
    public IAddress setLastName(String lastName) {
        this.lastName = lastName;
        logger.trace("Builder: lastname set: {}", lastName);
        return this;
    }

    @Override
    public ICountry setAddress1(String address1) {
        this.address1 = address1;
        logger.trace("Builder: address set: {}", address1);
        return this;
    }

    @Override
    public IState setCountry(String country) {
        this.country = country;
        logger.trace("Builder: country set: {}", country);
        return this;
    }

    @Override
    public ICity setState(String state) {
        this.state = state;
        logger.trace("Builder: state set: {}", state);
        return this;
    }

    @Override
    public IZipcode setCity(String city) {
        this.city = city;
        logger.trace("Builder: city set: {}", city);
        return this;
    }

    @Override
    public IMobileNumber setZipcode(String zipCode) {
        this.zipCode = zipCode;
        logger.trace("Builder: zipcode set: {}", zipCode);
        return this;
    }

    @Override
    public IBuild setMobileNumber(String mobile) {
        this.mobile = mobile;
        logger.trace("Builder: mobile number set: {}", mobile);
        return this;
    }

    @Override
    public IBuild setDay(String day) {
        this.day = day;
        logger.trace("Builder: day set: {}", day);
        return this;
    }

    @Override
    public IBuild setMonth(String month) {
        this.month = month;
        logger.trace("Builder: month set: {}", month);
        return this;
    }

    @Override
    public IBuild setYear(String year) {
        this.year = year;
        logger.trace("Builder: year set: {}", year);
        return this;
    }

    @Override
    public IBuild setGender(Gender gender) {
        this.gender = gender;
        logger.trace("Builder: gender set: {}", gender);
        return this;
    }

    @Override
    public ISignUp build() {
        logger.debug("SignUp object successfully built for user: {} {}", this.firstName, this.lastName);

        return this;
    }





}






























