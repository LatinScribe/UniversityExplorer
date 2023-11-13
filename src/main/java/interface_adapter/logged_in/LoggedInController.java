package interface_adapter.logged_in;

import use_case.signup.SignupInputData;

public class LoggedInController {

    final LoggedInPresenter loggedInPresenter;

    public LoggedInController(LoggedInPresenter loggedInPresenter) {
        this.loggedInPresenter = loggedInPresenter;
    }

    public void swapToUserProfileView() {
        loggedInPresenter.prepareUserProfileView();
    }
//    public void returnMainMenu() {
//        userSignupUseCaseInteractor.returnMainMenu();
//    }

}
