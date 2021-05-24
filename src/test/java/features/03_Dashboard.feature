Feature: Dashboard Feature
  Verify the login functionality 
  
    Scenario Outline: Verify the login with valid credentials
    Given User is on Home page
    When enter the username "<username>" and password "<password>"
    Then redirecting on dashboard page    
    Examples: 
    |username                     |password       |
    |scantist3                    |Ssunrise1!     |
     
	Scenario: Verify the slidebar menu
	When click on button to close slidebar menu 
	When Click on button to open menu
	Then validate the open menu
	
	Scenario: Verify the username of user
	When Get the username of user
	Then validate the username
	
#	Scenario: Verify the plan of user
#	When Get the plan of user
#	Then validate the user plan
	
	Scenario: Verify the component of account
	When Get the component of account
	Then validate the total component
	
	Scenario: Verify the vulnerability 
	When Get the total count of vulnerability 
	Then validate the total count of vulnerability 
	
	Scenario: Verify the compliance 
	When Get the total count of compliance 
	Then validate the total count of compliance 
	
	Scenario: Verify the project with risk table 
	When Get the project with risk information
	Then Validate the project with risk information
	
	Scenario: Verify the risk over the time graph 
	When Get the risk over the time information
	Then Validate the risk over the time information
	
	
	

	