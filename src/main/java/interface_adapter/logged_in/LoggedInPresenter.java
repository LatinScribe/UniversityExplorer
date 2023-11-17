package interface_adapter.logged_in;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.user_profiles.UserProfileState;
import interface_adapter.user_profiles.UserProfileViewModel;
import view.UserProfileView;

public class LoggedInPresenter {
    private final UserProfileViewModel userProfileViewModel;
//    private final LoginViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;

    public LoggedInPresenter(UserProfileViewModel userProfileViewModel, ViewManagerModel viewManagerModel) {
        this.userProfileViewModel = userProfileViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void prepareUserProfileView() {
        // On success, switch to the login view.
        UserProfileState userProfileState = userProfileViewModel.getState();
        this.userProfileViewModel.setState(userProfileState);
        userProfileViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(userProfileViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
        System.out.println("We swapped to UserProfileView" + userProfileViewModel.getViewName());
    }
//    public void prepareSignUpView() {
//        // On success, switch to the login view.
//
//        // quick check to make sure things work - to be removed
//        SignupState SignUpState = signupViewModel.getState();
//        this.signupViewModel.setState(SignUpState);
//        signupViewModel.firePropertyChanged();
//
//        viewManagerModel.setActiveView(signupViewModel.getViewName());
//        viewManagerModel.firePropertyChanged();
//    }
}
