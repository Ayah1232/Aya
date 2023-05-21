Feature: Product Information
@p11
Scenario: Empty Information.

  When click on insert button to insert information about product  and flag is 'false'
  Then the field "Category" should be an error
  And the field "name" should be an error
  And the field "Picture" should be an error
  And the field "description" should be an error
  And the field "Isrequiredspecialtreatment" should be an error
 
  
  @p2
Scenario Outline: full correct Information.

   When Customer click on insert button to insert information about product and flag is 'false'
   And he fills 'Category' with <Category>
   And he fills 'name' with <name>
   And he fills 'Picture' with <Picture>
   And he fills 'description' with <description>
   And he fills 'Isrequiredspecialtreatment' with <Isrequiredspecialtreatment>
  
   Then he will fill information sucessfully.
   
     Examples: 
      |Category    | name      | Picture      |     description      | Isrequiredspecialtreatment   |
      | "C"        |  "carpet" | "p.png"      |    "red"              |"false"                       |
      | "G"        |  "carpet" | "p2.jpeg"    |  "green"               |"false"                       |
      | "G"        |  "cover"  |  "p2.png"    |  "green"               |"false"                       |
  @p3
  Scenario Outline: full Information with error  
   When he fills 'Category' with <Category>
   And he fills 'name' with <name>
   And he fills 'Picture' with <Picture>
   And he fills 'description' with <description>
   And he fills 'Isrequiredspecialtreatment' with <Isrequiredspecialtreatment>
 
   Then he will see a 'message' tells that there is something error with his input 
  
    Examples: 
     |Category     | name      | Picture  |     description        | Isrequiredspecialtreatment   |
      | "C"        |  "carpet" | "p1"     | "red"                  |"false"                       |
      | "G"        |  "ground" |  "p2"    |  "green"               |"false"                       |
      | "C"        |  "carpet" | "p3"     | "yellow"               |"false"                       |
      | "C"        |  "carpet" | "p4"     | "blue"                 |"false"                       |
      | "G"        |  "ground" | "p5"     | "red"                  |"false"                       |
      | "C"        |  "carpet" | "p6"     | "red"                  |"false"                       |
