Feature:
  Verify Response time when using Get Operations

    Scenario: return a random fact endpoint
    Given I Entered the URL
    When  I send a get request
    Then I assert that a random fact has text

   Scenario: return a random fact from facts
    Given I Entered the URL
    When  I send a get request for facts
    Then I assert that a random fact from facts has text
