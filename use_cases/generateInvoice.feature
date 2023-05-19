Feature: Generate Invoice for Customer

Background:

 And the cleaning service includes the following items:
 
    #| Customer Id| Item        | Quantity |size  |
     |    12345   | Carpet      | 4        |  4,3 |#Aya
     |    92010   | cover       | 6        |  3,2 |#manar
     |    92010   | Carpet      | 6        |  7,3 |#manar
      
  And the customer has provided the following delivery information:
    #| Name |  Id     | Address  | City   |
     | Aya  |  12345  | A120     | Nablus | 
     | manar|  92010  | C340     | Nablus | 
     
      
  And these are the price in NIS
  
  #| Item Name    | Price |
   | carpet       | 40    |
   | cover        | 45    |
   
@generate

Scenario Outline: Generate Invoice with Delivery Information and Items to Clean

When  <customer> has requested cleaning services
And he has provided a list of items to be cleaned
And he has provided delivery information
Then an invoice should be generated
 Examples:
 |customer|
 | "Aya"  |
 |"manar" |

#- The customers name and address
#- The delivery information
#- A detailed list of items to be cleaned
#- The agreed-upon price for the cleaning services
#- Any applicable taxes or fees
#- The total amount due