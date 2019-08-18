#API Automation tests for interview with XSEED.

Getting Started
This is a maven project. Clone the repo locally and import as maven project. 

Running the tests
Once you have cloned and imported successfully. You can run the test via 2 methods. 

1. Navigate to the repo path and run "mvn test" command. 
2. Open the project in IDE. Right click on the testng.xml file under test folder/ right click the class ApiTestClass and select "run".

Both options will run the application and give you the test results.

Results/Reports:
Once the test run completed, we will get a "Test-output" folder created in project root path with all the reports.
Open index.html in some browser to view the reports and test case results.

Tools used for building the framework:
1. Java
2. Rest assured - for API testing.
3. TestNG - Testing framework tool.
4. TestNG assertions for validations.
5. JSON for response validation. 
6. github and maven.
7. TestNG for report generation.

GitHub url - https://github.com/ganesan91/demo.git

Bugs found: 
Bug 1 : createQuestionApiTest -> when request body input has options with is_correct value "true" for both options, creation should fail.
Bug 2 : createQuestionApiTest -> when request body input has options with is_correct value "false" for both options, creation should fail.
Bug 3 : createQuestionApiTest -> when request body input has empty value for any field json should return 400, but returns 500 Internal Server Error.
Bug 4 : createQuestionApiTest -> when request body input with type "tof" and has options with text value other than true/false, creations should fail.
Bug 5 : deleteQuestionApiTest -> Delete api is not working, question is not getting deleted though delete API returns 200 success.
Bug 6 : deleteQuestionApiTest -> Deleting with question id that doesn't exists should return 404, but returns 200 success.
Bug 7 : getQuestionUsingIdApiTest -> Get api with question id that doesn't exists should return 404, but returns 200 success.
Bug 8 : getQuestionsApiTest -> Get api with invalid token should return 401, but returns 200 success.
Bug 9 : loginApiErrorCaseTest -> Login with invalid username/credentials should return 401, but returns 400 bad request.

Further Enhancements : 
- Dynamic building data providers
- Logging
- Java doc
- Creating constants
- Creating properties file for setup configuration

Authors
Ganesan Narayanan.
