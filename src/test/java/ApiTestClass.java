import DataProviders.DataProviderClass;
import Utils.ApiBaseClass;
import Utils.ApiHelperClass;

import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApiTestClass extends ApiBaseClass {

    private static String token;
    private static String invalidToken = "ciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjEsImlhdCI6MTU2NTk0Mjg5M30.Rl9R-TVdMrvKdlct1oBTf_XwnjouaxCaDT0w9mclVd0";
    ApiHelperClass apiHelper;
    List<String> keys = new ArrayList<String>();

    @BeforeClass
    public void setup()
    {
        addKeys();
        setBaseUri();
        apiHelper = new ApiHelperClass();
        loginApiTest(USERNAME, PASSWORD,200);
        // We can add any configurations/setup to be done before starting actual tests
    }

    @Test(dataProviderClass = DataProviderClass.class, dataProvider = "getQuestionsApiTestDataProvider")
    public void getQuestionsApiTest(int expectedStatusCode, boolean validToken)
    {
        String tokenData = setToken(validToken);
        Response response = getQuestions(tokenData);
        Assert.assertEquals(apiHelper.getResponseStatusCode(response), expectedStatusCode, "Unexpected Status : " + response.getStatusLine());
    }

    @Test(dataProviderClass = DataProviderClass.class, dataProvider = "getQuestionUsingIdApiTestDataProvider")
    public void getQuestionUsingIdApiTest(String ID, int expectedStatusCode, boolean validToken)
    {
        int status;
        String tokenData = setToken(validToken);
        Response response = getQuestionUsingId(ID, tokenData);
        status = apiHelper.getResponseStatusCode(response);
        Assert.assertEquals(status, expectedStatusCode, "Unexpected Status : " + response.getStatusLine());
        if (status == 200)
        {
            Map<String, Object> getDataMap = apiHelper.getData(response);
            Assert.assertNotNull(getDataMap, "Data should not be null");
            validateData(getDataMap);
            System.out.println(response.getStatusLine());
        }
    }

    @Test(dataProviderClass = DataProviderClass.class, dataProvider = "deleteQuestionApiTestDataProvider")
    public void deleteQuestionApiTest(String ID, int expectedStatusCode, boolean validToken)
    {
        int status;
        String tokenData = setToken(validToken);
        Response deleteResponse = deleteQuestion(ID, tokenData);
        status = apiHelper.getResponseStatusCode(deleteResponse);
        Assert.assertEquals(status, expectedStatusCode, "Unexpected Status : " + deleteResponse.getStatusLine());
        if (status == 200)
        {
            Response response = getQuestionUsingId(ID, tokenData);
            Assert.assertEquals(apiHelper.getResponseStatusCode(response), 404, "Unexpected Status while verifying delete : " + response.getStatusLine());
            Assert.assertNull(apiHelper.getData(response), "Data should be Null if deletion is successful");
            System.out.println(response.getStatusLine());
        }
    }

    @Test(dataProviderClass = DataProviderClass.class, dataProvider = "createQuestionApiTestDataProvider")
    public void createQuestionApiTest(String question, String type, JSONArray options, int expectedStatusCode, boolean validToken )
    {
        int status;
        String tokenData = setToken(validToken);
        Response createResponse = addQuestion(apiHelper.buildJsonObjectToCreateQuestion( question, type, options), tokenData);
        status = apiHelper.getResponseStatusCode(createResponse);
        Assert.assertEquals(status, expectedStatusCode, "Unexpected Status :  " + createResponse.getStatusLine());
        if(status == 200) {
            Map<String, Object> getCreateDataMap = apiHelper.getData(createResponse);
            Assert.assertNotNull(getCreateDataMap, "Data should not be null");
            validateData(getCreateDataMap);
            validateOptions(getCreateDataMap);
            Response response = getQuestionUsingId(getCreateDataMap.get("id").toString(), tokenData);
            Map<String, Object> getDataMap = apiHelper.getData(response);
            Assert.assertNotNull(getDataMap, "Data should not be null");
        }
    }

    @Test(dataProviderClass = DataProviderClass.class, dataProvider = "loginTestDataProvider")
    public void loginApiErrorCaseTest(String username, String password, int expectedStatusCode)
    {
        loginApiTest(username, password, expectedStatusCode);
    }

    public void loginApiTest(String username, String password, int expectedStatusCode)
    {
        int status;
        Response response = login(username,password);
        status = apiHelper.getResponseStatusCode(response);
        Assert.assertEquals(apiHelper.getResponseStatusCode(response), expectedStatusCode, "Unexpected Status : " + response.getStatusLine());
        if(status == 200) {
            System.out.println("Login Successful - " + apiHelper.getToken(response));
            token = apiHelper.getToken(response);
        }
    }

    public void validateData(Map<String, Object> map)
    {
        for(String key:keys)
        {
            Assert.assertTrue(map.containsKey(key), "Mandatory Key Not present.");
            Assert.assertNotNull(map.get(key), "Value should not be Null for " + key);
        }
    }

    public void addKeys()
    {
        keys.add("text");
        keys.add("type");
        keys.add("options");
        keys.add("id");
    }

    public void validateOptions(Map<String, Object> map)
    {
        String type = map.get("type").toString();
        List<String> getTextValues = new ArrayList<String>();
        List<String> getIsCorrectValues = new ArrayList<String>();

        try{
            JSONArray jsonArray = new   JSONArray(map.get("options").toString());
            for(int n = 0; n < jsonArray.length(); n++)
            {
                JSONObject object = jsonArray.getJSONObject(n);
                Assert.assertTrue(object.has("text"), "Key \"text\" not found in option");
                Assert.assertNotNull(object.get("text"), "\"Text\" value for options should not be null");
                getTextValues.add(object.get("text").toString());
                Assert.assertTrue(object.has("is_correct"), "Key \"is_correct\" not found in option");
                Assert.assertNotNull(object.get("is_correct"), "\"is_correct\" value for options should not be null");
                getIsCorrectValues.add(object.get("is_correct").toString());
            }
            if(type.equals("tof"))
            {
                Assert.assertEquals(jsonArray.length(), 2, "Number of options should be 2.");
                Assert.assertTrue(getTextValues.contains("true"), "Text value should have \"true\" for type \"tof\".");
                Assert.assertTrue(getTextValues.contains("false"), "Text value should have \"false\" for type \"tof\".");
            }
            Assert.assertTrue(getIsCorrectValues.contains("true"), "is_correct value should have \"true\".");
            Assert.assertTrue(getIsCorrectValues.contains("false"), "is_correct value should have \"false\".");
        }
        catch (JSONException e)
        {
            System.out.println(e.getStackTrace());
        }

    }

    public String setToken(boolean validToken)
    {
        if(!validToken)
        {
            return invalidToken;
        }
        return token;
    }
}
