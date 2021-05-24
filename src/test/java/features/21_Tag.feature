Feature: Tag Feature
    Verify the tag creation
   
#    @selenium
#    Scenario Outline: Verify the login with valid credentials
#    Given User is on Home page
#    When enter the username "<username>" and password "<password>"
#    Then redirecting on dashboard page
#    
#    Examples: 
#    |username                     |password       |
#    |scantist3                    |Ssunrise1!     |
    
    Scenario: Verify the tag tab
	When Click on tag setting 
	Then validate the tag setting page
	When Enter the tag "testTag"
	Then Validate the tag
	When Enter the tag "testTag"
	Then Validate the duplicate tag	
	When Modify the tag
	Then Validate the modified tags
	When Delete the tag
	Then Validate the deleted tag
#	When enter empty tag
#	Then validate the validation error for blank tag field

	Scenario: Verify the Project page
	When Click on project menu
	Then validate the Project page
	
	Scenario: Verify the remove project
	When Click on add project
	When remove project "scantist3/example-pip-travis"
	Then Validate the removed project
	
	Scenario: Verify the remove project
	When remove project "scantist3/jfinal"
	Then Validate the removed project

	@selenium1
	Scenario: Verify the remove project
	When remove project "scantist3/example-python"
	Then Validate the removed project
	