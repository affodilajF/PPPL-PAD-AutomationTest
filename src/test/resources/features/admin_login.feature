Feature: Admin Login


  Scenario: Admin Login
    Given User in the admin login page
    When user submit valid credentials
    Then the user is redirected to the dashboard page
