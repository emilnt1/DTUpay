Feature: hello service
	Scenario: hello service retuns correct answer
		When I call the hello service
		Then I get the answer "Hello from REST"

	Scenario: hello from merchant
		When I call the hello service from merchant
		Then I get the answer "Hello from REST"

	Scenario: hello from customer
		When I call the hello service from customer
		Then I get the answer "Hello from REST"