Feature: Data Admin Management

  As an admin,
  I want to manage the sector distribution data.

  Background:
    Given I am logged in as an admin
    And I am on the dashboard page
    And I click on the report menu item
    And I should see the overview of platform statistics

  Scenario: Successfully Open Table Section
    When I click on the table menu in debtor distribution
    Then I should see the table

  Scenario: Succesfully Add Data
    When I click on the table menu in debtor distribution
    Then I should see the table
    When I click Tambah Data Penyaluran button
    Then I should see the form
    When I fill out the correct data
    Then I should see successful toast
