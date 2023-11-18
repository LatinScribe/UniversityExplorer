package use_case.user_profile;

public interface UserProfileOutputBoundary {

    void prepareEditView();

    void prepareProfileView(String error);
}
