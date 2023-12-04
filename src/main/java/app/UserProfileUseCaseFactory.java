package app;

import data_access.ProfileDataAccessInterface;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.main_menu.MainMenuViewModel;
import interface_adapter.user_profiles.UserProfileController;
import interface_adapter.user_profiles.UserProfilePresenter;
import interface_adapter.user_profiles.UserProfileViewModel;
import use_case.user_profile.UserProfileInteractor;
import view.UserProfileView;

import javax.swing.*;
import java.io.IOException;

public class UserProfileUseCaseFactory {

    private UserProfileUseCaseFactory() {
    }

    private static UserProfileController createUserProfileUseCase(
            ViewManagerModel viewManager,
            UserProfileViewModel userProfileViewModel,
            LoggedInViewModel loggedInViewModel,
            ProfileDataAccessInterface profileDataAccessInterface) throws IOException {

        UserProfilePresenter userProfilePresenter = new UserProfilePresenter(viewManager, userProfileViewModel, loggedInViewModel);
        UserProfileInteractor userProfileInteractor = new UserProfileInteractor(userProfilePresenter, profileDataAccessInterface);

        return new UserProfileController(userProfileInteractor);
    }

    public static UserProfileView create(
            ViewManagerModel viewManager,
            UserProfileViewModel userProfileViewModel,
            ProfileDataAccessInterface profileDataAccessInterface,
            LoggedInViewModel loggedInViewModel) {

        try {
            UserProfileController userProfileController = createUserProfileUseCase(viewManager, userProfileViewModel, loggedInViewModel, profileDataAccessInterface);
            return new UserProfileView(userProfileViewModel, userProfileController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
            return null;
        }
    }
}
