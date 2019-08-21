#API Automation tests for interview with XSEED.

*** This framework is build in a way that both UI and API automation can be done ***


Getting Started
This is a maven project. Clone the repo locally and import as maven project. 

Suites: 
There are 2 separate suites for UI and API testing. 
ui.xml is the testng file for UI Automation.
api.xml is the testng file for API automation.

Running the tests
Once you have cloned and imported successfully. You can run the test via 2 methods. 

1. Navigate to the repo path and run "mvn test -DsuiteXmlFile=api.xml or ui.xml" command. 
2. Open the project in IDE. Right click on the ui.xml for UI automation and api.xml for API automation file under test/resources folder and select "run".

Both options will run the application and give you the test results.

Results/Reports:
Once the test run completed, we will get a "Test-output" folder created in project root path with all the reports.
Open index.html in some browser to view the reports and test case results.

Tools used for building the framework:
1. Java
2. Rest assured - for API testing.
3. Selenium - for UI Automation
3. TestNG - Testing framework tool.
4. TestNG assertions for validations.
5. JSON for response validation. 
6. github and maven.
7. TestNG for report generation.

GitHub url - https://github.com/ganesan91/demo.git

Bugs found: 

API automation:
Bug 1 : createQuestionApiTest -> when request body input has options with is_correct value "true" for both options, creation should fail.
Bug 2 : createQuestionApiTest -> when request body input has options with is_correct value "false" for both options, creation should fail.
Bug 3 : createQuestionApiTest -> when request body input has empty value for any field json should return 400, but returns 500 Internal Server Error.
Bug 4 : createQuestionApiTest -> when request body input with type "tof" and has options with text value other than true/false, creations should fail.
Bug 5 : deleteQuestionApiTest -> Delete api is not working, question is not getting deleted though delete API returns 200 success.
Bug 6 : deleteQuestionApiTest -> Deleting with question id that doesn't exists should return 404, but returns 200 success.
Bug 7 : getQuestionUsingIdApiTest -> Get api with question id that doesn't exists should return 404, but returns 200 success.
Bug 8 : getQuestionUsingIdApiTest -> when response data doesn't have one field, test should fail - data inconsistency.
Bug 9 : getQuestionUsingIdApiTest -> when response data have one field with empty value, test should fail - data inconsistency.
Bug 10 : getQuestionUsingIdApiTest -> when response data with type "tof" and has options with text value other than true/false - data inconsistency.
Bug 11 : getQuestionsApiTest -> Get api with invalid token should return 401, but returns 200 success.
Bug 12 : loginApiErrorCaseTest -> Login with invalid username/credentials should return 401, but returns 400 bad request.

UI Automation:
Bug 1 : loginTest -> For invalid username/password there is no error message displayed for login failure.
Bug 2 : deleteQuestionTest -> Question deletion is not working.
Bug 3 : CheckMenuTabSelectedOnRefresh -> Switch tab to questions and when refreshed home tab is select but the elements shown are from question tab.
Bug 4 : CheckQuestionMenuTabSelectedOnRefresh -> Switch tab to create and when refreshed list tab is select but the elements shown are from create tab.
Bug 5 : createQuestionTest -> Creation should fail When trying to create question with text values (other than true or false) for tof type.
Bug 6 : createQuestionTest -> Creation failed for type mcq with error message - question validation failed: type: Path `type` is required.

**Also there are no error messages for many cases. I have included them to the test but skipped for now using a flag from dataprovider.

Further Enhancements : 
- Dynamically building data providers
- Logging
- Java doc
- Creating constants
- Creating properties file for setup configuration
- Parameterizing for required inputs from command line.

Authors
Ganesan Narayanan.
