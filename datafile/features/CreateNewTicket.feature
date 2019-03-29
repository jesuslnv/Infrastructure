Feature: Create New Ticket

  @owner_Jesus.Neira @Smoke2
  Scenario Outline: Create New Ticket
    Given I Login to IBM Cloud with <user> as user and <pass> as password
    #Then IBM Cloud Page is Correctly Displayed
    #When I Navigate to the URL <url>
    #Then Service Support Page is Correctly Displayed
    #When I Click on Create Ticket Button; in Service Support Page
    #Then Create Ticket Page is Correctly Displayed; in Service Support Page
    #When I Click on <ticketType> as Ticket Type; in Create Ticket Page; in Service Support Page
    #When I Select <location> as Location; in Create Ticket Page; in Service Support Page
    #When I Select <customerExpedite> as Customer Expedite; in Create Ticket Page; in Service Support Page
    #When I Set <shortDescription> as Short Description; in Create Ticket Page; in Service Support Page
    #When I Set <description> as Description; in Create Ticket Page; in Service Support Page
    #When I Click on Submit Button; in Create Ticket Page; in Service Support Page
    #Then Service Support Page is Correctly Displayed
    #When I Set <shortDescription> as Filter By; in Service Support Page
    #Then Resulted Ticket List is Correctly Displayed; in Service Support Page

  @DEV
    Examples:
      | user                | pass             | url                                                       | ticketType | location       | customerExpedite | shortDescription       | description      |
      | Jesus.Neira@ibm.com | Q3VydGVkekAxMTE= | http://localhost:3000/managed-solutions/support/#/support | Incident   | REGUS Begonias | Low              | Test Short Description | Test Description |
