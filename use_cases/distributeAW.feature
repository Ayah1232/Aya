
Feature: Order distribution

  Scenario: Distribute orders to available workers
    Given a worker named "Alice" is available
    And a worker named "Bob" is available
    And an order with ID "1234" and customer name "John" is received
    When the order is added to the distribution queue
    Then the order should be assigned to worker "Alice"
    And worker "Bob" should still be available