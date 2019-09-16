Feature: Verify_TestScenario3

  @owner_Jneira @Smoke
  Scenario Outline: Verify_TestScenario3
    Given I Login To Belatrix Page with <user> as User and <pass> as Password
    Then Belatrix Page is Correctly Displayed

    @DEV
    Examples:
      | user   | pass                     |
      | Jneira | 26LMoucqQFjcWlmduLFy4w== |
