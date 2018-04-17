Feature: Basic Elasticsearch feature

  @elastic @create
  Scenario Outline: API Create a new Tweet
    When I request to create a new index "<indexName>" with values for "<typeName>"
      | diegmer          |
      | Hi elasticsearch |
    Then I should get <expectedStatusCode> status code in create
    And I should get "<expectedResult>" result

    Examples:
      | indexName | typeName | expectedStatusCode | expectedResult |
      | twitter   | tweet    | 200                | CREATED        |
#      | blog      | post     | 200                | CREATED        |



  @elastic @update
  Scenario Outline: Update a document
    When I request to update field "<field>" with value "<value>" in index "<indexName>"
    Then I should get <expectedStatusCode> status code in update
    And I should get "<expectedResult>" result

    Examples:
      | field   | value               | indexName | expectedStatusCode | expectedResult |
      | message | I like mario bros 7 | twitter   | 200                | UPDATED        |
      | message | I like mario bros 4 | twitter   | 200                | UPDATED        |


  @elastic @search
  Scenario Outline: Search an existing user by field, value and index
    When I request to search field "<field>" with value "<value>" in index "<indexName>"
    Then I should get <expectedStatusCode> status code in search
    And I should get "<expectedResult>" hits

    Examples:
      | field | value    | expectedResult | indexName | expectedStatusCode |
      | user  | diegmer  | 1              | twitter   | 200                |
      | user  | diegmer2 | 0              | twitter   | 200                |
      | user  | luigi    | 5              | twitter   | 200                |


  @elastic @search
  Scenario Outline: Search by filter
    When I request to search with filter "<filter>" in index "<indexName>"
    Then I should get <expectedStatusCode> status code in search
    And I should get "<expectedResult>" hits

    Examples:
      | filter        | expectedResult | indexName | expectedStatusCode |
      | +luigi -party | 3              | twitter   | 200                |
      | +diegmer      | 1              | twitter   | 200                |


  @elastic @search
  Scenario Outline: Search by fields
    When I request to search "<query>" in fields "<fields>" in index "<indexName>"
    Then I should get <expectedStatusCode> status code in search
    And I should get "<expectedResult>" hits

    Examples:
      | query  | fields        | expectedResult | indexName | expectedStatusCode |
      | mario  | user, message | 2              | twitter   | 200                |
      | master | user, message | 1              | twitter   | 200                |
      | party  | user, message | 1              | twitter   | 200                |


  @elastic @search
  Scenario Outline: Search by bool query
    When I request to search bool query in index "<indexName>" and type "<typeName>"
    Then I should get <expectedStatusCode> status code in search
    And I should get "<expectedResult>" hits

    Examples:
      | indexName | typeName | expectedStatusCode | expectedResult |
      | twitter   | tweet    | 200                | 2              |



  #TODO
  @elastic @delete
  Scenario: Delete a user by value
    When I request to delete a "user" by value "luigi" a index "twitter" type "tweet"
    Then I should get "DELETED" result


  @elastic @delete
  Scenario Outline: Delete a index by index, type and id
    When I request to delete a index "<index>" type "<type>" by ID "1"
    Then I should get "<expectedResult>" result

    Examples:
      | index   | type  | expectedResult |
      | twitter | tweet | DELETED        |


  @elastic @delete
  Scenario Outline: Delete a index by index, type and id
    When I request to delete a index "<index>" type "<type>" by ID "1"
    Then I should get <expectedStatusCode> status code in delete
    And I should get "<expectedResult>" result

    Examples:
      | index   | type  | expectedStatusCode | expectedResult |
      | blog    | post  | 200                | DELETED        |
      | twitter | tweet | 200                | DELETED        |


  @elastic @delete
  Scenario Outline: Delete a index by name
    When I request to delete a index by name "<index>"
    Then I should is acknowledged

    Examples:
      | index   |
      | twitter |
      | blog    |


  @elastic @delete
  Scenario: Delete all index
    When I request to delete all index
    Then I should is acknowledged

