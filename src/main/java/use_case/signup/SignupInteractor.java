// Author: Henry
package use_case.signup;

import data_access.TokenDataAccessInterface;
import entity.*;

import java.time.LocalDateTime;

public class SignupInteractor implements SignupInputBoundary {
    final SignupUserDataAccessInterface userDataAccessObject;
    final SignupOutputBoundary userPresenter;
    final CreationUserFactory userFactory;

    final TokenDataAccessInterface tokenDataAccessInterface;

    public SignupInteractor(SignupUserDataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            CreationUserFactory userFactory, TokenDataAccessInterface tokenDataAccessInterface) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
        this.tokenDataAccessInterface = tokenDataAccessInterface;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        if (userDataAccessObject.existsByName(signupInputData.getUsername())) {
            userPresenter.prepareFailView("User already exists.");
        } else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        } else {

            LocalDateTime now = LocalDateTime.now();
            CreationUser user = userFactory.create(signupInputData.getUsername(), signupInputData.getPassword(), now);
            ExistingUser user1 = userDataAccessObject.save(user);

            // save the token and id somewhere
            tokenDataAccessInterface.save_token(user1.getID(),user1.getToken());

            SignupOutputData signupOutputData = new SignupOutputData(user.getName(), now.toString(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }

    @Override
    // added return to main menu (to be changed)
    public void returnMainMenu() {
        userPresenter.prepareMainMenuView();
    }
}