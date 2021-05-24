Feature: General setting organization Feature
    Verify the general setting 
   
#    @selenium
#    Scenario Outline: Verify the login with valid credentials
#    Given User is on Home page
#    When enter the username "<username>" and password "<password>"
#    Then redirecting on dashboard page
#    
#    Examples: 
#    |username                     |password       |
#    |scantist3                    |Ssunrise1!     |
    
    Scenario: Verify the general organization setting 
	When Click on organization setting 
	Then validate the general setting page
    When Click on description to update the organization information
 	Then Click to update button
    