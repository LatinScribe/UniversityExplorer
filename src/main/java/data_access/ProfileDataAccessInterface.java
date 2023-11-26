package data_access;

import entity.UserPreferences;
import entity.UserProfile;

import java.io.IOException;

public interface ProfileDataAccessInterface {
    String saveProfile(UserProfile userProfile) throws IOException;

    String updateProfile(UserProfile userProfile) throws IOException;

    UserProfile getProfile() throws IOException;
}
