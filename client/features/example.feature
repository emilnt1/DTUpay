Feature: hello service
Scenario: hello service retuns correct answer
	When I call the hello service
	Then I get the answer "Hello RESTEasy"
	
Scenario: hello service getPersonJson returns correct answer
	When I call the hello service to get person via Json
	Then I get a person with name "Hopper" and address "Garden City"
	
Scenario: hello service getPersonXml returns correct answer
	When I call the hello service to get person via Xml
	Then I get a person with name "Hopper" and address "Garden City"
	
Scenario: Updating the person using JSON
	When I update the person with name "Carrie V. Herzig" and address "Harrisburg" using JSON
	And I call the hello service to get person via Json
	Then I get a person with name "Carrie V. Herzig" and address "Harrisburg"

Scenario: Updating the person using XML
	When I update the person with name "Carrie V. Herzig" and address "Harrisburg" using XML
	And I call the hello service to get person via Xml
	Then I get a person with name "Carrie V. Herzig" and address "Harrisburg"
