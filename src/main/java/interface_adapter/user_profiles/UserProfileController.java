package interface_adapter.user_profiles;

import use_case.user_profile.UserProfileInputBoundary;
import use_case.user_profile.UserProfileInteractor;
import use_case.user_profile.UserProfileOutputBoundary;

public class UserProfileController {

    final UserProfileInputBoundary userProfileInteractor;

    private UserProfileState userProfileState;
    private final UserProfileViewModel userProfileViewModel;

    public UserProfileController(UserProfileViewModel userProfileViewModel, UserProfileInputBoundary userProfileInteractor) {
        this.userProfileViewModel = userProfileViewModel;
        this.userProfileInteractor = userProfileInteractor;
        initializeState();

    }

    private void initializeState() {
        // TODO: Need to check if user profile exists and load based on that - @Henry
        userProfileState = new UserProfileState();
        userProfileState.setAvgSalary(0);
        userProfileState.setFinAidRequirement(0);
        userProfileState.setLocationPreference(null);
        userProfileState.setPreferredProgram(null);
        userProfileState.setUniversityRankingRange(null);
    }

    public void updateUserProfile(Integer finAidRequirement, String preferredProgram,
                                  Integer avgSalary, Integer[] universityRankingRange,
                                  String locationPreference) {
        // Update the state
        userProfileState.setFinAidRequirement(finAidRequirement);
        userProfileState.setPreferredProgram(preferredProgram);
        userProfileState.setAvgSalary(avgSalary);
        userProfileState.setUniversityRankingRange(universityRankingRange);
        userProfileState.setLocationPreference(locationPreference);

        // Notify the view model
        userProfileViewModel.updateState(userProfileState);
    }

    public void execute(String searchCriteria) {
        return;
    }

    // TODO - include a way for the user to save and return university IDs
}
