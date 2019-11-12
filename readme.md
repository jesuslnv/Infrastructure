[![Stargazers][stars-shield]][stars-url]
[![Travis][travis-shield]][travis-url]
[![Sonar][sonar-shield]][sonar-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]

#Selenium Control
This is a base project that uses "SeleniumControl" as Library to run Automated Tests and Penetration Testing.

#Getting Started
This project can run using the file **MainRunner** located in the package runner, this runner receives the next **arguments**:

|Function Name                                      |Action Performed|
|---                                                |---|
|-t                                                 |Is the TAG to be search in the datafile list|
|-t 'not @Chrome'                                   |Command to execute all the tests using **Google Chrome**|
|-t 'not @OWASP'                                    |Command to execute all the tests using **Owasp Zap**|
|-p json:target/Infrastructure/Infrastructure.json  |Is a TAG where the **JSON** report will be written|
|-p html:target/Infrastructure/html-report          |Is a TAG where the **HTML** report will be written|

#Class/Package Distribution
In this project exists different pre-defined distributions that you must follow to maintain the order and legibility.

##Package "datafile"
This folder is necessary to store all your features, also inside this folder you can create other folders with different names to order your tests.

##Class "MainRunner"
This class is in charge to run all your tests, here you can find the parameters previously defined to run your features.

##Class "WebDriverManager"
This class is where all the logic with the webdriver is stored. Here is a list of each function defined:

|Function Name                      |Action Performed|
|---                                |---|
|getWebDriver                       |Configures the **webdriver** for the first time or returns the previously configured one |
|closeDriver                        |Closes the **webdriver** and takes an screenshot to the last page viewed before it closes |
|generalWebDriverConfiguration      |Here is configured all the parameters and properties for the **webdriver**, also here is configured the "Owasp Zap" and the Browser Type (Chrome or Firefox) |
|runPenetrationTesting              |Here is configured the base parameters to run the **Penetration Testing** using the **SeleniumControl** library |
|waitForSuccessfulConnectionToZap   |This function detects the successful connection to the previously configured **Owasp Zap** and returns "*true*" or "*false*" |
|configSeleniumGrid                 |This function configures the base **Selenium Grid** |
|getNodeIP                          |This is a function used by the **configSeleniumGrid** to determine where is the test running in the Selenium Grid |

##Class "ProjectSteps" 
In this class is located all the step definitions with its LINK to **Cucumber Features**. 

##Package "Pages"
Here is located the class distribution for pages and views. The order established here is to easy maintain the tests and get a basic knowledge about the location of each page.
In this example we can check the **EbayPage** class where contains different functions using the "**SeleniumControl**" library to call a "ButtonControl" or "SelectControl".
This elements are calling another "Page" or "View" in the **Main Page**, so here you declare a new "*CLASS*" with the name of that view, in this case **ProductInformationPage**,
this view **extends** from the previous page **EbayPage** to avoid "*multi-iframe*" dependencies.
The "*multi-iframe*" is a common problem for many test developers, so with this order you can create an internal dependency between classes to fight against it.
Is important to notice that each **view** is created inside a "**package**" with the same name of the previous corresponding Page or View. Here is the proposed example:

```sh
- EbayPage (Class)
- ebay (Package)
  - ProductInformationView (Class)
  - productInformation (Package)
    - DescriptionTabView (Class)
```

# Dependencies
This project depends on different components but the principal is the "SeleniumControl" library that must be installed in your "**maven**" repository. This project includes the next maven dependency: 

```xml
<dependency>
    <groupId>SeleniumControl</groupId>
    <artifactId>SeleniumControl</artifactId>
    <version>1.0.0</version>
</dependency>
```
This dependency references a previously installed library. For more information visit the [SeleniumControl](https://github.com/jesuslnv/SeleniumControl) github.


<!-- LINKS -->
[stars-shield]: https://img.shields.io/github/stars/jesuslnv/Infrastructure.svg
[stars-url]: https://github.com/jesuslnv/Infrastructure/stargazers
[travis-shield]: https://travis-ci.com/jesuslnv/Infrastructure.svg?branch=master
[travis-url]: https://travis-ci.com/jesuslnv/Infrastructure
[sonar-shield]: https://sonarcloud.io/api/project_badges/measure?project=jesuslnv_Infrastructure&metric=alert_status
[sonar-url]: https://sonarcloud.io/dashboard?id=jesuslnv_Infrastructure
[license-shield]: https://img.shields.io/badge/License-MIT-green.svg
[license-url]: https://github.com/jesuslnv/Infrastructure/blob/master/LICENSE
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?logo=linkedin&colorB=1E5799
[linkedin-url]: https://pe.linkedin.com/in/jesus-luis-neira-vizcarra-27b4b31a