package use_case.logged_in;

import data_access.FileTokenDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.prefapply.PrefApplyViewModel;
import interface_adapter.user_profiles.UserProfileViewModel;
import org.junit.jupiter.api.Test;

class LoggedInInteractorTest {
    FileTokenDataAccessObject tokenDataAccessObject = new FileTokenDataAccessObject();
    UserProfileViewModel userProfileViewModel = new UserProfileViewModel("UserProfile");
    ViewManagerModel viewManagerModel = new ViewManagerModel();
    LoginViewModel loginViewModel = new LoginViewModel();
    PrefApplyViewModel prefApplyViewModel = new PrefApplyViewModel();
    LoggedInPresenter loggedInPresenter = new LoggedInPresenter(userProfileViewModel, viewManagerModel, loginViewModel, prefApplyViewModel);
    LoggedInInteractor loggedInInteractor = new LoggedInInteractor(tokenDataAccessObject, loggedInPresenter);

    @Test
    void showUserProfileView() {
        loggedInInteractor.showUserProfileView();
        assert viewManagerModel.getActiveView().equals("userProfileView");
    }

    @Test
    void prepareUserPrefApplyView() {
        loggedInInteractor.prepareUserPrefApplyView();
        assert viewManagerModel.getActiveView().equals("PrefSubmit form");
    }

    @Test
    void logOut() {
        loggedInInteractor.logOut();
        assert viewManagerModel.getActiveView().equals("log in");
    }
}