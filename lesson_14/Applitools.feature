Feature: Test Mail.ru by applitools

  Scenario: Test positive mail
    Given I am on a main application page
    When I take screenshot login page
    Then I compare screenshots
    And I login as a correct user
    And I take screenshot inbox window
    Then I compare screenshots
    And I create new letter
    And I take screenshot new letter window
    Then I compare screenshots
    And I send letter
    And I take screenshot send notification window
    Then I compare screenshots
