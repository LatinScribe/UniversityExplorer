package use_case.logged_in;

import data_access.TokenDataAccessInterface;

public class LoggedInInteractor implements LoggedInInputBoundary {

    private final TokenDataAccessInterface tokenDataAccessInterface;
    private final LoggedInOutputBoundary loggedInOutputBoundary;

    public LoggedInInteractor(TokenDataAccessInterface tokenDataAccessInterface, LoggedInOutputBoundary loggedInOutputBoundary) {
        this.tokenDataAccessInterface = tokenDataAccessInterface;
        this.loggedInOutputBoundary = loggedInOutputBoundary;
    }

    @Override
    public void showUserProfileView() {
        loggedInOutputBoundary.prepareUserProfileView();
    }

    @Override
    public void prepareUserPrefApplyView() {
        loggedInOutputBoundary.prepareUserPrefApplyView();
    }


    @Override
    public void logOut() {
        tokenDataAccessInterface.remove_token();
        loggedInOutputBoundary.prepareLoginView();
    }
}
