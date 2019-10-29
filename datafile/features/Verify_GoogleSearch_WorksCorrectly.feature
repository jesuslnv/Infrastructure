Feature: Verify Google Search Works Correctly

  @owner_Jneira @Smoke
  Scenario Outline: Verify Google Search Works Correctly
    Given I Login To Google Page with <user> as User and <pass> as Password
    Then Google Page is Correctly Displayed
    When I Set <searchValue> as search value; in Google Page
    When I Press Enter Key; in Google Page
    Then Search Result Correctly Displayed; in Google Page
    When I Click on Images Button; in Google Page
    Then Images Page is Correctly Displayed; in Google Page
    When I Click on First Image Found; in Images Page; in Google Page
    Then Image Preview Page is Correctly Displayed; in Google Page

    @DEV
    Examples:
      | user       | pass       | searchValue   |
      | SampleUser | samplePass | Cucumber Java |
