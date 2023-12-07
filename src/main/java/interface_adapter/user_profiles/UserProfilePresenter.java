package interface_adapter.user_profiles;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.main_menu.MainMenuState;
import interface_adapter.main_menu.MainMenuViewModel;
import use_case.user_profile.UserProfileOutputBoundary;
import use_case.user_profile.UserProfileOutputData;

/**
 * Presenter class for the user profile, implementing the UserProfileOutputBoundary.
 * This class is responsible for taking user profile data from the use case layer,
 * converting it to a view-friendly format, and updating the user profile view model.
 */
public class UserProfilePresenter implements UserProfileOutputBoundary {

    private final ViewManagerModel viewManagerModel;

    private final UserProfileViewModel userProfileViewModel;

    private final LoggedInViewModel loggedInViewModel;



    /**
     * Constructs a UserProfilePresenter with specified view manager model, user profile view model,
     * and logged-in view model.
     *
     * @param viewManagerModel The view manager model to manage different views.
     * @param userProfileViewModel The view model for user profile-related data and operations.
     * @param loggedInViewModel The view model for the logged-in user state and operations.
     */
    public UserProfilePresenter(ViewManagerModel viewManagerModel, UserProfileViewModel userProfileViewModel, LoggedInViewModel loggedInViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.userProfileViewModel = userProfileViewModel;

    }


    /**
     * Presents the user profile data by updating the user profile view model.
     *
     * @param userProfileOutputData The data to be presented in the user profile view.
     */
    @Override
    public void presentUserProfile(UserProfileOutputData userProfileOutputData) {
        // Convert output data to view model state and update the view model
        UserProfileState state = convertToViewState(userProfileOutputData);
        userProfileViewModel.setState(state);

        // Notify the view to update
        userProfileViewModel.firePropertyChanged();
    }

    /**
     * Converts the provided UserProfileOutputData to a UserProfileState suitable for the view model.
     *
     * @param outputData The output data from the use case layer.
     * @return UserProfileState The state to be set in the user profile view model.
     */
    private UserProfileState convertToViewState(UserProfileOutputData outputData) {
        UserProfileState state = new UserProfileState();
        // Map fields from outputData to state
        state.setFinAidRequirement(outputData.getFinAidRequirement());
        state.setAvgSalary(outputData.getAvgSalary());
        state.setLocationPreference(outputData.getLocationPreference());
        state.setPreferredProgram(outputData.getPreferredProgram());
        state.setUniversityRankingRange(outputData.getUniversityRankingRange());
        return state;
    }

    /**
     * Prepares the main menu view by updating the logged-in view model and view manager model.
     */
    public void prepareMainMenuView() {
        // On success, go back to the main menu
        LoggedInState loggedInState = loggedInViewModel.getState();
        this.loggedInViewModel.setState(loggedInState);
        this.loggedInViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(loggedInViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
