Feature: Email Notifications
  
  Background:
  Given these users names, their emails and their IDs are contained in the system
# |Customer Name    | Email                |  ID      |
  |Aya              |abtammam2020@gmail.com|   12345  | 
  |yasmeen          |yasmeen@gmail.com     |   54322  |
  |manar            |manar@gmail.com       |   92010  |

And these orders names, their IDs are contained in the system

# |product Name      | Customer ID      |  Product ID|  status    |
  |Cover             |12345             |      1     |  complete  |
  |Cover             |54322             |      2     |  complete  |
  |Carpet            |12345             |      3     |  waiting   |
  |Carpet            |92010             |      4     |in treatment|


   
  @notify

Scenario Outline: send a notify to the customer
  Given a customer with this <email>
  And   an order with this <ID> is marked as complete
  Then the user should receive an email from system with subject "Your order is complete" and  the email body should contain "Dear customer, your order is complete!"
      
 Examples: 
 
        |       email                    |    ID     |
        |  "abtammam2020@gmail.com"      |    1      |