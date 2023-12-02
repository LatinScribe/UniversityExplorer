package data_access;

import entity.UserPreferences;
import entity.UserPreferencesFactory;
import entity.UserProfile;
import entity.UserProfileFactory;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;

public class ServerProfileDataAccessObject implements ProfileDataAccessInterface{
    private final TokenDataAccessInterface tokenDataAccessInterface;

    private final UserProfileFactory userProfileFactory;

    public ServerProfileDataAccessObject(TokenDataAccessInterface tokenDataAccessInterface, UserProfileFactory userProfileFactory) {
        this.tokenDataAccessInterface = tokenDataAccessInterface;
        this.userProfileFactory = userProfileFactory;
    }

    @Override
    public String saveProfile(UserProfile userProfile) throws IOException {
        if (userProfile.getUniversityRankingRange().length != 2) {
            throw new IOException("saveProfile expected 2 elements in uniRankingRange");
        }
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", tokenDataAccessInterface.retrieve_id());
        requestBody.put("finAidReq", userProfile.getFinAidRequirement());
        requestBody.put("prefProg", userProfile.getPreferredProgram());
        requestBody.put("avgSalary", userProfile.getAvgSalary());
        requestBody.put("uniRankingRangeStart", userProfile.getUniversityRankingRange()[0]);
        requestBody.put("uniRankingRangeEnd", userProfile.getUniversityRankingRange()[1]);
        requestBody.put("locationPref", userProfile.getLocationPreference());
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
    public String updateProfile(UserProfile userProfile) throws IOException {
        if (userProfile.getUniversityRankingRange().length != 2) {
            throw new IOException("saveProfile expected 2 elements in uniRankingRange");
        }
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", tokenDataAccessInterface.retrieve_id());
        requestBody.put("finAidReq", userProfile.getFinAidRequirement());
        requestBody.put("prefProg", userProfile.getPreferredProgram());
        requestBody.put("avgSalary", userProfile.getAvgSalary());
        requestBody.put("uniRankingRangeStart", userProfile.getUniversityRankingRange()[0]);
        requestBody.put("uniRankingRangeEnd", userProfile.getUniversityRankingRange()[1]);
        requestBody.put("locationPref", userProfile.getLocationPreference());
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
    public UserProfile getProfile() throws IOException {
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
                int[] uniRankingRange = {responseBody.getInt("uniRankingRangeStart"), responseBody.getInt("uniRankingRangeEnd")};

                return userProfileFactory.create(responseBody.getInt("finAidReq"), responseBody.getString("prefProg"), responseBody.getInt("avgSalary"), uniRankingRange,responseBody.getString("locationPref"));
            } else {
                throw new RuntimeException(responseBody.getString("message"));
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
