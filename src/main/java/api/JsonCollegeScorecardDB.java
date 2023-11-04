package api;

import entity.Grade;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
//https://api.data.gov/ed/collegescorecard/v1/schools.json?school.degrees_awarded.predominant=2,3&fields=id,school.name,2013.student.size&api_key=AwypPp5bIHx6H4EevsB5sFs4gw5NK1M7SNc4eXwl
public class JsonCollegeScorecardDB implements CollegeScorecardDB{
    String API_TOKEN = "AwypPp5bIHx6H4EevsB5sFs4gw5NK1M7SNc4eXwl";
    String API_ADDRESS = "https://api.data.gov/ed/collegescorecard/v1/";
    @Override
    public JSONObject basicQuery(String endpoint, String fileFormat, String queryParameters, String optionalParameters) {

        // create the query url
        String url = API_ADDRESS + endpoint + fileFormat + "?" + queryParameters + "&" + optionalParameters + "&api_key=" + API_TOKEN;
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt("status_code") == 200) {
                JSONObject grade = responseBody.getJSONObject("grade");
                return grade;
            } else {
                throw new RuntimeException(responseBody.getString("message"));
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JSONObject basicQuery(String queryParameters, String optionalParameters) {
        // create the query url
        String url = API_ADDRESS+ "schools.json?" + queryParameters + "&" + optionalParameters + "&api_key=" + API_TOKEN;
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            JSONObject responseBody = new JSONObject(response.body().string());
                return responseBody;

//            if (responseBody.getInt("status_code") == 200) {
//                JSONObject grade = responseBody.getJSONObject("grade");
//                return grade;
//            } else {
//                throw new RuntimeException(responseBody.getString("message"));
//            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        JsonCollegeScorecardDB db = new JsonCollegeScorecardDB();
        JSONObject stuff = db.basicQuery("school.degrees_awarded.predominant=2,3", "fields=id,school.name,2013.student.size");
        System.out.println(stuff);
    }
}
