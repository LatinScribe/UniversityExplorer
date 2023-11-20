package data_access;

import entity.UserPreferences;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class ServerProfileDataAccessObject implements ProfileDataAccessInterface{
    private final TokenDataAccessInterface tokenDataAccessInterface;

    public ServerProfileDataAccessObject(TokenDataAccessInterface tokenDataAccessInterface) {
        this.tokenDataAccessInterface = tokenDataAccessInterface;
    }

    @Override
    public String saveProfile(int finAidReq, String prefProg, int avgSalary, int uniRankingRange, String locationPref) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", tokenDataAccessInterface.retrieve_id());
        requestBody.put("finAidReq", finAidReq);
        requestBody.put("prefProg", prefProg);
        requestBody.put("avgSalary", avgSalary);
        requestBody.put("uniRankingRange", uniRankingRange);
        requestBody.put("locationPref", locationPref);
        RequestBody body = RequestBody.create(mediaType, requestBody.toString());
        Request request = new Request.Builder()
                .url("https://henrytchen.com/custom-api/saveProfile")
                .method("POST", body)
                .addHeader("Authorization", tokenDataAccessInterface.retrieve_token())
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            JSONObject responseBody = new JSONObject(response.body().string());
            System.out.println(responseBody);

            if (responseBody.getInt("status_code") == 200) {
                return null;
            } else {
                throw new RuntimeException(responseBody.getString("message"));
            }
        }
        catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String updateProfile(int finAidReq, String prefProg, int avgSalary, int uniRankingRange, String locationPref) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", tokenDataAccessInterface.retrieve_id());
        requestBody.put("finAidReq", finAidReq);
        requestBody.put("prefProg", prefProg);
        requestBody.put("avgSalary", avgSalary);
        requestBody.put("uniRankingRange", uniRankingRange);
        requestBody.put("locationPref", locationPref);
        RequestBody body = RequestBody.create(mediaType, requestBody.toString());
        Request request = new Request.Builder()
                .url("https://henrytchen.com/custom-api/updateProfile")
                .method("PUT", body)
                .addHeader("Authorization", tokenDataAccessInterface.retrieve_token())
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            JSONObject responseBody = new JSONObject(response.body().string());
            System.out.println(responseBody);

            if (responseBody.getInt("status_code") == 200) {
                return null;
            } else {
                throw new RuntimeException(responseBody.getString("message"));
            }
        }
        catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public UserPreferences getProfile() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(String.format("https://henrytchen.com/custom-api/profile?id=%s", this.tokenDataAccessInterface.retrieve_id()))
                .addHeader("Authorization", this.tokenDataAccessInterface.retrieve_token())
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt("status_code") == 200) {
//                JSONObject grade = responseBody.getJSONObject("grade");
                // TODO: Use a builder instead
//                return Grade.builder()
//                        .utorid(grade.getString("utorid"))
//                        .course(grade.getString("course"))
//                        .grade(grade.getInt("grade"))
//                        .build();
                ArrayList<Integer> myArray = new ArrayList<>();
                myArray.add(responseBody.getInt("uniRankingRange"));
                UserPreferences userPreferences = new UserPreferences(responseBody.getInt("finAidReq"), responseBody.getString("prefProg"), responseBody.getInt("avgSalary"), myArray,responseBody.getString("locationPref"));
                return  userPreferences;
            } else {
                throw new RuntimeException(responseBody.getString("message"));
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        // NOTE YOU MUST BE SIGNED IN TO RUN THIS!!!!
        FileTokenDataAccessObject fileTokenDataAccessObject = new FileTokenDataAccessObject();
        ServerProfileDataAccessObject db = new ServerProfileDataAccessObject(fileTokenDataAccessObject);
//        db.saveProfile(20000, "commerce", 100000, 5, "Boston");
        db.updateProfile( 100000, "compsci", 100000, 10, "New York");

        UserPreferences userPreferences = db.getProfile();
        System.out.println(userPreferences);
    }
}
