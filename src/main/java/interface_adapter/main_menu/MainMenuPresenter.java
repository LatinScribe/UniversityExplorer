// Author: Henry

package interface_adapter.main_menu;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.sub_menu.SubViewModel;
import interface_adapter.sub_menu.SubViewState;
import use_case.main_menu.MainMenuOutputBoundary;

public class MainMenuPresenter implements MainMenuOutputBoundary {
    private final SignupViewModel signupViewModel;
    private final LoginViewModel loginViewModel;
    private final SubViewModel subViewModel;
    private ViewManagerModel viewManagerModel;

    public MainMenuPresenter(SignupViewModel signupViewModel, LoginViewModel loginViewModel, SubViewModel subViewModel, ViewManagerModel viewManagerModel) {
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
        this.subViewModel = subViewModel;
        this.viewManagerModel = viewManagerModel;
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
    @Override
    public void prepareSignUpView() {
        // On success, switch to the login view.

        // quick check to make sure things work - to be removed
        SignupState SignUpState = signupViewModel.getState();
        this.signupViewModel.setState(SignUpState);
        signupViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(signupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
    @Override
    public void prepareSubView() {
        // On success, switch to the login view.

        // quick check to make sure things work - to be removed
        SubViewState subViewState = subViewModel.getState();
        this.subViewModel.setState(subViewState);
        subViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(subViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
