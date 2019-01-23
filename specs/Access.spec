System Access Tests
=====================
Created by Shane on 19/07/2018

This is a group of tests for testing the access funtionality to the Active Admin Application
Accessibility covers the users ability to signup, login, etc.,
Because the test system does not persist data, make absolutely sure that:
    The signup test parameters have not been used since the server last started up
    The user login / logout parameters exist and are valid i.e. signed-up user/pass

    |username|password|
    |test1|test1|
    |test2|test2|

* User opens the active admin application

## User Sign Up

tags: signup, sign, up

* User clicks on signup link
* User enters "test6" in username field
* User enters "test6@test.com" in email field
* User enters "test6" in password field
* User enters "test6" in confirm password field
* User presses signup button
* User is registered successfully
* User closes the window

## User Log In

tags: login, log, in

* User clicks log in link
* User "test1" enters username
* User enters password "test1"
* User clicks log in
* User closes the window

## User Log Out

tags: logout, log, out

* User clicks log in link
* User <username> enters username
* User enters password <password>
* User clicks log in
* User clicks log out
* User closes the window