package interface_adapter.user_profiles;

public class UserProfileController {

    private UserProfileState userProfileState;
    private final UserProfileViewModel userProfileViewModel;

    public UserProfileController(UserProfileViewModel userProfileViewModel) {
        this.userProfileViewModel = userProfileViewModel;
        initializeState();

    }

    private void initializeState() {
        // Need to check if user profile exists and load based on that - @Henry
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

}
