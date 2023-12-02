package app;

import data_access.TokenDataAccessInterface;
import entity.CreationCommonUserFactory;
import entity.CreationUserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInController;
import interface_adapter.logged_in.LoggedInPresenter;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.prefapply.PrefApplyViewModel;
import interface_adapter.user_profiles.UserProfileViewModel;
import use_case.logged_in.LoggedInInputBoundary;
import use_case.logged_in.LoggedInInteractor;
import use_case.logged_in.LoggedInOutputBoundary;
import view.LoggedInView;

import javax.swing.*;
import java.io.IOException;

public class LoggedInUseCaseFactory {
    /**
     * Prevent instantiation.
     */
    private LoggedInUseCaseFactory() {
    }


    public static LoggedInView create(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            UserProfileViewModel userProfileViewModel,
            PrefApplyViewModel prefApplyViewModel,
            TokenDataAccessInterface tokenDataAccessInterface) {

        try {
            LoggedInController loginController = createLoginUseCase(viewManagerModel, loginViewModel, userProfileViewModel, prefApplyViewModel, tokenDataAccessInterface);
            return new LoggedInView(loggedInViewModel, userProfileViewModel, loginController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static LoggedInController createLoginUseCase(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
//            LoggedInViewModel loggedInViewModel,
//            MainMenuViewModel mainMenuViewModel,
            UserProfileViewModel userProfileViewModel,
            PrefApplyViewModel prefApplyViewModel,
            TokenDataAccessInterface tokenDataAccessInterface) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        LoggedInOutputBoundary loggedInPresenter = new LoggedInPresenter(userProfileViewModel, viewManagerModel, loginViewModel, prefApplyViewModel);

        CreationUserFactory userFactory = new CreationCommonUserFactory();

        LoggedInInputBoundary loggedInInputBoundary = new LoggedInInteractor(tokenDataAccessInterface, loggedInPresenter);

        return new LoggedInController(loggedInInputBoundary);
    }

}
