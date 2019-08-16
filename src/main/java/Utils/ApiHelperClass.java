package Utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
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

    public String getDataValue(Map<String, Object> extract, String key)
    {
        return extract.get(key).toString();
    }

    public Map<String, Object> getData(Response response)
    {
        Map<String, Object> jsonAsMap = new HashMap<String, Object>();
        jsonAsMap = response.then().contentType(ContentType.JSON).extract().path("data");
        return jsonAsMap;
    }

    public JSONObject buildJsonObjectToCreateQuestion(String question, String type, JSONArray options)
    {
        try {
            JSONObject requestParams = new JSONObject();
            requestParams.put("options" , options);
            requestParams.put("type",type);
            requestParams.put("text", question);
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
            System.out.println(option.toString());
            return option;
        }
        catch (JSONException e)
        {
            System.out.println("Exception during request body creation:" + e.getStackTrace());
        }
        return null;
    }
}
