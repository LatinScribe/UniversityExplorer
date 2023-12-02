package app;

import data_access.TokenDataAccessInterface;
import entity.CreationCommonUserFactory;
import entity.CreationUserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.main_menu.MainMenuViewModel;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginUserDataAccessInterface;
import view.LoginView;

import javax.swing.*;
import java.io.IOException;

public class LoginUseCaseFactory {

    /** Prevent instantiation. */
    private LoginUseCaseFactory() {}

    public static LoginView create(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            MainMenuViewModel mainMenuViewModel,
            LoginUserDataAccessInterface userDataAccessObject,
            TokenDataAccessInterface tokenDataAccessInterface) {

        try {
            LoginController loginController = createLoginUseCase(viewManagerModel, loginViewModel, loggedInViewModel, mainMenuViewModel, userDataAccessObject, tokenDataAccessInterface);
            return new LoginView(loginViewModel, loginController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error creating login model. This could be because there was a user DAO issue");
        }

        return null;
    }

    private static LoginController createLoginUseCase(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            MainMenuViewModel mainMenuViewModel,
            LoginUserDataAccessInterface userDataAccessObject, TokenDataAccessInterface tokenDataAccessInterface) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, loggedInViewModel, loginViewModel, mainMenuViewModel);

        CreationUserFactory userFactory = new CreationCommonUserFactory();

        LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, tokenDataAccessInterface, loginOutputBoundary);

        return new LoginController(loginInteractor);
    }
}
