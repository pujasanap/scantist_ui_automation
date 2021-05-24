Feature: Knowledge Graph Feature
  Verify the Knowledge Graph functionality 
  
#    @selenium
#    Scenario Outline: Verify the login with valid credentials
#    Given User is on Home page
#    When enter the username "<username>" and password "<password>"
#    Then redirecting on dashboard page    
#    Examples: 
#    |username                     |password       |
#    |scantist3                    |Ssunrise1!     |
    
    Scenario: Verify the knowledge graph page
	When Click on knowledge graph menu
	Then Validate the knowledge graph page
	
	Scenario: Verify the knowledge graph page
	When Get the knowledge graph information
	Then Knowledge graph is displaying
	
	Scenario: Verify the knowledge graph page Most Vulnerable Projects
	When Search knowledge graph Most Vulnerable Projects 
	Then Get the knowledge graph information 
	
	Scenario: Verify the knowledge graph page Critical Projects
	When Search knowledge graph Critical Projects Projects 
	Then Get the knowledge graph information
	
	Scenario: Verify the knowledge graph page Most Common CVEs projects
	When Search knowledge graph Most Common CVEs Projects 
	Then Get the knowledge graph information
	
	Scenario: Verify the knowledge graph page Less Maintained Projects
	When Search knowledge graph Less Maintained Projects 
	Then Get the knowledge graph information
	
	Scenario: Verify the knowledge graph with advanced search
	When Search for projects in advanced search
	Then Get the knowledge graph information
	
	Scenario: Verify the knowledge graph with advanced search
	When Search for vulnerability in advanced search
	Then Get the knowledge graph information
	
	Scenario: Verify the knowledge graph with advanced search
	When Search for Licenses in advanced search
	Then Get the knowledge graph information
	
#	Scenario: Verify the knowledge graph with advanced search
#	When Search for projects in advanced search
#	Then Get the knowledge graph information
	
	Scenario: Verify the knowledge graph with advanced search
	When Search for component in advanced search
	Then Get the knowledge graph information
	
	Scenario: Verify the knowledge graph with advanced search
	When Search for customs in advanced search
	Then Get the knowledge graph information