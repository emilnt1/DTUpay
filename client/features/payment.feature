Feature: payment
  Scenario: Customer wants tokens issued
    Given a valid customer registered at dtupay
    When they want 5 tokens issued
    Then the customer receives 5 tokens

  Scenario: Customer wants to make a payment to a merchant
    Given a valid customer and merchant
    When a customer wants to make a payment to a merchant
    Then the customer presents a token to the merchant
    And the merchant makes the payment



