Feature: Register
  Scenario: Check that a valid bank is provided
    Given a customer with a registered bank account
    And a merchant with a registered bank account
    When I call the bank to get the account
    Then I get an account with firstname "Nikolai", lastname "Stone" and CPR "2803750479"

  Scenario: Register Customer at DTUPay
    Given a customer with a registered bank account
    And a merchant with a registered bank account
    When a customer with a valid bank id wants to register
    Then the customer is registered at DTUPay

  #Scenario: Register Customer at DTUPay with invalid bank id
    #When a customer with an invalid bank id is registered
    #Then the customer is not registered at DTUPay

  Scenario: Register Merchant at DTUPay
    Given a customer with a registered bank account
    And a merchant with a registered bank account
    When a merchant with a valid bank id wants to register
    Then the merchant is registered at DTUPay

  #Scenario: Register Merchant at DTUPay with invalid bank id
    #When a merchant with an invalid bank id is registered
    #Then the merchant is not registered at DTUPay

  Scenario: Deregister Customer at DTUPay
    Given a customer registered at DTUPay
    When the customer requests to deregister
    Then the customer is deleted and is no longer in the system

  Scenario: Deregister Merchant at DTUPay
    Given a merchant registered at DTUPay
    When the merchant requests to deregister
    Then the merchant is deleted and is no longer in the system



