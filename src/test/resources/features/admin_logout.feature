Feature: Admin Logout Process

  As an admin,
  I want to log out from the dashboard,
  So that I can ensure my session is secure and I am redirected appropriately.

  Background:
    Given I am logged in as an admin
    And I am on the dashboard page

  Scenario: Successful logout
    When I click on my profile avatar
    Then a tooltip should be displayed
    When I click on the logout button
    Then I should be redirected to the landing page
