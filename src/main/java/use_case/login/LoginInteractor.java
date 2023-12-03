package use_case.login;

import data_access.TokenDataAccessInterface;
import entity.ExistingUser;

/**
 * Interactor of the login/sign in use case
 * Should take the given input data and attempt to log in the user
 *
 * @author Henry
 */
public class LoginInteractor implements LoginInputBoundary {
    final LoginUserDataAccessInterface userDataAccessObject;
    final TokenDataAccessInterface tokenDataAccessInterface;
    final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                           TokenDataAccessInterface tokenDataAccessInterface, LoginOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.tokenDataAccessInterface = tokenDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
    }

    /**
     * Method to attempt to log the user in using the input data
     *
     * @param loginInputData Data used to log the user in
     */
    @Override
    public void execute(LoginInputData loginInputData) {
        String username = loginInputData.getUsername();
        String password = loginInputData.getPassword();
        if (!userDataAccessObject.existsByName(username)) {
            loginPresenter.prepareFailView(username + ": Account does not exist.");
        } else {
            try {
                ExistingUser user = userDataAccessObject.get(loginInputData.getUsername(), password);

                // save the token and id
                tokenDataAccessInterface.save_token(user.getID(), user.getToken());

                LoginOutputData loginOutputData = new LoginOutputData(user.getName(), false);
                loginPresenter.prepareSuccessView(loginOutputData);
            } catch (Exception e) {
                loginPresenter.prepareFailView(e.getMessage());
            }
        }
    }

    /**
     * Method to switch view back to main menu
     */
    @Override
    // added return to main menu (to be changed)
    public void returnMainMenu() {
        loginPresenter.prepareMainMenuView();
    }
}