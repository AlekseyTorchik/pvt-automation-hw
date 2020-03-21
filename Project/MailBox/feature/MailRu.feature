Feature: Test Mail.ru functionality

  Scenario: Test positive mail
    Given I am on a main application page
    When I login as a correct user
    And I am on inbox page
    Then is the inbox page
    And I filter only unread email
    Then unread email are shown
    And I send new email to other user
    Then email has been sent
    And I forward incoming email to other user
    Then email has been forward
    And I create new folder in mailbox
    Then new folder is create in menu
    And I move email from inbox to new folder
    Then email has been moved to new folder
    And I move emails from new folder to archive
    Then emails has been moved to archive
    And I move message from archive to trash
    Then emails has been moved to trash
    And I sing out
    Then I am on a main page