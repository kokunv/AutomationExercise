 Feature: Sign in

   Background: User on home page

     Scenario: User can sign up successfully
       Given user open sign up page
       Then sign up page should be loaded

       When user sign up with "New" and "email1212@gmail.com"
       And user fills registration form and create account
       Then account created message should be visible

       When user continues to home page
       Then logged "New" should be visible
