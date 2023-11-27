// Author: Henry
package use_case.login;

import data_access.TokenDataAccessInterface;
import entity.ExistingUser;
import entity.User;

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
    @Override
    // added return to main menu (to be changed)
    public void returnMainMenu() {
        loginPresenter.prepareMainMenuView();
    }
}