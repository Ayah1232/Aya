Feature: logIn 
  Background:
  Given these customers with there information contained in the system
    
 
#| name    |  phone       | address   |       email           | id   | password |state|
 | Aya     |  09376       | A120      |abtammam2020@gmail.com |12345 | 1AsO45   |false|
 | yasmeen |  0388673923  | B230      |yasmin@gmail.com       |54322 | 12342Aa  |false|
 | manar   |  8876209378  | C340      |manar2019@gmail.com    |92010 |Ffffff123 |false|
     
  
  
  
  
@scenario1
  Scenario Outline: logIn successfully
  the customer want to logIn to his profile
  
    Given  name field is empty
    And password field is empty
    When he fill 'email' with <email>
    And  he fill 'password' with <Password>
    Then his profile will open 

    Examples: 
      | email                    | Password        | 
      | "manar2019@gmail.com"    |     "Ffffff123" |
      | "abtammam2020@gmail.com" |     "1AsO45"    |

      
 @scenario2
 
 Scenario Outline: logIn error
  
    Given  name field is empty
    And password field is empty
    When he fill 'email' with <email>
    And  he fill 'password' with <Password>
    Then a 'message' should appear said that there is something error with your input

    Examples: 
      | email     | Password | 
      | "manarr"  |     "897"|
      | "aya"     |     "1232"|
    
  @scenario3
  
  Scenario: empty fields
  Given  Customer click on login and flag is 'false'
  Then field 'name' should be error
  And  field 'password ' should be error
 And an error message will show 'please fill the request fields' 
  
  
   
    
    
    
    