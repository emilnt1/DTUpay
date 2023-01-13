Feature: Register
  Scenario: Register Customer
    When "Oliver" "Fiedler" with CPR "0602971234" wants an account
    Then DTUpay creates an user at FM
    And DTUpay returns an error message