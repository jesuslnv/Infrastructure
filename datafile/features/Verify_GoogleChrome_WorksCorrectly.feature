Feature: Verify Google Chrome Works Correctly

  @owner_Jesus.Neira @Smoke
  Scenario Outline: Verify Google Chrome Works Correctly
    Given I Navigate To Google Page
    Then Google Page is Correctly Displayed

  @DEV
    Examples:
      | user                | pass             |
      | Jesus.Neira@ibm.com | Q3VydGVkekAxMQ== |
