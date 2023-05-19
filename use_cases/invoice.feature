Feature: Statistics for Deliveries

Background: 

Given the following deliveries have been made:

#| Item Name   | Quantity | Price|
 | carpet      | 34       | 40   |
 | cover       | 58       | 45   |

And the following payments have been received:

#| Payment Type | Amount |
| Cash         | 750   |
| Credit card  | 123   |

And the following debts:

#| Customer id   |  Amount |
|   12345        |  50    |
|   92010        |  100   |

 @invoice
 
Scenario: Calculate total delivered items, cash, paid, and debts

When I calculate the total delivered items
And  I calculate the total cash 
And  I calculate the total paid
And  I calculate the total debts
Then a statistics should be generated