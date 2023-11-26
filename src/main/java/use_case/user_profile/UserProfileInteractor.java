package use_case.user_profile;

import data_access.ProfileDataAccessInterface;
import entity.UserPreferences;
import entity.UserProfile;

import java.awt.color.ProfileDataException;
import java.io.IOException;
import java.lang.reflect.Array;

public class UserProfileInteractor implements UserProfileInputBoundary{

    final UserProfileOutputBoundary userProfileOutputBoundary;
    // UserProfilePresenter implements this and will be the primary function called

    final ProfileDataAccessInterface profileDataAccessInterface;

    // final UserPreferencesFactory userPreferenceFactory;

    public UserProfileInteractor(UserProfileOutputBoundary userProfilePresenter, ProfileDataAccessInterface profileDataAccessInterface){
        this.userProfileOutputBoundary = userProfilePresenter;
        // this.userPreferenceFactory = userPreferenceFactory;
        this.profileDataAccessInterface = profileDataAccessInterface;

        // TODO: See if you even need the userPreferenceFactory here

    }

    public void showPersonalProfileView(UserProfileOutputData userProfileOutputData) {
        this.userProfileOutputBoundary.presentUserProfile(userProfileOutputData);
    }

    @Override
    public void updateUserProfile(int finAidRequirement, int avgSalary, String locationPreference, String preferredProgram, int[] universityRankingRange) {
        try {
            UserPreferences userPreferences = new UserPreferences(finAidRequirement, preferredProgram, avgSalary, universityRankingRange, locationPreference);
            this.profileDataAccessInterface.updateProfile(userPreferences);
        } catch (IOException e) {
            throw new RuntimeException(e);
            // TODO: Make this error more descriptive
        }
    }

    @Override
    public void fetchUserProfileData() {
        try {
            UserProfile userProfilePreferences = profileDataAccessInterface.getProfile();
            UserProfileOutputData userProfileData = new UserProfileOutputData(userProfilePreferences.getFinAidRequirement(), userProfilePreferences.getAvgSalary(), userProfilePreferences.getLocationPreference(), userProfilePreferences.getPreferredProgram(), userProfilePreferences.getUniversityRankingRange());
            userProfileOutputBoundary.presentUserProfile(userProfileData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
