package utils.api;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ApiBaseClass {

    private String BASE_URL = "https://tranquil-ridge-49349.herokuapp.com";
    private String BASE_URI = "/api/v1";
    private String LOGIN_URI = "/users/authenticate";
    private String QUESTIONS_URI = "/questions";
    private String ADD_QUESTION_URI = "/new";
    protected static String USERNAME = "test";
    protected static String PASSWORD = "test";


    /**
     * Constructor call.
     */
    public ApiBaseClass() {

    }

    public void setBaseUri()
    {
        RestAssured.baseURI = BASE_URL;
    }

    public Response login(String username, String password)
    {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        Map<String, Object> jsonAsMap = new HashMap<String, Object>();
        jsonAsMap.put("username", username);
        jsonAsMap.put("password", password);
        request.body(jsonAsMap);
        return request.post(LOGIN_URI);
    }

    public Response getQuestions(String token)
    {
        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "Bearer "+token);
        return request.get(BASE_URI + QUESTIONS_URI);
    }

    public Response getQuestionUsingId(String id, String token)
    {
        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "Bearer "+token);
        return request.get(BASE_URI + QUESTIONS_URI+"/"+id);
    }

    public Response deleteQuestion(String id, String token)
    {
        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "Bearer "+token);
        return request.delete(BASE_URI + QUESTIONS_URI+"/"+id);
    }

    public Response addQuestion(JSONObject jsonObject, String token)
    {
        RequestSpecification request = RestAssured.given();
        request.header("Authorization", "Bearer "+ token);
        request.header("Content-Type", "application/json");
        request.body(jsonObject.toString());
        return request.post(BASE_URI + QUESTIONS_URI + ADD_QUESTION_URI);
    }

}
