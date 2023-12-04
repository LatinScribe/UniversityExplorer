package interface_adapter.logged_in;

import use_case.logged_in.LoggedInInputBoundary;

public class LoggedInController {

    final LoggedInInputBoundary loggedInInputBoundary;

    public LoggedInController(LoggedInInputBoundary loggedInInputBoundary) {
        this.loggedInInputBoundary = loggedInInputBoundary;
    }

    public void swapToUserProfileView() {
        loggedInInputBoundary.showUserProfileView();
    }

    public void prepareUserPrefApplyView() {
        loggedInInputBoundary.prepareUserPrefApplyView();
    }

    public void swapToSearchView() {loggedInInputBoundary.showSearchView();}
    public void logOutUser() {
        loggedInInputBoundary.logOut();
    }
//    public void returnMainMenu() {
//        userSignupUseCaseInteractor.returnMainMenu();
//    }

}
