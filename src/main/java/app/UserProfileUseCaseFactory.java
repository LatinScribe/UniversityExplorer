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

    /**
     * Factory class for creating user profile use cases.
     * This class is responsible for setting up and initializing the components needed for the user profile use case,
     * including the controller, presenter, interactor, and view.
     */
    private UserProfileUseCaseFactory() {
    }

    /**
     * Creates and initializes a UserProfileController.
     *
     * @param viewManager The view manager model to manage different views.
     * @param userProfileViewModel The view model for user profile-related data and operations.
     * @param loggedInViewModel The view model for the logged-in user state and operations.
     * @param profileDataAccessInterface The data access interface for user profile operations.
     * @return A UserProfileController instance, ready to be used in the application.
     * @throws IOException If an I/O error occurs during the setup.
     */
    private static UserProfileController createUserProfileUseCase(
            ViewManagerModel viewManager,
            UserProfileViewModel userProfileViewModel,
            LoggedInViewModel loggedInViewModel,
            ProfileDataAccessInterface profileDataAccessInterface) throws IOException {

        UserProfilePresenter userProfilePresenter = new UserProfilePresenter(viewManager, userProfileViewModel, loggedInViewModel);
        UserProfileInteractor userProfileInteractor = new UserProfileInteractor(userProfilePresenter, profileDataAccessInterface);

        return new UserProfileController(userProfileInteractor);
    }

    /**
     * Factory method to create a complete UserProfileView with its required components.
     * This method encapsulates the creation and wiring of UserProfileController,
     * UserProfileViewModel, and other necessary components to create a fully functional
     * UserProfileView.
     *
     * @param viewManager The view manager model.
     * @param userProfileViewModel The user profile view model.
     * @param profileDataAccessInterface The data access interface for user profiles.
     * @param loggedInViewModel The logged-in view model.
     * @return A UserProfileView instance, ready to be used in the application.
     */
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
