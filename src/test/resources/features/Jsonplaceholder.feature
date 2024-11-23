@SurepayTesting
Feature: Verification of user and respective posts
  As an API consumer
  I want to retrieve posts written by specific users
  So that I can analyze their content

  Background:
    Given the JSONPlaceholder API is available
    #Given start the wiremock for JSONPlaceholder API

  Scenario Outline: Verify posts written by Delphine
    When I search for user with username "<Username>"
    Then the user should be found in the system
    And validate the format of user emailId
    And validate the emailID of the user "<email>"
    When I retrieve all posts written by the user
    Then there should be posts available
    And each post should have valid title and body
    Examples:
    |Username|email|
    |Delphine|Chaim_McDermott@dana.io|
    |Elwyn.Skiles|Telly.Hoeger@billy.biz|
    #|Leopoldo_Corkery|cort              |

  Scenario: Validate post structure
    When I search for user with username "Delphine"
    And I retrieve all posts written by the user
    Then each post should have required fields
    And no. of posts should be greater than 0