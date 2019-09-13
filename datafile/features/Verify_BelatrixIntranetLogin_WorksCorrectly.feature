Feature: Verify Belatrix INternal Login Works Correctly

  @owner_Jneira @Smoke
  Scenario Outline: Verify Belatrix INternal Login Works Correctly
    Given I Login To Belatrix Page with <user> as User and <pass> as Password
    Then Belatrix Page is Correctly Displayed
    When I Click on My Files Button; in Belatrix Main Page
    Then My Files Page is Correctly Displayed; in Belatrix Page

    @DEV
    Examples:
      | user   | pass                     |
      | Jneira | 26LMoucqQFjcWlmduLFy4w== |
