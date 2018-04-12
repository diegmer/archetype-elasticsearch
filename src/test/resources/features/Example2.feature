Feature: Basic Elasticsearch feature

  @elastic @create
  Scenario Outline: API Create a new Tweet
    When I request to create a new index "<indexName>" with default values
    Then I should get <expectedStatusCode> status code
    And I should get "<expectedResult>" result

    Examples:
      | indexName | expectedStatusCode | expectedResult |
      | twitter   | 200                | CREATED        |


  @elastic @search
  Scenario Outline: Search an existing user
    When I request to search field "<field>" with value "<value>" in index "<indexName>"
    Then I should get <expectedStatusCode> status code
    And I should get "<expectedResult>" hits

    Examples:
      | field | value    | expectedResult | indexName | expectedStatusCode |
      | user  | diegmer  | 1              | twitter   | 200                |
      | user  | diegmer2 | 0              | twitter   | 200                |
      | user  | luigi    | 5              | twitter   | 200                |


  @elastic @search
  Scenario Outline: Search an existing user
    When I request to search with filter "<filter>" in index "<indexName>"
    Then I should get <expectedStatusCode> status code
    And I should get "<expectedResult>" hits

    Examples:
      | filter        | expectedResult | indexName | expectedStatusCode |
      | +luigi -party | 3              | twitter   | 200                |
      | +diegmer      | 1              | twitter   | 200                |


  @elastic @search
  Scenario Outline: Search an existing user
    When I request to search "<query>" in fields "<fields>" in index "<indexName>"
    Then I should get <expectedStatusCode> status code
    And I should get "<expectedResult>" hits

    Examples:
      | query  | fields        | expectedResult | indexName | expectedStatusCode |
      | mario  | user, message | 2              | twitter   | 200                |
      | master | user, message | 1              | twitter   | 200                |
      | party  | user, message | 1              | twitter   | 200                |


  @elastic @search
  Scenario Outline: Search an existing user
    When I request to search bool query in index "<indexName>"
    Then I should get <expectedStatusCode> status code
    And I should get "<expectedResult>" hits

    Examples:
      | query | fields        | expectedResult | indexName | expectedStatusCode |
      | mario | user, message | 2              | twitter   | 200                |




    #TODO
#  @elastic @delete
#  Scenario Outline: Delete a user
#    When I request to delete a "user" with nick "diegmer"
#    Then I should get "<expectedResult>" result
#
#    Examples:
#      | key  | value   | expectedResult |
#      | user | diegmer | DELETED        |


#  @elastic @delete
#  Scenario Outline: Delete a index by index, type and id
#    When I request to delete a index "twitter" type "tweet" by ID "1"
#    Then I should get "<expectedResult>" result
#
#    Examples:
#      | key  | value   | expectedResult |
#      | user | diegmer | DELETED        |


#  @elastic @delete
#  Scenario Outline: Delete a index by name
#    When I request to delete a index by name "<index>"
#    Then I should is acknowledged
#
#    Examples:
#      | index   |
#      | twitter |
#      | blog    |
#
#
#  @elastic @delete
#  Scenario: Delete all index
#    When I request to delete all index
#    Then I should is acknowledged

