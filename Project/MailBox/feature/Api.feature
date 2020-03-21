Feature: Test VK.com functionality api

  Scenario: Add post on the user's wall
    Given I create data for API
    When I post on wall
    Then post is create
    And I edit post
    And I delete post
    Then post is delete