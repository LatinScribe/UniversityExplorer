package interface_adapter.user_profiles;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.main_menu.MainMenuState;
import interface_adapter.main_menu.MainMenuViewModel;
import use_case.user_profile.UserProfileOutputBoundary;
import use_case.user_profile.UserProfileOutputData;

public class UserProfilePresenter implements UserProfileOutputBoundary {

    private final ViewManagerModel viewManagerModel;

    private final UserProfileViewModel userProfileViewModel;

    private final LoggedInViewModel loggedInViewModel;




    public UserProfilePresenter(ViewManagerModel viewManagerModel, UserProfileViewModel userProfileViewModel, LoggedInViewModel loggedInViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.userProfileViewModel = userProfileViewModel;

    }



    @Override
    public void presentUserProfile(UserProfileOutputData userProfileOutputData) {
        // Convert output data to view model state and update the view model
        UserProfileState state = convertToViewState(userProfileOutputData);
        userProfileViewModel.setState(state);

        // Notify the view to update
        userProfileViewModel.firePropertyChanged();
    }

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

    public void prepareMainMenuView() {
        // On success, go back to the main menu
        LoggedInState loggedInState = loggedInViewModel.getState();
        this.loggedInViewModel.setState(loggedInState);
        this.loggedInViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(loggedInViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }

    @Override
    public void presentProfileEditConfirmation(boolean isSuccess, String message) {

    }

    @Override
    public void presentProfileViewError(String message) {

    }
}
