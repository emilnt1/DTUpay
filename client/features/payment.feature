Feature: payment
  Scenario: Customer wants tokens issued
    Given a valid customer registered at dtupay
    When they want 5 tokens issued
    Then the customer receives 5 tokens

  Scenario: Customer wants to make a payment to a merchant
    Given a valid customer and merchant
    When a customer presents a token to a merchant
    And the merchant makes the payment of 100.00 kr
    Then the payment is registered with 200
    And the token is removed from the customer

  Scenario: Customer wants to make an payment with an invalid amount to a merchant
    Given a valid customer and merchant
    When a customer presents a token to a merchant
    And the merchant makes the payment of -100.00 kr
    Then the payment fails with error code 400
    And the token is removed from the customer

