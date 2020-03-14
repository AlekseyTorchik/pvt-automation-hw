Feature: Login Mail.ru

  Scenario: Positive login Mail.ru
    Given I am on a main application page
    When I take screenshot login page
    Then I compare screenshots
    And I login as a correct user
    And I take screenshot mail window
    Then I compare screenshots