Feature: Links

  Scenario: returns links
    Given App is running on port 8090
    When the client requests a list of links
    Then the response is the list containing two links
    And first link is http://google.com
    And second link is http://spring.io