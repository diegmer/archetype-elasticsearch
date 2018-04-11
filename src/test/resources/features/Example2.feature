Feature: Basic Elasticsearch feature

  @elastic
  Scenario Outline: API Create a new Tweet
    When I request to create a new index "twitter" with default values
    Then I should get "<expectedResult>" result

    Examples:
      | key  | value   | expectedResult |
      | user | diegmer | CREATED        |


  @elastic
  Scenario Outline: Search an existing user
    When I request to search field "<field>" with value "<value>"
    Then I should get "<expectedResult>" hits

    Examples:
      | field | value    | expectedResult |
      | user  | diegmer  | 1              |
      | user  | diegmer2 | 0              |
      | user  | luigi    | 5              |


  @elastic
  Scenario Outline: Delete an existing index
    When I request to delete a index "twitter" type "tweet" by ID "1"
    Then I should get "<expectedResult>" result

    Examples:
      | key  | value   | expectedResult |
      | user | diegmer | DELETED        |


#  Scenario: Delete an existing user
#    #Dado que estoy en el index Twitter y type tweet
#    When I request to delete a "user" by value "diegmer"
##    Then I should get 204 status code

  #    And The value for the "<key>" after post operation should be "<value>"
#    When I request to delete a "user" by value "diegmer"

#    When I request to delete a index by name "twitter"
    #When I request to delete a "user" by value "diegmer"

#    When I request to delete all index
