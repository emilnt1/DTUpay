Feature: Register
  Scenario: Check that a valid bank is provided
    When I call the bank to get the account
    Then I get an account with firstname "Oliver", lastname "Fiedler" and CPR "0602971234"

  Scenario: Register Customer at DTUPay
    Given a customer with a valid bank id
    Then the customer is registered at DTUPay

  #Scenario: Register Customer at DTUPay with invalid bank id
    #When a customer with an invalid bank id is registered
    #Then the customer is not registered at DTUPay

  Scenario: Register Merchant at DTUPay
    Given a merchant with a valid bank id
    Then the merchant is registered at DTUPay

  #Scenario: Register Merchant at DTUPay with invalid bank id
    #When a merchant with an invalid bank id is registered
    #Then the merchant is not registered at DTUPay





