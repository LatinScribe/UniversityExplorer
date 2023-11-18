package app;

import entity.UserPreferencesFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.user_profiles.UserProfilePresenter;
import interface_adapter.user_profiles.UserProfileViewModel;
import interface_adapter.user_profiles.UserProfileController;
import use_case.user_profile.UserProfileDataAccessInterface;
import use_case.user_profile.UserProfileInputBoundary;
import use_case.user_profile.UserProfileInteractor;
import use_case.user_profile.UserProfileOutputBoundary;
import view.UserProfileView;
import view.ViewManager;

import javax.swing.*;
import java.io.IOException;

public class UserProfileUseCaseFactory {

    /**
     * Prevent instantiation.
     */
    private UserProfileUseCaseFactory() {
    }

    private static UserProfileController createUserProfileUseCase(ViewManagerModel viewManager, UserProfileViewModel userProfileViewModel) throws IOException {

//    private static UserProfileController createUserProfileUseCase(ViewManager viewManager, UserProfileViewModel userProfileViewModel,
//            UserProfileDataAccessInterface userProfileDataAccessInterface, UserProfileOutputBoundary userProfileOutputBoundary,
//            UserPreferencesFactory userPreferencesFactory) throws IOException {

        // Assume UserProfilePresenter implements UserProfileOutputBoundary
        // TODO: Remove redundant inputs for createUserProfile - userPreferencesFactory, DAO, etc
        UserProfileOutputBoundary userProfilePresenter = new UserProfilePresenter(viewManager, userProfileViewModel);

        UserProfileInputBoundary userProfileInteractor = new UserProfileInteractor(userProfilePresenter);

        return new UserProfileController(userProfileViewModel, userProfileInteractor);
    }

    public static UserProfileView create(ViewManagerModel viewManager, UserProfileViewModel userProfileViewModel) {

        try {
            UserProfileController userProfileController = createUserProfileUseCase(viewManager, userProfileViewModel);
            return new UserProfileView(userProfileViewModel, userProfileController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }
}