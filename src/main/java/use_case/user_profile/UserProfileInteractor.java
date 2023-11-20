package use_case.user_profile;

import data_access.ProfileDataAccessInterface;

import java.awt.color.ProfileDataException;
import java.io.IOException;
import java.lang.reflect.Array;

public class UserProfileInteractor implements UserProfileInputBoundary{

    final UserProfileOutputBoundary userProfileOutputBoundary;

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
    public void updateUserProfile(int finAidRequirement, int avgSalary, String locationPreference, String preferredProgram, Integer[] universityRankingRange) {
        try {
            this.profileDataAccessInterface.updateProfile(finAidRequirement, preferredProgram, avgSalary, universityRankingRange, locationPreference);
        } catch (IOException e) {
            throw new RuntimeException(e);
            // TODO: Make this error more descriptive
        }
    }
}
