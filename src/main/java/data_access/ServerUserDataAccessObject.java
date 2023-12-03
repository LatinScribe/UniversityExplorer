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

/**
 * A class that contains the data access object to store and retrieve user data from
 * the server
 *
 * @author Henry
 */
public class ServerUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface {
    private final ExistingUserFactory userFactory;

    public ServerUserDataAccessObject(ExistingUserFactory factory) {
        this.userFactory = factory;
    }

    /**
     * Use this method to retrieve a user from the server
     * User must already exist in the database, otherwise error is returned
     *
     * @param username
     * @param password
     * @return Selected user's data packaged as an ExistingUser Data entity
     */
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

    /**
     * Use this method to check if the user is already on the server database
     * Checks the username
     *
     * @param identifier
     * @return boolean value representing whether the user is in the database or not
     */
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

    /**
     * Use this method to save a user to the server
     *
     * @param user populated Creation user to save
     * @return The existing user, with the new id and token values.
     */
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
