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
// <<<<<<< KanishV2
//     public String saveProfile(int finAidReq, String prefProg, int avgSalary, int[] uniRankingRange, String locationPref) throws IOException {
// =======
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

// <<<<<<< KanishV2
//     public String updateProfile(int finAidReq, String prefProg, int avgSalary, int[] uniRankingRange, String locationPref) throws IOException {
// =======
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
//                JSONObject grade = responseBody.getJSONObject("grade");
                // TODO: Use a builder instead
//                return Grade.builder()
//                        .utorid(grade.getString("utorid"))
//                        .course(grade.getString("course"))
//                        .grade(grade.getInt("grade"))
//                        .build();
                int[] uniRankingRange = {responseBody.getInt("uniRankingRangeStart"), responseBody.getInt("uniRankingRangeEnd")};

// <<<<<<< KanishV2
//                 UserPreferences userPreferences = new UserPreferences(responseBody.getInt("finAidReq"), responseBody.getString("prefProg"), responseBody.getInt("avgSalary"), uniRankingRange,responseBody.getString("locationPref"));
//                 return userPreferences;
// =======
                return userProfileFactory.create(responseBody.getInt("finAidReq"), responseBody.getString("prefProg"), responseBody.getInt("avgSalary"), uniRankingRange,responseBody.getString("locationPref"));
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
        UserPreferencesFactory userPreferencesFactory = new UserPreferencesFactory();
        ServerProfileDataAccessObject db = new ServerProfileDataAccessObject(fileTokenDataAccessObject, userPreferencesFactory);
//        db.saveProfile(20000, "commerce", 100000, 5, "Boston");
// <<<<<<< KanishV2
//         db.updateProfile( 100000, "compsci", 100000, new int[]{1,2}, "New York");

//         UserPreferences userPreferences = db.getProfile();
//         System.out.println("finaid: " + userPreferences.getFinAidRequirement());
//         System.out.println("prefProg: " + userPreferences.getPreferredProgram());
//         System.out.println("avgSal: " + userPreferences.getAvgSalary());
//         System.out.println("RankingRange: " + Arrays.toString(userPreferences.getUniversityRankingRange()));
//         System.out.println("locPref: " + userPreferences.getLocationPreference());
//         System.out.println(userPreferences);
// =======
        UserPreferences userPreferences = new UserPreferences(100000, "compsci", 100000, new int[]{1,2}, "New York");
        db.updateProfile(userPreferences);

        UserProfile userProfile = db.getProfile();
        System.out.println("finaid: " + userProfile.getFinAidRequirement());
        System.out.println("prefProg: " + userProfile.getPreferredProgram());
        System.out.println("avgSal: " + userProfile.getAvgSalary());
        System.out.println("RankingRange: " + Arrays.toString(userProfile.getUniversityRankingRange()));
        System.out.println("locPref: " + userProfile.getLocationPreference());
        System.out.println(userProfile);
    }
}
