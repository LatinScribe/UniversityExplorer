package use_case.user_profile;

public interface UserProfileOutputBoundary {

    void presentUserProfile(UserProfileOutputData userProfileOutputData);


    void prepareMainMenuView();
}
