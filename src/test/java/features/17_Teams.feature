Feature: Teams Feature
    Verify the teams creation 
   
#    @selenium
#    Scenario Outline: Verify the login with valid credentials
#    Given User is on Home page
#    When enter the username "<username>" and password "<password>"
#    Then redirecting on dashboard page
#    
#    Examples: 
#    |username                     |password       |
#    |scantist3                    |Ssunrise1!     |
    
    Scenario: Verify the team setting tab
	When Click on team setting 
	Then validate the team setting page
    When Click the "testTeam" team name
 	Then Click on create team button
 	Then Delete the team