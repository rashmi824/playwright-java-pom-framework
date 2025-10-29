![Build Status](https://img.shields.io/badge/build-passing-brightgreen)
![TestNG](https://img.shields.io/badge/testng-7.9-blue)
![Extent Report](https://img.shields.io/badge/report-extent-orange)
![Playwright](https://img.shields.io/badge/automation-playwright-success)

Playwright Java Automation Framework    

An enterprise-grade automation framework built using Playwright for Java, designed with a strong architectural backbone for scalability, parallel execution, and continuous test integration via Jenkins Maven jobs.  

This project demonstrates deep automation engineering concepts — including Page Object Model (POM), Page Chaining, ThreadLocal driver management, configuration-driven execution, and rich HTML reporting   (Extent Reports).    


🧱 Framework Architecture & Design    

🔹 Page Object Model (POM)  

Each application page is represented as a dedicated class containing locators and actions.  
Enables high modularity, maintainability, and code reusability.  
Page Chaining is implemented — page actions return the next page object for smooth flow (e.g., loginPage.doLogin().navigateToHome()).    

🔹 ThreadLocal Implementation  

Every thread maintains its own Playwright driver instance, avoiding conflicts during parallel runs.  
Isolates test data and browser sessions at thread-level.  
Enables true parallel execution across multiple browsers and test suites.  

🔹 BaseTest Abstraction  

Encapsulates:  
Playwright setup & teardown.  
Thread-safe browser context creation.
Configuration loading from config.properties.  
Common utilities for test lifecycle management.  

🔹 Factory Layer (PlaywrightFactory) 

Centralized browser and context management layer.  
Supports:  
Chromium  
Firefox  
WebKit  
Dynamically initialized using property-driven configurations.  

🔹 Configuration Management  

Framework is fully config-driven.  
Environment variables (URL, browser type, headless mode, etc.) loaded from:  
src/test/resources/config/config.properties  

⚙️ Parallel Execution Strategy  

Managed via TestNG XML suites:  
src/test/resources/testrunners/testng_regression.xml  
Each test thread creates an isolated browser context using ThreadLocal.  
Maximum parallelism achieved through:  
TestNG parallel attributes (parallel="tests" thread-count="4")  
Maven Surefire plugin configuration in pom.xml.  

📊 Reporting & Analytics  
🔸 Extent Reports  
Generates visually rich HTML reports with categorized test outcomes.  
Integrated listener: ExtentReportListener.java  
Features:  
Screenshots for failed steps.  
Step-wise logging with timestamps.  
Embedded media for traceability.  

Output:  
/build/TestExecutionReport.html  

🔸 TestNG Default Reports  
Secondary reporting layer for suite-level visibility:  
/test-output/index.html  

🧩 Jenkins Integration (Maven Job Execution)

🔸 Jenkins Job Setup  

Configured as a Maven project (not a full CI/CD pipeline).  
Navigate to Jenkins Dashboard → New Item → Maven Project.  
Configure Git Repository. 
Under Build → Goals and Options, specify:  
clean test -DsuiteXmlFile=src/test/resources/testrunners/testng_regression.xml  
Save and trigger build manually or via SCM polling.  

🔸 Build & Post-Build Steps  

On each Jenkins job execution:  

Maven cleans and compiles the project.  
TestNG executes regression suite.  
Playwright spins up browsers (thread-safe).  
Extent & TestNG reports are generated automatically.  
Report Access Paths (Jenkins Workspace):  
Report	Location  
Extent HTML Report	build/TestExecutionReport.html  
TestNG HTML Report	test-output/index.html 

🔸 Jenkins Optimization  

Leverages Maven Surefire plugin for test orchestration.  
Thread-safe driver management using ThreadLocal ensures flawless parallel execution in Jenkins environments.  
HTML Publisher Plugin (optional) can be configured to publish reports under Jenkins UI.  

🧠 Key Concepts Demonstrated  

✅ Page Object Model (POM)  
✅ Page Chaining Implementation  
✅ ThreadLocal-based Browser Management  
✅ Configuration-Driven Execution  
✅ TestNG Parallel Execution  
✅ Extent & TestNG Dual Reporting  
✅ Jenkins Maven Job Integration  
✅ BaseTest Abstraction for Reusability    

🧪 How to Execute Tests  

▶️ Run Locally (via Maven)  
mvn clean test -DsuiteXmlFile=src/test/resources/testrunners/testng_regression.xml  

⚙️ Run via Jenkins (Maven Job)  
clean test -DsuiteXmlFile=src/test/resources/testrunners/testng_regression.xml  

🏗️ Future Enhancements (Planned)  

CI/CD pipeline integration (GitHub Actions)  
Allure Report integration  
Parallel cross-browser matrix execution  
Dockerized Playwright grid setup  
Configurable environment profiles (QA, UAT, Prod)  


