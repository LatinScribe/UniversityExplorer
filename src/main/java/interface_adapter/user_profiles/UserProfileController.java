package interface_adapter.user_profiles;

import interface_adapter.ViewManagerModel;
import use_case.user_profile.MockUserProfileInputBoundary;
import use_case.user_profile.UserProfileInputBoundary;
import use_case.user_profile.UserProfileOutputData;
import view.ViewManager;

import java.lang.reflect.Array;

public class UserProfileController {

    final UserProfileInputBoundary userProfileInputBoundary;

    public UserProfileController(UserProfileInputBoundary userProfileInputBoundary) {
        this.userProfileInputBoundary = userProfileInputBoundary;
        // Takes in a UserProfileInteractor, which implements the Input Boundary
    }

    public void execute(String searchCriteria) {
        return;
    }

    public void updateUserProfile(int finAidRequirement, int avgSalary, String locationPreference,
                                  String preferredProgram, Integer[] universityRankingRange)
            throws IllegalArgumentException {
        // Input validation
        if (finAidRequirement < 0) {
            throw new IllegalArgumentException("Financial aid requirement cannot be negative.");
        }
        if (avgSalary < 0) {
            throw new IllegalArgumentException("Average salary cannot be negative.");
        }
        if (locationPreference == null || locationPreference.trim().isEmpty()) {
            throw new IllegalArgumentException("Location preference cannot be null or empty.");
        }
        if (preferredProgram == null || preferredProgram.trim().isEmpty()) {
            throw new IllegalArgumentException("Preferred program cannot be null or empty.");
        }
        if (universityRankingRange == null || universityRankingRange.length == 0) {
            throw new IllegalArgumentException("University ranking range cannot be null or empty.");
        }

        // Call the use case layer/interactor to update the user profile
        userProfileInputBoundary.updateUserProfile(finAidRequirement, avgSalary, locationPreference,
                preferredProgram, universityRankingRange);
    }

    public void switchToPersonalProfile(int finAidRequirement, int avgSalary, String locationPreference,
                                        String preferredProgram, Integer[] universityRankingRange) {
        UserProfileOutputData userProfileOutputData = new UserProfileOutputData(finAidRequirement, avgSalary, locationPreference,
                preferredProgram, universityRankingRange);
        this.userProfileInputBoundary.showPersonalProfileView(userProfileOutputData);
    }

    public void updateUserProfile(int finAidRequirement, int avgSalary) {
    }

    public void getProfile() {
        userProfileInputBoundary.fetchUserProfileData();
    }
}
