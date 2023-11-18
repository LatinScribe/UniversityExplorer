// Author: Henry
// Note: NEEDS INTERNET ACCESS
package data_access;

import entity.*;
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
    private ExistingUserFactory userFactory;

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
                ExistingUser user = this.userFactory.create(username, responseBody.getInt("id"), password, curr, responseBody.getString("token"));
                return user;

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
                if (result.equals("USER EXISTS")) {
                    return true;
                }
                else {
                    return false;
                }

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

    public static void main(String[] args) {
        ServerUserDataAccessObject db = new ServerUserDataAccessObject(new ExistingCommonUserFactory());
        boolean result = db.existsByName("johny");
        System.out.println("This should return true");
        System.out.println(result);

        boolean result2 = db.existsByName("somerandomuser");
        System.out.println("This should return false");
        System.out.println(result2);

        CreationCommonUserFactory fact = new CreationCommonUserFactory();
        LocalDateTime time = LocalDateTime.now();

        ExistingUser myuser = db.get("johny1234", "12342324");
        System.out.println("We expect true");
        System.out.println(myuser.getToken().equals("1kRrWzHPUZnSFlFevLuMBoQi2lFeXP8z"));
        System.out.println("We expect true");
        System.out.println(myuser.getID() == 8);

        // change username to something else or else an error is thrown!
        try {
            CreationUser user = fact.create("BillyBob123", "1234654", time);
            ExistingUser user4 = db.save(user);
            System.out.println(user4.getToken());
        }
        catch (RuntimeException e) {
            System.out.println(e);
            System.out.println("This should be because user already exists");
        }

    }
}
