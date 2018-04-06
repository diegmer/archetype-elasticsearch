Feature: Basic Elasticsearch feature

  Scenario: Test1
    When I delete all index
#    When I delete index "twitter"
    And I create a new index "twitter" with default values
    And I search "user" "diegmer"
#    And I update the index "twitter" and type "tweet"
