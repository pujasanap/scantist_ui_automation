Feature: Integration Feature
    Verify the integrations
   
#    @selenium
#    Scenario Outline: Verify the login with valid credentials
#    Given User is on Home page
#    When enter the username "<username>" and password "<password>"
#    Then redirecting on dashboard page
#    
#    Examples: 
#    |username                     |password       |
#    |scantist3                    |Ssunrise1!     |
    
    Scenario: Verify the Jira integrations 
    When Click on integration setting 
	Then Validate the integration setting page
	Then Validate the status of jira

    