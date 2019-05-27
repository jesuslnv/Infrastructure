Feature: Verify Google Chrome Works Correctly

  @owner_Jesus.Neira @Smoke
  Scenario Outline: Verify Google Chrome Works Correctly
    Given I Login to IBM Cloud with <user> as user and <pass> as password
    Then IBM Cloud Page is Correctly Displayed

  @DEV
    Examples:
      | user                | pass             |
      | Jesus.Neira@ibm.com | Q3VydGVkekAxMQ== |
