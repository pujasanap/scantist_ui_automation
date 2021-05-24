Feature: Componenent Scan Result Feature
  Verify the Component scan result functionality 
  
#  @selenium
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
	
	Scenario: Verify the Component list with project details
	When search for project "example-pip-travis" in searchbox
	And get the project details & click on view button
	
	Scenario: Verify the component statistics 
	When Get component statistics information
	
	Scenario: Verify the Component Vulnerability 
	When Get Component Vulnerability information
	
	Scenario: Verify the Component Licenses 
	When Get Component Licenses information
	
	Scenario: Verify the Cloned Files Analysis
	When Get Cloned Files Analysis information
		
	Scenario: Verify the dependency graph with information 
	When Get dependency graph information
	Then validate the dependency graph information
	
	Scenario: Verify the list of component
	When search for component "django" and enter
	And get the component detail
	And click on vulnerability fiter 
	And click on download button
	And download pdf version
	And download csv version
	And download xml version
	And download json version
	And click on view button
	Then redirect on individual component details page
	
	Scenario: Verify the component with information 
	When Get component information
	Then validate the information of vulnerability of that component 
	Then validate version history
	
	Scenario: Verify the License of details 
	When Get the Allowed License Attribute
	Then validate the Allowed License Attribute
	When Get the Required License Attribute
	Then validate the Required License Attribute
	When Get the Restricted License Attribute
	Then validate the Restricted License Attribute
	
#	Scenario: Verify the sorting of vulnerability
#	When Click on back button
#	Then validate the sorting of functionality
	
	