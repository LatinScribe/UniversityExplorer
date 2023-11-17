package use_case.user_profile;

public class UserProfileInteractor implements UserProfileInputBoundary{

    final UserProfileOutputBoundary userProfilePresenter;

    // final UserPreferencesFactory userPreferenceFactory;

    public UserProfileInteractor(UserProfileOutputBoundary userProfilePresenter){
        this.userProfilePresenter = userProfilePresenter;
        // this.userPreferenceFactory = userPreferenceFactory;

        // TODO: See if you even need the userPreferenceFactory here

    }

    @Override
    public void execute(UserProfileInputData userProfileInputData) {



    }
}
