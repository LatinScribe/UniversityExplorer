package app;

import entity.CreationCommonUserFactory;
import entity.CreationUserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.main_menu.MainMenuController;
import interface_adapter.main_menu.MainMenuPresenter;
import interface_adapter.main_menu.MainMenuViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import use_case.main_menu.MainMenuInputBoundary;
import use_case.main_menu.MainMenuInteractor;
import use_case.main_menu.MainMenuOutputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupUserDataAccessInterface;
import view.MainMenuView;

import java.io.IOException;

//package app;
//
//import entity.CreationCommonUserFactory;
//import entity.CreationUserFactory;
//import interface_adapter.ViewManagerModel;
//import interface_adapter.login.LoginViewModel;
//import interface_adapter.main_menu.MainMenuController;
//import interface_adapter.main_menu.MainMenuViewModel;
//import interface_adapter.signup.SignupController;
//import interface_adapter.signup.SignupPresenter;
//import interface_adapter.signup.SignupViewModel;
//import use_case.signup.SignupInputBoundary;
//import use_case.signup.SignupInteractor;
//import use_case.signup.SignupOutputBoundary;
//import use_case.signup.SignupUserDataAccessInterface;
//import view.SignupView;
//
//import javax.swing.*;
//import java.io.IOException;
//
public class MainMenuUseCaseFactory {
    /** Prevent instantiation. */
    private MainMenuUseCaseFactory() {}

    public static MainMenuView create(MainMenuViewModel mainMenuViewModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel, ViewManagerModel viewManagerModel) {
        MainMenuController mainMenuController = createMainMenuUseCase(signupViewModel, loginViewModel, viewManagerModel);
        return new MainMenuView(mainMenuViewModel, mainMenuController);
    }

    private static MainMenuController createMainMenuUseCase(SignupViewModel signupViewModel, LoginViewModel loginViewModel, ViewManagerModel viewManagerModel) {

        // Notice how we pass this method's parameters to the Presenter.
        MainMenuOutputBoundary mainMenuOutputBoundary = new MainMenuPresenter(signupViewModel, loginViewModel, viewManagerModel);

        MainMenuInputBoundary mainMenuInputBoundary = new MainMenuInteractor(mainMenuOutputBoundary);

        return new MainMenuController(mainMenuInputBoundary);
    }
}
