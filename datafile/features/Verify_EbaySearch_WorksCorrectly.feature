Feature: Verify Ebay Search Works Correctly

  @owner_Jneira @Smoke
  Scenario Outline: Verify Ebay Search Works Correctly
    Given I Login To Ebay Page with <user> as User and <pass> as Password
    Then Ebay Page is Correctly Displayed
    When I Set <searchValue> as search value; in Ebay Page
    When I Press Enter Key; in Ebay Page
    Then Search Result Correctly Displayed; in Ebay Page
    When I Click on First Product in List; in Ebay Page
    Then Product Information Page is Correctly Displayed; in Ebay Page
    When I Click on Description Tab; in Product Information Page; in Ebay Page
    Then Description Tab View is Correctly Displayed; in Product Information Page; in Ebay Page

    @DEV
    Examples:
      | user       | pass       | searchValue |
      | SampleUser | samplePass | Shoes       |
