package use_case.user_profile;

import entity.UserPreferencesFactory;

public class UserProfileInteractor implements UserProfileInputBoundary{

    final UserProfileDataAccessInterface profileDataAccessObject;

    final UserProfileOutputBoundary userProfilePresenter;

    final UserPreferencesFactory userPreferenceFactory;

    public UserProfileInteractor(UserProfileDataAccessInterface profileDataAccessObject,
                                 UserProfileOutputBoundary userProfilePresenter,
                                 UserPreferencesFactory userPreferenceFactory) {
        this.profileDataAccessObject = profileDataAccessObject;
        this.userProfilePresenter = userProfilePresenter;
        this.userPreferenceFactory = userPreferenceFactory;

    }

    @Override
    public void execute(UserProfileInputData userProfileInputData) {



    }
}
