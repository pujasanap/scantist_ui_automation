Feature: Project details Feature
  Verify the project details feature functionality  
 
#   @selenium
#   Scenario Outline: Verify the login with valid credentials
#   Given User is on Home page
#    When enter the username "<username>" and password "<password>"
#    Then redirecting on dashboard page    
#    Examples: 
#    |username                     |password       |
#    |scantist3                    |Ssunrise1!     |
    
    Scenario: Verify the Project page
	When Click on project menu
	Then validate the Project page
	
	Scenario: Verify the source scan 
	Given clear the project name from serach box
	When enter the source project name "example-pip-travis" and hit enter
	And Click on project to go project details page
	Then validate project details page 
	When Add tag to project
	When Add tag to project
	When Click on Action button
	And Click on the scan button
	Then Validate the scan started
	Then Validate the scan completed
	
	Scenario: Verify the scan setting 
	When Click on scheduled scan of source code
#	And validate the compliance policy

	
#	Scenario: Verify to download report
#	When Click on genarate report
#	Then Download json report
#	Then Download xml report
#	Then Download csv report
#	Then Download pdf report
	
#	@upload
#	Scenario: Verify the upload file
#	When Click on upload file 
#	And Enter the file version and upload the file
#	Then Click on Save file
#	Then Validate the version file	
	
	Scenario: Verify the scan history
	When Select the current scan 
	And Click on rescan 
	Then Validate the scan completed

	Scenario: Verify the Compare feature
	And Click on compare button
	Then Validate the compare page 
	
	
	
   
	
	