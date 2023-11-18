package interface_adapter.user_profiles;

import interface_adapter.ViewManagerModel;
import use_case.user_profile.UserProfileOutputBoundary;

public class UserProfilePresenter implements UserProfileOutputBoundary {

    private final ViewManagerModel viewManagerModel;

    private final UserProfileViewModel userProfileViewModel;

    // TODO: Need to add other views and viewmodels here


    public UserProfilePresenter(ViewManagerModel viewManagerModel, UserProfileViewModel userProfileViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.userProfileViewModel = userProfileViewModel;

    }


    @Override
    public void prepareEditView() {

    }

    @Override
    public void prepareProfileView() {

    }
}
