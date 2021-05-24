Feature: Project Feature
  Verify the Project functionality 
  
#    @selenium
#    Scenario Outline: Verify the login with valid credentials
#    Given User is on Home page
#    When enter the username "<username>" and password "<password>"
#    Then redirecting on dashboard page    
#    Examples: 
#    |username                    |password        |
#    |scantist3                   |Ssunrise1!      |
    
    Scenario: Verify the Project page
	When Click on project menu
	Then validate the Project page
	
	Scenario: Verify the overview section
	When Get the overview section information
	Then validate the overview section
	
	Scenario: Verify the add project
	When Click on add project
	When Add project
	Then Validate the added project

	Scenario: Verify the add project
	When Add project "jfinal"
	
	Scenario: Verify the add project
	When Add project "example-python"
	
	Scenario: Verify the source scan 
	When Click on project menu
	Then validate the Project page
	When enter the source project name "example-pip-travis" and hit enter
	Then validate the project details
	When click on scan button to scan source code
	And wait until the scan completion of source code 
	Then validate the scan result
	When enter the source project name "jfinal" and hit enter
	When click on scan button to scan source code
	When enter the source project name "example-python" and hit enter
	When click on scan button to scan source code
	
	
	
#	@binary
#	Scenario: Verify the Binary scan 
#	When enter the binary project name "NestedZipFile" and hit enter
#	Then validate the project details
#	When click on scan button
#	And wait until the scan completion 
#	Then validate the scan result
#	
#	@group
#	Scenario: Verify the group scan 
#	When select the projects to group scan
#	Then validate the projects details	
