Feature: Member invitation Feature
    Verify the Member inviation 
   
#    @selenium
#    Scenario Outline: Verify the login with valid credentials
#    Given User is on Home page
#    When enter the username "<username>" and password "<password>"
#    Then redirecting on dashboard page
#    
#    Examples: 
#    |username                     |password       |
#    |scantist3                    |Ssunrise1!     |
    
    Scenario: Verify the member invitation tab
	When Click on member setting 
	Then validate the member setting page
    When enter the "scantist4@gmail.com" member name
 	Then Click on invite member button
