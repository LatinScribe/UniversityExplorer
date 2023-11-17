package interface_adapter.user_profiles;

import interface_adapter.ViewManagerModel;
import use_case.user_profile.UserProfileOutputBoundary;
import use_case.user_profile.UserProfileOutputData;
import view.UserProfileView;
import view.ViewManager;

public class UserProfilePresenter implements UserProfileOutputBoundary {

    private ViewManagerModel viewManagerModel;

    private UserProfileViewModel userProfileViewModel;

    public UserProfilePresenter(ViewManagerModel viewManager, UserProfileViewModel userProfileViewModel) {
        this.viewManagerModel = viewManager;
        this.userProfileViewModel = userProfileViewModel;


    }
    @Override
    public void prepareSuccessView(UserProfileOutputData user) {
        return;

    }

    @Override
    public void prepareFailView(String error) {
        return;

    }
}
