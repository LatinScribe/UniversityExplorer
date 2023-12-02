// Author: Henry
// Note: NEEDS INTERNET ACCESS
package data_access;

import entity.CreationUser;
import entity.ExistingUser;
import entity.ExistingUserFactory;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.io.IOException;
import java.time.LocalDateTime;

public class ServerUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface {
    private final ExistingUserFactory userFactory;

    public ServerUserDataAccessObject(ExistingUserFactory factory) {
        this.userFactory = factory;
    }

    @Override
    public ExistingUser get(String username, String password) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(String.format("https://henrytchen.com/custom-api/signIn?username=%s&password=%s", username, password))
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt("status_code") == 200) {
                LocalDateTime curr = LocalDateTime.now();
                return this.userFactory.create(username, responseBody.getInt("id"), password, curr, responseBody.getString("token"));

            } else {
                throw new RuntimeException(responseBody.getString("message"));
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existsByName(String identifier) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(String.format("https://henrytchen.com/custom-api/existsByName?username=%s", identifier))
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt("status_code") == 200) {

                String result = responseBody.getString("message");
                return result.equals("USER EXISTS");

            } else {
                throw new RuntimeException(responseBody.getString("message"));
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ExistingUser save(CreationUser user) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(String.format("https://henrytchen.com/custom-api/signUp?username=%s&password=%s", user.getName(), user.getPassword()))
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            assert response.body() != null;
            JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt("status_code") == 200) {
                LocalDateTime curr = LocalDateTime.now();
                return this.userFactory.create(user.getName(), responseBody.getInt("id"), user.getPassword(), curr, responseBody.getString("token"));

            } else {
                throw new RuntimeException(responseBody.getString("message"));
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
