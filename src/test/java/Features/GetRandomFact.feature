Feature:
  Verify Response time when using Get Operations


  Scenario: return a random fact
    Given I Entered the end point
    When  I send a get request
    Then I assert that a random fact has text

