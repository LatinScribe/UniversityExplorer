// Author: Henry
package app;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.main_menu.MainMenuController;
import interface_adapter.main_menu.MainMenuPresenter;
import interface_adapter.main_menu.MainMenuViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.sub_menu.SubViewModel;
import use_case.main_menu.MainMenuInputBoundary;
import use_case.main_menu.MainMenuInteractor;
import use_case.main_menu.MainMenuOutputBoundary;
import view.MainMenuView;

public class MainMenuUseCaseFactory {
    /** Prevent instantiation. */
    private MainMenuUseCaseFactory() {}

    public static MainMenuView create(MainMenuViewModel mainMenuViewModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel, SubViewModel subViewModel,ViewManagerModel viewManagerModel) {
        MainMenuController mainMenuController = createMainMenuUseCase(signupViewModel, loginViewModel, subViewModel, viewManagerModel);
        return new MainMenuView(mainMenuViewModel, mainMenuController);
    }

    private static MainMenuController createMainMenuUseCase(SignupViewModel signupViewModel, LoginViewModel loginViewModel, SubViewModel subViewModel, ViewManagerModel viewManagerModel) {

        // Notice how we pass this method's parameters to the Presenter.
        MainMenuOutputBoundary mainMenuOutputBoundary = new MainMenuPresenter(signupViewModel, loginViewModel, subViewModel, viewManagerModel);

        MainMenuInputBoundary mainMenuInputBoundary = new MainMenuInteractor(mainMenuOutputBoundary);

        return new MainMenuController(mainMenuInputBoundary);
    }
}
