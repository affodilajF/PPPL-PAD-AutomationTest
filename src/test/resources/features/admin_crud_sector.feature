Feature: Data Admin Management

  As an admin,
  I want to process data and monitor the

  Background:
    Given I am logged in as an admin
    And I am on the dashboard page

  Scenario: Viewing overall platform statistics
    When I click on the report menu item
    Then I should see the overview of platform statistics
    When I explore the statistics details by filter
    Then I should be able to see another data