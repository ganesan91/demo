import DataProviders.DataProviderClass;
import Utils.ApiBaseClass;
import Utils.ApiHelperClass;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApiTestClass extends ApiBaseClass {

    private static String USERNAME = "test";
    private static String PASSWORD = "test";
    private String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjEsImlhdCI6MTU2NTk0Mjg5M30.Rl9R-TVdMrvKdlct1oBTf_XwnjouaxCaDT0w9mclVd0";
    private String invalidToken = "ciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjEsImlhdCI6MTU2NTk0Mjg5M30.Rl9R-TVdMrvKdlct1oBTf_XwnjouaxCaDT0w9mclVd0";
    ApiHelperClass apiHelper;
    List<String> keys = new ArrayList<String>();

    @BeforeClass
    public void setup()
    {
        keys.add("text");
        keys.add("type");
        keys.add("options");
        keys.add("id");

        setBaseUri();
        apiHelper = new ApiHelperClass();
        loginApiTest();
        // We can add any configurations/setup to be done before starting actual tests
    }

    @Test(dataProviderClass = DataProviderClass.class, dataProvider = "getQuestionsApiTestDataProvider")
    public void getQuestionsApiTest(int expectedStatusCode, boolean validToken)
    {
        String tokenData = token;
        if(!validToken)
        {
            tokenData=invalidToken;
        }
        Response response = getQuestions(tokenData);
        Assert.assertEquals(apiHelper.getResponseStatusCode(response), expectedStatusCode, "Error getting questions : " + response.getStatusLine());
        System.out.println(response.getStatusLine());
        System.out.println(response.asString());
    }

    @Test(dataProviderClass = DataProviderClass.class, dataProvider = "getQuestionUsingIdApiTestDataProvider")
    public void getQuestionUsingIdApiTest(String ID, int expectedStatusCode, boolean validToken)
    {
        String tokenData = token;
        if(!validToken)
        {
            tokenData=invalidToken;
        }
        Response response = getQuestionUsingId(ID, tokenData);
        Assert.assertEquals(apiHelper.getResponseStatusCode(response), expectedStatusCode, "Error getting questions : " + response.getStatusLine());
        Map<String, Object> getDataMap = apiHelper.getData(response);
        Assert.assertNotNull(getDataMap, "Data should not be null");
        validateData(getDataMap);
        System.out.println(response.getStatusLine());
    }

    @Test(dataProviderClass = DataProviderClass.class, dataProvider = "getQuestionUsingIdApiTestDataProvider")
    public void deleteQuestionApiTest(String ID, int expectedStatusCode, boolean validToken)
    {
        String tokenData = token;
        if(!validToken)
        {
            tokenData=invalidToken;
        }
        Response response = getQuestionUsingId(ID, tokenData);
        Assert.assertEquals(apiHelper.getResponseStatusCode(response), expectedStatusCode, "Error getting questions : " + response.getStatusLine());
        Assert.assertNull(apiHelper.getData(response), "Data should be Null if deletion is successful");
        System.out.println(response.getStatusLine());
    }

//    @Test
//    public void createQuestionApiTest()
//    {
//        JSONArray options = new JSONArray();
//        options.put(apiHelper.buildJsonObjectForOptions("true",true));
//        options.put(apiHelper.buildJsonObjectForOptions("false",false));
//
//        Response response = addQuestion(apiHelper.buildJsonObjectToCreateQuestion("What is question 1?" , "tof", options));
//        softAssert.assertEquals(apiHelper.getResponseStatusCode(response), 200, "Error getting questions : " + response.getStatusLine());
//    }

    public void loginApiTest()
    {
        Response response = login(USERNAME,PASSWORD);
        int responseCode = apiHelper.getResponseStatusCode(response);
        Assert.assertEquals(responseCode, 200, "Login failed : " + response.getStatusLine());
        System.out.println("Login Successful - " + apiHelper.getToken(response));
        token = apiHelper.getToken(response);
    }

    public void validateData(Map<String, Object> map)
    {
        for(String key:keys)
        {
            Assert.assertTrue(map.containsKey(key), "Mandatory Key Not present.");
            Assert.assertNotNull(map.get(key), "Value should not be Null for " + key);
        }
    }


}
