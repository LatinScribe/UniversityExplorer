package use_case.user_profile;

public interface UserProfileOutputBoundary {

    void prepareSuccessView(UserProfileOutputData user);

    void prepareFailView(String error);
}
