Feature: Verify Amazon Search Works Correctly

  @owner_Jneira @Smoke
  Scenario Outline: Verify Amazon Search Works Correctly
    Given I Login To Amazon Page with <user> as User and <pass> as Password
    Then Amazon Page is Correctly Displayed
    When I Set <searchValue> as search value; in Amazon Page
    When I Press Enter Key; in Amazon Page
    Then Search Result Correctly Displayed; in Amazon Page
    When I Click on First Product in List; in Amazon Page
    Then Product Information Page is Correctly Displayed; in Amazon Page
    When I Click on Description Tab; in Product Information Page; in Amazon Page
    Then Description Tab View is Correctly Displayed; in Product Information Page; in Amazon Page

    @DEV
    Examples:
      | user       | pass       | searchValue |
      | SampleUser | samplePass | Shoes       |
