Feature: Login
  Background: User on Login page
    Given user open login page
    Then login page should be loaded

  Scenario Outline: user login with incorrect data
    When user fills "<email>" and "<password>"
    Then user still on login page

    Examples:
    |email|password|
    | | |
    |email|Qwerty4!|
    |email@gmail.com| |
    |!@#$%^|*/!)(*&|

