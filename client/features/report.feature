Feature: Report
  Scenario: Customer wants to receive a report of their transactions
    Given a valid customer
    When the customer requests a list of their transactions
    Then a list of the customers transactions is provided

  Scenario: Merchant wants to receive a report of payments made to them
    Given a valid merchant
    When a merchant requests a list of their transactions
    Then a list of a merchants transactions is provided

  Scenario: Manager recieves transaction list
    When a manager requests a report of all transactions from DTUPay
    Then a list of all transactions is provided
