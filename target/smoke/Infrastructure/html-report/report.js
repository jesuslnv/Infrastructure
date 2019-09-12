$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("datafile/features/Verify_BelatrixIntranetLogin_WorksCorrectly.feature");
formatter.feature({
  "line": 1,
  "name": "Verify Belatrix INternal Login Works Correctly",
  "description": "",
  "id": "verify-belatrix-internal-login-works-correctly",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "line": 4,
  "name": "Verify Belatrix INternal Login Works Correctly",
  "description": "",
  "id": "verify-belatrix-internal-login-works-correctly;verify-belatrix-internal-login-works-correctly",
  "type": "scenario_outline",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 3,
      "name": "@owner_Jneira"
    },
    {
      "line": 3,
      "name": "@Smoke"
    }
  ]
});
formatter.step({
  "line": 5,
  "name": "I Login To Belatrix Page with \u003cuser\u003e as User and \u003cpass\u003e as Password",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "Belatrix Page is Correctly Displayed",
  "keyword": "Then "
});
formatter.step({
  "line": 7,
  "name": "I Click on My Files Button; in Belatrix Main Page",
  "keyword": "When "
});
formatter.step({
  "line": 8,
  "name": "My Files Page is Correctly Displayed; in Belatrix Page",
  "keyword": "Then "
});
formatter.examples({
  "line": 11,
  "name": "",
  "description": "",
  "id": "verify-belatrix-internal-login-works-correctly;verify-belatrix-internal-login-works-correctly;",
  "rows": [
    {
      "cells": [
        "user",
        "pass"
      ],
      "line": 12,
      "id": "verify-belatrix-internal-login-works-correctly;verify-belatrix-internal-login-works-correctly;;1"
    },
    {
      "cells": [
        "Jneira@belatrixsf.com",
        "Q3VydGVkekAxMQ\u003d\u003d"
      ],
      "line": 13,
      "id": "verify-belatrix-internal-login-works-correctly;verify-belatrix-internal-login-works-correctly;;2"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 11304305100,
  "status": "passed"
});
formatter.scenario({
  "line": 13,
  "name": "Verify Belatrix INternal Login Works Correctly",
  "description": "",
  "id": "verify-belatrix-internal-login-works-correctly;verify-belatrix-internal-login-works-correctly;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 3,
      "name": "@owner_Jneira"
    },
    {
      "line": 3,
      "name": "@Smoke"
    }
  ]
});
formatter.step({
  "line": 5,
  "name": "I Login To Belatrix Page with Jneira@belatrixsf.com as User and Q3VydGVkekAxMQ\u003d\u003d as Password",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "Belatrix Page is Correctly Displayed",
  "keyword": "Then "
});
formatter.step({
  "line": 7,
  "name": "I Click on My Files Button; in Belatrix Main Page",
  "keyword": "When "
});
formatter.step({
  "line": 8,
  "name": "My Files Page is Correctly Displayed; in Belatrix Page",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "Jneira@belatrixsf.com",
      "offset": 30
    },
    {
      "val": "Q3VydGVkekAxMQ\u003d\u003d",
      "offset": 64
    }
  ],
  "location": "ProjectSteps.LoginTo_BelatrixPage(String,String)"
});
formatter.result({
  "duration": 32378482200,
  "error_message": "org.openqa.selenium.UnhandledAlertException: unexpected alert open: {Alert text : Usuario o contraseña incorrectos.}\n  (Session info: chrome\u003d77.0.3865.75): Usuario o contraseña incorrectos.\nBuild info: version: \u00273.141.59\u0027, revision: \u0027e82be7d358\u0027, time: \u00272018-11-14T08:17:03\u0027\nSystem info: host: \u0027JNEIRA\u0027, ip: \u0027192.168.2.90\u0027, os.name: \u0027Windows 10\u0027, os.arch: \u0027amd64\u0027, os.version: \u002710.0\u0027, java.version: \u002712.0.2\u0027\nDriver info: org.openqa.selenium.chrome.ChromeDriver\nCapabilities {acceptInsecureCerts: false, browserName: chrome, browserVersion: 77.0.3865.75, chrome: {chromedriverVersion: 77.0.3865.40 (f484704e052e0..., userDataDir: C:\\Users\\jneira\\AppData\\Loc...}, goog:chromeOptions: {debuggerAddress: localhost:51526}, javascriptEnabled: true, networkConnectionEnabled: false, pageLoadStrategy: normal, platform: XP, platformName: XP, proxy: Proxy(), setWindowRect: true, strictFileInteractability: false, timeouts: {implicit: 0, pageLoad: 300000, script: 30000}, unhandledPromptBehavior: dismiss and notify}\nSession ID: d46f1980ed7cbf7bef0c42950a8f489f\r\n\tat org.openqa.selenium.remote.http.W3CHttpResponseCodec.decode(W3CHttpResponseCodec.java:120)\r\n\tat org.openqa.selenium.remote.http.W3CHttpResponseCodec.decode(W3CHttpResponseCodec.java:49)\r\n\tat org.openqa.selenium.remote.HttpCommandExecutor.execute(HttpCommandExecutor.java:158)\r\n\tat org.openqa.selenium.remote.service.DriverCommandExecutor.execute(DriverCommandExecutor.java:83)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.execute(RemoteWebDriver.java:552)\r\n\tat org.openqa.selenium.remote.RemoteWebDriver.executeScript(RemoteWebDriver.java:489)\r\n\tat components.Page.lambda$0(Page.java:102)\r\n\tat org.openqa.selenium.support.ui.FluentWait.until(FluentWait.java:249)\r\n\tat components.Page.waitForPageLoad(Page.java:103)\r\n\tat pages.BelatrixPage.LoginTo_BelatrixPage(BelatrixPage.java:29)\r\n\tat steps.ProjectSteps.LoginTo_BelatrixPage(ProjectSteps.java:32)\r\n\tat ✽.Given I Login To Belatrix Page with Jneira@belatrixsf.com as User and Q3VydGVkekAxMQ\u003d\u003d as Password(datafile/features/Verify_BelatrixIntranetLogin_WorksCorrectly.feature:5)\r\n",
  "status": "failed"
});
formatter.match({
  "location": "ProjectSteps.BelatrixPage_CorrectlyDisplayed()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "ProjectSteps.ClickOn_MyFilesButton_in_BelatrixMainPage()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "ProjectSteps.MyFilesPage_CorrectlyDisplayed_in_BelatrixPage()"
});
formatter.result({
  "status": "skipped"
});
formatter.embedding("image/png", "embedded0.png");
formatter.after({
  "duration": 1390569400,
  "status": "passed"
});
});