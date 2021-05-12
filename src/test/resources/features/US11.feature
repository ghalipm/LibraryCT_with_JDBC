Feature: Data comparison feature

  Scenario: DB data and UI data matching

    Given the user librarian is on the homepage
    Then the user should see that the numbers from DB and UI matches for:
      | Users          |
      | Books          |
      | Borrowed Books |