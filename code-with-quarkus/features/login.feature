Feature: Login
  Scenario: Register customer
    Given a DTU Pay customer with id "TestUser"
    When the customer wants to link their account with a balance of 1000
    Then the bank is contacted and the DTU Pay account is created
    And the customer account is deleted

  Scenario: Register merchant
    Given a DTU Pay Merchant with id "TestMerchant"
    When the merchant want to link their account with a balance of 1000
    Then the bank is contacted and the DTU Pay account is created
    And the merchant account is deleted
