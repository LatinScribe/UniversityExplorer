package interface_adapter.user_profiles;

import entity.User;
import interface_adapter.ViewManagerModel;
import use_case.user_profile.UserProfileOutputBoundary;
import use_case.user_profile.UserProfileOutputData;

public class UserProfilePresenter implements UserProfileOutputBoundary {

    private final ViewManagerModel viewManagerModel;

    private final UserProfileViewModel userProfileViewModel;

    // TODO: Need to add other views and viewmodels here


    public UserProfilePresenter(ViewManagerModel viewManagerModel, UserProfileViewModel userProfileViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.userProfileViewModel = userProfileViewModel;

    }

    private UserProfileOutputData getCurrentUserProfileData() {
        return null;
        // TODO: Implement Henry's data storage and retrieval so that user data can be accessed remotely

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

    @Override
    public void presentProfileEditConfirmation(boolean isSuccess, String message) {

    }

    @Override
    public void presentProfileViewError(String message) {

    }
}
