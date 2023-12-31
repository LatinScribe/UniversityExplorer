// Author: Henry
package app;

import data_access.TokenDataAccessInterface;
import entity.CreationCommonUserFactory;
import entity.CreationUserFactory;
import entity.PasswordValidator;
import entity.UsernameValidator;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.main_menu.MainMenuViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupUserDataAccessInterface;
import view.SignupView;

import javax.swing.*;
import java.io.IOException;

public class SignupUseCaseFactory {

    /**
     * Prevent instantiation.
     */
    private SignupUseCaseFactory() {
    }

    /**
     * Factory to create an instance of the signup use case
     *
     * @param viewManagerModel
     * @param loginViewModel
     * @param signupViewModel
     * @param userDataAccessObject
     * @param mainMenuViewModel
     * @param tokenDataAccessInterface
     * @param passwordValidator
     * @param usernameValidator
     * @return Signup view fully populated and ready for use!
     */
    public static SignupView create(
            ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SignupViewModel signupViewModel, SignupUserDataAccessInterface userDataAccessObject, MainMenuViewModel mainMenuViewModel, TokenDataAccessInterface tokenDataAccessInterface, PasswordValidator passwordValidator, UsernameValidator usernameValidator) {

        try {
            SignupController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, loginViewModel, userDataAccessObject, mainMenuViewModel, tokenDataAccessInterface, passwordValidator, usernameValidator);
//            ClearController clearController = createClearUseCase(viewManagerModel, signupViewModel, loginViewModel, userDataAccessObject);
            return new SignupView(signupController, signupViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error creating login model. This could be because there was a user DAO issue");
        }

        return null;
    }

    private static SignupController createUserSignupUseCase(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel, SignupUserDataAccessInterface userDataAccessObject, MainMenuViewModel mainMenuViewModel, TokenDataAccessInterface tokenDataAccessInterface, PasswordValidator passwordValidator, UsernameValidator usernameValidator) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel, mainMenuViewModel);

        CreationUserFactory userFactory = new CreationCommonUserFactory();

        SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory, tokenDataAccessInterface, passwordValidator, usernameValidator);

        return new SignupController(userSignupInteractor);
    }
//    private static ClearController createClearUseCase(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel, SignupUserDataAccessInterface userDataAccessObject) throws IOException {
//
//        // Notice how we pass this method's parameters to the Presenter.
//        ClearOutputBoundary clearOutputBoundary = new ClearPresenter(viewManagerModel, signupViewModel, loginViewModel);;
//        ClearInputBoundary clearInteractor = new ClearInteractor(
//                (ClearUserDataAccessInterface) userDataAccessObject, clearOutputBoundary);
//
//        return new ClearController(clearInteractor);
//    }
}
