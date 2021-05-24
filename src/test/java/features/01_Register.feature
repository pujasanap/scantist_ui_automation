	Feature: Register Feature
    Verify the registration of user
   
    @selenium
    Scenario Outline: Verify the entered less than four character in username 
    Given User is on Home page
    When user click on create account  
    And enter the less than four character username "<shortUsername>"
    Then display error message for short username
    
    Examples: 
    | shortUsername   |
    | abc             |
    
    
    Scenario: Verify the blank username field
    When enter the blank username " "
    Then display error message for blank username
    
 	Scenario Outline: Verify the add special char in username field
    When enter the special char in username "<invalidUsername>" 
    Then display error message for invalid username
    
    Examples: 
    | invalidUsername  |
    | as@#             |
    
    Scenario Outline: Verify the email field with invalid email id
    When enter the invalid email id "<invalidEmailID>" 
    Then display error message for invalid email id
    
    Examples: 
    | invalidEmailID        |
    | axyz@.com             |
    
    Scenario: Verify the blank email id field
    When enter the blank email id " "
    Then display error message for blank email id field
    
    Scenario Outline: Verify the password field for entering less than 8 character
    When enter the password with less than eight character "<shortPassword>" 
    Then display error message for short password
    
    Examples: 
    | shortPassword        |
    | Ssun                 |
    
    Scenario: Verify the blank password field
    When enter the blank password " "
    Then display error message for blank password field
    
    Scenario: Verify the blank confirm password field
    When enter the blank confirm password " "
    Then display error message for blank confirm password field
    
    Scenario Outline: Verify the password field for unmatched password
    When enter the password "<password>" and confirm password "<confirmPassword>"
    Then display error message for unmatched password
    
    Examples: 
    | password       | confirmPassword     |
    | Ssunrise1!     | Ssunrise            |
    
    Scenario: Verify the create account form all placeholder
    When get the placeholders of all fields
    Then verify the all placeholders
    
    Scenario Outline: Verify the create account
    When Open yopmail in new tab and copy mail paste in email field
    When enter the username and email and password "<password>" and confirm password "<confirmPassword>"
    And click on By signing up, I agree to Scantist's Terms and Conditions.
    And click on create account and click on terms and condition link
    Then display message for successfully account created
    
    Examples: 
    |password         |confirmPassword  |
    |Ssunrise1!       |Ssunrise1!       |
     