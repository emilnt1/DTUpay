Feature: Report
  Scenario: Customer wants to receive a report of their transactions
    Given a valid customer and a merchant with existing transactions
    When the customer requests a list of their transactions
    Then a list of the customers transactions is provided
    And the list consists of the correct transactions for the customer

  Scenario: Merchant wants to receive a report of payments made to them
    Given a valid customer and a merchant with existing transactions
    When a merchant requests a list of their transactions
    Then a list of a merchants transactions is provided
    And the list consists of the correct transactions for the merchant

  Scenario: Manager recieves transaction list
    Given a valid customer and a merchant with existing transactions
    When a manager requests a report of all transactions from DTUPay
    Then a list of all transactions is provided
