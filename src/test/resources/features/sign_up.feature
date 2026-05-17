Feature: Sign in

  Background: User on Sign Up page
    Given user open sign up page
    Then sign up page should be loaded

  Scenario: User can sign up successfully
    When user sign up with name "New"
    And user fills registration form and create account
    Then account created message should be visible
    When user continues to home page
    Then logged "New" should be visible

  Scenario: User cannot register with existing email
    When user registers with valid data
    And user logs out
    And user tries to register with the same email
    Then account already exists message should be displayed
    And user should not be logged in
    And user should stay on login page

  Scenario: User can't register with empty field
    When user sign up with name "MikeTyson"
    And user keep empty fields and click create account
    Then user can't register with empty field
    And validation message should be displayed
    And user should stay on sign up page

