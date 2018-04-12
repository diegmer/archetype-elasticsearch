Feature: Basic Elasticsearch feature

#  @elastic @create
#  Scenario Outline: API Create a new Tweet
#    When I request to create a new index "<indexName>" with default values
#    Then I should get "<expectedResult>" result
#
#    Examples:
#      | indexName | expectedResult |
#      | twitter   | CREATED        |


#  @elastic @search
#  Scenario Outline: Search an existing user
#    When I request to search field "<field>" with value "<value>" in index "<indexName>"
#    Then I should get "<expectedResult>" hits
#
#    Examples:
#      | field | value    | expectedResult | indexName |
#      | user  | diegmer  | 1              | twitter   |
#      | user  | diegmer2 | 0              | twitter   |
#      | user  | luigi    | 5              | twitter   |
#
#
  @elastic @search
  Scenario Outline: Search an existing user
    When I request to search with filter "<filter>" in index "<indexName>"
    Then I should get "<expectedResult>" hits

    Examples:
      | filter        | expectedResult | indexName |
      | +luigi -party | 3              | twitter   |
      | +diegmer      | 1              | twitter   |


  @elastic @search
  Scenario Outline: Search an existing user
    When I request to search "<query>" in fields "<fields>" in index "<indexName>"
    Then I should get "<expectedResult>" hits

    Examples:
      | query | fields        | expectedResult | indexName |
      | luigi | user, message | 1              | twitter   |



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

