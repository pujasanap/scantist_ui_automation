Feature: Login Feature
    Verify the login functionality
    
#    @selenium 
#    Scenario Outline: Verify the login with valid credentials
#    Given User is on Home page
#    When enter the username "<username>" and password "<password>"
#    Then redirecting on dashboard page
#    Then logout the user account
#    
#    Examples: 
#    |username                     |password       |
#    |scantist3                    |Ssunrise1!     |
    
    Scenario Outline: Verify the login with invalid username and valid password
   
    When enter the invalid username "<invalidusername>" and valid password "<validpassword>"
    Then display error message for invalid credentials
    
    Examples: 
    |invalidusername              |validpassword       |
    |scantist3@.com               |Ssunrise1!          |
    
     
    Scenario Outline: Verify the login with valid username and invalid password
   
    When enter the valid username "<validusername>" and invalid password "<invalidpassword>"
    Then display error message for invalid credentials
    
    Examples: 
    |validusername                |invalidpassword       |
    |scantist3@gmail.com          |Ssunrise              |
    
     
    Scenario Outline: Verify the login with invalid username and invalid password
    
    When enter the invalid username "<invalidusername>" and invalid password "<invalidpassword>"
    Then display error message for invalid credentials
    
    Examples: 
    |invalidusername                |invalidpassword       |
    |scantist3gmail.com             |Ssunrise              |
        
    Scenario: Verify with blank email address and password
    
    When Do not enter email address and password
    And click on signin button
    Then display validation error for email and password
    
    
    Scenario Outline: Verify the login with github
    
    When click on login with github 
    And enter username "<githubUsername>" and password "<githubPassword>"
    Then redirect on project page
    
     Examples: 
    |githubUsername       |githubPassword              |
    |scantist2@gmail.com  |Pooja1234567890             |
    
    
#    Scenario Outline: Verify with not confirm user
#    
#    When enter the not confirm user username"<nonConfirmUser>" and password "<nonConfirmUserPswd>"
#    Then Display message email is not verified
#    When click on resend verification email
#    Then Display Verification email has been sent.
#    
#    Examples: 
#    |nonConfirmUser     |nonConfirmUserPswd     |
#    |scantest           |Ssunrise1!             |    
    
#    Scenario Outline: Verify the login with gitlab
#    
#    When click on login with gitlab
#    And enter gitlab username "<gitlabUsername>" and password "<gitlabPassword>"
#    Then redirect on dashboard page
#    
#     Examples: 
#    |gitlabUsername        |gitlabPassword         |
#    |gitlanuser@gmail.com  |Ssunrise1!             |
    
    

    
   
    
  
    
    