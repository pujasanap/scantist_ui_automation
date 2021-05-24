Feature: Apply Policy Feature
  Verify the apply policy functionality     
    
 
    Scenario: Verify the policy details page
	When Get the policy details page information 
	And Get the policy name
	Then validate the policy name
	
	Scenario: Verify the add rule from Denied category
	When Click on Denied Add rule
	And Create the rule with single attribute for Component
	Then Validate the created rule 
	
	Scenario: Verify the add rule from Flagged category
	When Click on Flagged Add rule
	And Create the rule with single attribute for Component with flagged policy rule
	Then Validate the created rule with flagged policy rule
	
	Scenario: Verify the add rule from Approved category
	When Click on Approved Add rule
	And Create the rule with single attribute for Component with Approved policy rule
	Then Validate the created rule with Approved policy rule
	
	Scenario: Verify the Project page
	When Click on project menu
	Then validate the Project page
	
	Scenario: Verify the source scan 
	When enter the source project name "example-pip-travis" and hit enter
	Then validate the project details
	When click on scan button to scan source code
	And wait until the scan completion of source code 
	Then validate the scan result
	