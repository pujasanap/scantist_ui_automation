Feature: Create Policy Feature
  Verify the Create Policy functionality 
 
#  @selenium
#   Scenario Outline: Verify the login with valid credentials
#   Given User is on Home page
#    When enter the username "<username>" and password "<password>"
#    Then redirecting on dashboard page    
#    Examples: 
#    |username                     |password       |
#    |scantist3                    |Ssunrise1!     |
    
    Scenario: Verify the Organization tab
	When Click on Organization menu
	Then validate the organization tab opened 
	
#	Scenario: Verify the Organization policy table
#	When Get the Organization policy information
#	Then Displaying organization policy information
		 
	Scenario: Verify the duplicate policy
	
#	When Redirect on create policy popup
	When Enter policy name "testPolicy8"
	And Click on Create Policy
	Then Validate the duplicate policy 
	
#	Scenario: Verify the Create policy form
#	When Click on Create Policy
#	Then Validate the error message for policy name and description
	
#	Scenario: Verify the default policy 
#	When Click to select default policy 
#	Then validate the tooltip of default policy

	Scenario: Verify the Create policy
	When Enter policy name 
	And Click on Create Policy	
	Then Displaying organization policy information
	Then Click on new policy to assign rule
	
	
	

	
	
	