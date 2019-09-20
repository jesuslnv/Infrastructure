Feature: Verify_TestScenario1

  @owner_Jneira
  Scenario Outline: Verify_TestScenario1
    Given I Login To Belatrix Page with <user> as User and <pass> as Password
    Then Belatrix Page is Correctly Displayed

    @DEV
    Examples:
      | user   | pass                     |
      | Jneira | 26LMoucqQFjcWlmduLFy4w== |
