@SurepayTesting
Feature: Verification of user and respective posts
  As an API consumer
  I want to retrieve posts written by specific users
  So that I can analyze their content

  Background:
    Given the JSONPlaceholder API is available

    #IF service is unavailable,wiremock can also be an alternative - This is an experimental feature
    #Given start the wiremock for JSONPlaceholder API

  @FunctionalTesting @IntegrationTesting
  Scenario Outline:"<TestCaseID>"_Validating of email format in the post for the user
    When I search for user with username "<Username>"
    Then the user should be found in the system
    And validate the emailID of the user "<email>"
    When I retrieve all posts written by the user
    Then there should be posts available
    And each post should have valid title and body
    And retrieve comments for each post
    Then validate email in each comment section
    Examples:
    |TestCaseID|Username|email|
    |TC-01|Delphine|Chaim_McDermott@dana.io|
    |TC-02|Elwyn.Skiles|Telly.Hoeger@billy.biz|
    |TC-03|Natarajan|Rey.Padberg@karina.biz|
    |TC-04|  NULL   |Null@test.com         |

  @UsersPostsTesting @SmokeTesting
  Scenario: Validate post structure
    When I search for user with username "Delphine"
    And I retrieve all posts written by the user
    Then each post should have required fields
    And no. of posts should be greater than 0