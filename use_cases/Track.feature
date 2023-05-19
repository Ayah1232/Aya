
  
  Feature: Order tracking
  Customer want to track his orders So that he can know their status
@track1
  Scenario Outline: Track order status
    Given <Customer> have an order with the order number <number>
    When he check the status of his order
    Then the status should be "waiting"
    
    Examples:
    |Customer|number|
    |"Aya"   | 1    |
    
@track2
  Scenario Outline: Update order status
    Given <Customer> have an order with the order number <number>
    When the order is being processed
    Then the status should be "in treatment"
     Examples:
    |Customer|number|
    |"manar" | 2    |
@track3
  Scenario Outline: Complete order
    Given <Customer> have an order with the order number <number>
    When the order is complete
    Then the status should be "complete"
     Examples:
    |Customer|number|
    |"lana"    | 3    |
  