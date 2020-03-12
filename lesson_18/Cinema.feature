Feature: Test search available cinema on Yandex.by

  Scenario: Test available cinema
    Given I am on a main page
    When I go to poster page
    And I search movie for tonight
    And I take screenshot of result
    Then I check available cinema for tonight