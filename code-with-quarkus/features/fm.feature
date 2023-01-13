Feature: FastMoney api tests

  Scenario: FastMoney create account
    Given i have a valid user "John" "Doe" with cpr "1912981513"
    When i create a new account
    Then i should get a valid account id

  Scenario: FastMoney create account with invalid user
    Given i have an invalid user
    When i create a new account
    Then i should get an error

  Scenario: Request the balance of a valid account
    Given i have a valid account
    And the account has been created
    When i request the balance
    Then i should get the balance
