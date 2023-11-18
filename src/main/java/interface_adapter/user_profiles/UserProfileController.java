package interface_adapter.user_profiles;

import use_case.user_profile.UserProfileInputBoundary;

public class UserProfileController {

    final UserProfileInputBoundary userProfileInputBoundary;

    public UserProfileController(UserProfileInputBoundary userProfileInputBoundary) {
        this.userProfileInputBoundary = userProfileInputBoundary;
    }

    public void execute(String searchCriteria) {
        return;
    }

    public void switchToEditProfile() {
    }

    public void switchToPersonalProfile() {
    }
}
