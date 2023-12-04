package use_case.user_profile;

import data_access.ProfileDataAccessInterface;
import entity.UserPreferences;
import entity.UserProfile;

import java.io.IOException;

public class UserProfileInteractor implements UserProfileInputBoundary {

    final UserProfileOutputBoundary userProfileOutputBoundary;
    // UserProfilePresenter implements this and will be the primary function called

    final ProfileDataAccessInterface profileDataAccessInterface;

    // final UserPreferencesFactory userPreferenceFactory;

    public UserProfileInteractor(UserProfileOutputBoundary userProfilePresenter, ProfileDataAccessInterface profileDataAccessInterface){
        this.userProfileOutputBoundary = userProfilePresenter;
        this.profileDataAccessInterface = profileDataAccessInterface;

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

    public void saveNewUserProfile(int finAidRequirement, int avgSalary, String locationPreference, String preferredProgram, int[] universityRankingRange) throws IOException {
        try {
            UserPreferences userPreferences = new UserPreferences(finAidRequirement, preferredProgram, avgSalary, universityRankingRange, locationPreference);
            this.profileDataAccessInterface.saveProfile(userPreferences);
        } catch (IOException e) {
            throw new RuntimeException(e);
            // TODO: Make this error more descriptive
        }

    }

    public void prepareMainMenuView() {
        this.userProfileOutputBoundary.prepareMainMenuView();
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
