package use_case.user_profile;

import java.lang.reflect.Array;

public class UserProfileInteractor implements UserProfileInputBoundary{

    final UserProfileOutputBoundary userProfileOutputBoundary;

    // final UserPreferencesFactory userPreferenceFactory;

    public UserProfileInteractor(UserProfileOutputBoundary userProfilePresenter){
        this.userProfileOutputBoundary = userProfilePresenter;
        // this.userPreferenceFactory = userPreferenceFactory;

        // TODO: See if you even need the userPreferenceFactory here

    }

    @Override
    public void execute(UserProfileInputData userProfileInputData) {



    }

    @Override
    public void showPersonalProfileView() {

    }

    public void showPersonalProfileView(UserProfileOutputData userProfileOutputData) {
        this.userProfileOutputBoundary.presentUserProfile(userProfileOutputData);
    }

    @Override
    public void updateUserProfile(int finAidRequirement, int avgSalary, String locationPreference, String preferredProgram, Integer[] universityRankingRange) {
        // TODO: Look at Henry's implementation of the data object and use that to update CSV data
    }
}
