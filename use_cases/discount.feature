

Feature: discount

Background:
Given these customers and those information
#name |totalpayed|frequently|
|lana |   2000   | 11       |
|omar |   7000   | 14       |
|aya  |   1200   | 23       |
|yara |    360   | 19       |
|manar|   4000   |  3       |
| ali |   120    |  1       |
  @discount_tag


Scenario Outline: Give the customer discount
    Given the <customer> has used the service 'frequently'
    And he has spent more than 400 NIS
    When he requests a discount
    Then the discount rate should be 10 %
   
   Examples:  
    |customer  |
    | "lana"   |
    | "omar"   |
    | "aya"    |
     
     
 @discount_tag2
 
    Scenario Outline: Give the customer discount
    When the <customer> hasnt used the service so many
    And the customer hasnt spent more than 400 NIS
    Then he will not get a discount
    
     Examples:  
    |customer  |
    | "yara"   |
    | "manar"  |
    | "ali"    |
    
    
    
 