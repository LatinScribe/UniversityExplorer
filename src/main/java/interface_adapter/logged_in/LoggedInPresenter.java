package interface_adapter.logged_in;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.user_profiles.UserProfileState;
import interface_adapter.user_profiles.UserProfileViewModel;
import use_case.logged_in.LoggedInOutputBoundary;

public class LoggedInPresenter implements LoggedInOutputBoundary {

    private ViewManagerModel viewManagerModel;
    private final UserProfileViewModel userProfileViewModel;
//    private final LoginViewModel loginViewModel;
    private final LoginViewModel loginViewModel;

    public LoggedInPresenter(UserProfileViewModel userProfileViewModel, ViewManagerModel viewManagerModel, LoginViewModel logInViewModel) {
        this.userProfileViewModel = userProfileViewModel;
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = logInViewModel;
    }

    @Override
    public void prepareUserProfileView() {
        // On success, switch to the login view.
        UserProfileState userProfileState = userProfileViewModel.getState();
        this.userProfileViewModel.setState(userProfileState);
        userProfileViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(userProfileViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
        System.out.println("We swapped to UserProfileView" + userProfileViewModel.getViewName());
    }
    @Override
    public void prepareLoginView() {
        // On success, switch to the login view.
        LoginState loginState = loginViewModel.getState();
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
