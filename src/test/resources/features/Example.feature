Feature: Basic Elasticsearch feature

#  Scenario: Delete an existing index
#    When I request to delete a index by name "twitter"
##    Then I should get "204" status code


  Scenario Outline: Create a new Tweet
    When I request to create a new index "twitter" with default values
    Then I should get "<expectedResult>" result
#    And The value for the "<key>" after post operation should be "<value>"
#    When I request to delete a "user" by value "diegmer"

    Examples:
      | key  | value   | expectedResult |
      | user | diegmer | CREATED        |


  Scenario: Delete an existing user
    #Dado que estoy en el index Twitter y type tweet
    When I request to delete a "user" by value "diegmer"
#    Then I should get 204 status code


  Scenario: Test1
    When I delete all index
    When I delete index "twitter"
#    And I create a new index "twitter" with default values
#    And I insert new tweet "Hello" for user "mario" in index "index"
    And I search "user" "diegmer"
    #And I search "user" "diegmer2"
#    And I search "user" "mario"
    And I delete "user" "diegmer"
    #And I update the index "twitter" and type "tweet"
