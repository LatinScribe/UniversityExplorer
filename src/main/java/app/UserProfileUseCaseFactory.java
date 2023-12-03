package app;

import data_access.ProfileDataAccessInterface;
import interface_adapter.ViewManagerModel;
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
            ProfileDataAccessInterface profileDataAccessInterface) throws IOException {

        UserProfilePresenter userProfilePresenter = new UserProfilePresenter(viewManager, userProfileViewModel);
        UserProfileInteractor userProfileInteractor = new UserProfileInteractor(userProfilePresenter, profileDataAccessInterface);

        return new UserProfileController(userProfileInteractor);
    }

    public static UserProfileView create(
            ViewManagerModel viewManager,
            UserProfileViewModel userProfileViewModel,
            ProfileDataAccessInterface profileDataAccessInterface) {

        try {
            UserProfileController userProfileController = createUserProfileUseCase(viewManager, userProfileViewModel, profileDataAccessInterface);
            return new UserProfileView(userProfileViewModel, userProfileController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
            return null;
        }
    }
}
