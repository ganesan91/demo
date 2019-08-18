package Utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class ApiHelperClass {

    public int getResponseStatusCode(Response response)
    {
        return response.getStatusCode();
    }

    public String getToken(Response response)
    {
        return response.then().contentType(ContentType.JSON).extract().path("token");
    }

    public Map<String, Object> getData(Response response)
    {
        Map<String, Object> jsonAsMap;
        jsonAsMap = response.then().contentType(ContentType.JSON).extract().path("data");
        return jsonAsMap;
    }

    public JSONObject buildJsonObjectToCreateQuestion(String question, String type, JSONArray options)
    {
        try {
            JSONObject requestParams = new JSONObject();
            requestParams.put("text", question);
            requestParams.put("type",type);
            requestParams.put("options" , options);
            return requestParams;
        }
        catch (JSONException e)
        {
            System.out.println("Exception during request body creation:" + e.getStackTrace());
        }
        return null;
    }

    public JSONObject buildJsonObjectForOptions(String text, boolean is_correct)
    {
        try {
            JSONObject option = new JSONObject();
            option.put("text", text);
            option.put("is_correct", is_correct);
            return option;
        }
        catch (JSONException e)
        {
            System.out.println("Exception during request body creation:" + e.getStackTrace());
        }
        return null;
    }

    public JSONArray createJsonArray(String text1, String text2, boolean status1, boolean status2) {
        JSONArray options = new JSONArray();
        options.put(buildJsonObjectForOptions(text1,status1));
        options.put(buildJsonObjectForOptions(text2,status2));
        return options;
    }
}
