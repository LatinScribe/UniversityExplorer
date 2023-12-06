package use_case.user_profile;

public interface UserProfileOutputBoundary {

    void presentUserProfile(UserProfileOutputData userProfileOutputData);

    void presentProfileEditConfirmation(boolean isSuccess, String message);
    void presentProfileViewError(String message);

    void prepareMainMenuView();
}
