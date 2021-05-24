Feature: Componenent Feature
  Verify the component functionality 

#	@selenium
#   Scenario Outline: Verify the login with valid credentials
#   Given User is on Home page
#    When enter the username "<username>" and password "<password>"
#    Then redirecting on dashboard page    
#    Examples: 
#    |username                     |password       |
#    |scantist3                    |Ssunrise1!     |
    
    Scenario: Verify the component page
	When Click on component menu
	Then validate the component page
	
	Scenario: Verify the component statistics page
	When Get component statistics menu
	
	Scenario: Verify the Component Vulnerability page
	When Get Component Vulnerability menu
	
	Scenario: Verify the Component Licenses page
	When Get Component Licenses menu
	
	Scenario: Verify the Component list with project details
	When search for project "example-pip-travis" to view component details of project 
	And get the project details & click on view button
	Then redirect on component scan result page
	
	Scenario: Verify the Component list with download report
	When search for project "example-pip-travis" to view component details of project 
	And get the project details & click on action button 
	And click on Generate and download pdf report
#	Then validate the downloaded pdf report
	
	Scenario: Verify the download report with csv format
	When click on download csv report
#  Then validate the downloaded csv report

	Scenario: Verify the download report with xml format
	When click on download xml report
	
	Scenario: Verify the download report with json format
	When click on download json report
	
	Scenario: Verify the Component list with by component tab
	When select the tab and search for project "django"
	And get the project details
	And click on Project button and scan result button and view button
	Then validate the project page and scan result page and individual component page
	
	