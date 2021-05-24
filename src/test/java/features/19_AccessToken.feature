Feature: access token Feature
    Verify the access token 
   
#    @selenium
#    Scenario Outline: Verify the login with valid credentials
#    Given User is on Home page
#    When enter the username "<username>" and password "<password>"
#    Then redirecting on dashboard page
#    
#    Examples: 
#    |username                     |password       |
#    |scantist3                    |Ssunrise1!     |
    
    Scenario: Verify the access token tab
	When Click on access token setting 
	Then validate the access token setting page
	When Enter the access token "QWERTYUI"
	Then Validate the access token
	When Enter the access token "QWERTYUI"
	Then Validate the duplicate access token	
	When Delete the access token
	Then Validate the delete token
	When enter empty token
	Then validate the validation error for blank field
	When enter the invalid token "as#$%"
	Then validate the valiation error for invalid token