package interface_adapter.logged_in;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.prefapply.PrefApplyState;
import interface_adapter.prefapply.PrefApplyViewModel;
import interface_adapter.user_profiles.UserProfileState;
import interface_adapter.user_profiles.UserProfileViewModel;
import use_case.logged_in.LoggedInOutputBoundary;

public class LoggedInPresenter implements LoggedInOutputBoundary {

    private ViewManagerModel viewManagerModel;
    private final UserProfileViewModel userProfileViewModel;
//    private final LoginViewModel loginViewModel;
    private final LoginViewModel loginViewModel;

    private  final PrefApplyViewModel prefApplyViewModel;

    public LoggedInPresenter(UserProfileViewModel userProfileViewModel, ViewManagerModel viewManagerModel, LoginViewModel logInViewModel, PrefApplyViewModel prefApplyViewModel) {
        this.userProfileViewModel = userProfileViewModel;
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = logInViewModel;
        this.prefApplyViewModel = prefApplyViewModel;
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
        loginState.setPassword("");
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
    @Override
    public void prepareUserPrefApplyView() {
        // On success, switch to the login view.
        PrefApplyState prefApplyState = prefApplyViewModel.getState();
        this.prefApplyViewModel.setState(prefApplyState);
        prefApplyViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(prefApplyViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
        System.out.println("We swapped to PrefapplyView" + prefApplyViewModel.getViewName());
    }
}
