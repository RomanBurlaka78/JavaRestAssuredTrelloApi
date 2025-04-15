@smoke @api @trello
Feature: Trello API Smoke Tests
  As a Trello API user
  I want to verify basic API functionality
  So that I can ensure the core features are working

  Background:
    Given I have valid Trello API credentials
    And I set up the API request with base url "https://api.trello.com/1"

  @positive @board
  Scenario: Create and delete a board
    When I create a new board with name "Smoke Test Board"
#    Then the response status code should be 200
#    And the response should contain a valid board id
    When I delete the created board
#    Then the response status code should be 200

#  @positive @list
#  Scenario: Create a list on a board
#    Given I have created a board named "List Test Board"
#    When I create a list with name "Test List" on the board
#    Then the response status code should be 200
#    And the response should contain a valid list id
#    And the list name should be "Test List"
#    And I delete the parent board
#
#  @positive @card
#  Scenario: Create a card on a list
#    Given I have created a board named "Card Test Board"
#    And I have created a list named "Card Test List" on the board
#    When I create a card with name "Test Card" on the list
#    Then the response status code should be 200
#    And the response should contain a valid card id
#    And the card name should be "Test Card"
#    And I delete the parent board
#
#  @negative @authentication
#  Scenario: Attempt to create board with invalid API key
#    Given I have invalid Trello API credentials
#    When I attempt to create a new board with name "Unauthorized Board"
#    Then the response status code should be 401
#    And the response should contain an unauthorized error message