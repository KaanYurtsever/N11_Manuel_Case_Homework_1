Feature: Go to the n11 stores page and control comments count for chosen store

  @commentcount
  Scenario: Control comment count for chosen store
    Given User opens the browser and goes to the stores page from home page
    When All stores written an excel file
    And User choose the store which was wanted
    Then User sees comment count of store