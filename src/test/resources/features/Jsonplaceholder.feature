Feature: Verification of user and respective posts
  As an API consumer
  I want to retrieve posts written by specific users
  So that I can analyze their content

  Background:
    Given the JSONPlaceholder API is available

  Scenario: Verify posts written by Delphine
    When I search for user with username "Delphine"
    Then the user should be found in the system
    When I retrieve all posts written by the user
    Then there should be posts available
    And each post should have valid title and body
    And I should see the following post details:
      | title | body |
      | .* | .* |

  Scenario: Validate post structure
    When I search for user with username "Delphine"
    And I retrieve all posts written by the user
    Then each post should have required fields
    And post count should be greater than 0