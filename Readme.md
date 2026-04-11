
📘 Test Automation Framework

This project contains UI automated tests for:
👉 https://automationexercise.com/

🎯 Project Goal

The goal of this project is to build a scalable and flexible test automation framework using:

🧰 Technology Stack
Automation Tool: Selenium WebDriver
Language: Java
Build Tool: Maven
Test Runner: TestNG
Assertion Library: SoftAssert
Logging: Log4j2

🏗️ Design Patterns Used
Page Object Model (POM) – separates UI logic
Page Factory – initializes elements
Singleton – manages WebDriver instance, testValueProvider, signInRepository
Builder Pattern – creates test data (e.g. SignUp)


📁 Project Structure
```
src
 ├── main
 │   ├── driver        → WebDriver setup & management
 │   ├── pages         → Page Objects & business logic
 │   ├── components    → Header, Footer, reusable UI parts
 │   ├── data          → Test data models (Builder pattern), email generator
 │   ├── utils         → Helpers (TestValueProvider, config reader)
 │
 ├── test
 │   ├── tests         → Test classes
 │   ├── runners       → TestNG runners
 │
 ├── resources
 │   ├── configs       → Environment properties
 │   ├── testng_suits  → XML suites (Smoke, Regression)
 │   ├── driver        → msedgedriver.exe
 │   ├── logging       → Logging configuration
```
⚙️ Key Features
```
✅ Multi-browser support
✅ Environment configuration 
      Supports multiple environments via .properties file
✅ Flexible configuration (CI-ready)       
✅ TestNG Suites
✅ Logging (Log4j2)
✅ Screenshot on Failure
```

🚀 How to Run
* Via IDE (IntelliJ IDEA)
*   Right-click on src/test/java and select Run 'All Tests'.

* Via Maven Terminal
*    mvn clean test

  Run with parameters:

   mvn clean test -Dbrowser=chrome -Dsuite=smoke -Denv=qa
