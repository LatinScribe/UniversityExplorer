package use_case.user_profile;

import data_access.ProfileDataAccessInterface;
import entity.UserPreferences;

import java.io.IOException;

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
            this.profileDataAccessInterface.updateProfile(finAidRequirement, preferredProgram, avgSalary, universityRankingRange, locationPreference);
        } catch (IOException e) {
            throw new RuntimeException(e);
            // TODO: Make this error more descriptive
        }
    }

    @Override
    public void fetchUserProfileData() {
        try {
            UserPreferences userPreferences = profileDataAccessInterface.getProfile();
            UserProfileOutputData userProfileData = new UserProfileOutputData(userPreferences.getFinAidRequirement(), userPreferences.getAvgSalary(),
                    userPreferences.getLocationPreference(), userPreferences.getPreferredProgram(), userPreferences.getUniversityRankingRange());

            userProfileOutputBoundary.presentUserProfile(userProfileData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
