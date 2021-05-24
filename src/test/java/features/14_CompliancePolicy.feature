Feature: CompliancePolicy Feature
  Verify the CompliancePolicy functionality 
     
  
    Scenario: Verify the CompliancePolicy page
	When Click on CompliancePolicy menu
	Then validate the CompliancePolicy page
	
	Scenario: Verify the Compliance policy details  
	When enter the project name "example-pip-travis" and hit enter
	Then validate the compliance policy details
	When Click on result button
	When enter the project name "example-pip-travis" and hit enter
	And Clcik on view button