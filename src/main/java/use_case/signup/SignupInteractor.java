package use_case.signup;

import data_access.TokenDataAccessInterface;
import entity.*;

import java.time.LocalDateTime;

/**
 * Interactor of the signup use case
 * Should take the given input data and attempt to sign the user up
 *
 * @author Henry
 */
public class SignupInteractor implements SignupInputBoundary {
    final SignupUserDataAccessInterface userDataAccessObject;
    final SignupOutputBoundary userPresenter;
    final CreationUserFactory userFactory;

    final TokenDataAccessInterface tokenDataAccessInterface;

    final PasswordValidator passwordValidator;
    final UsernameValidator usernameValidator;

    public SignupInteractor(SignupUserDataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            CreationUserFactory userFactory, TokenDataAccessInterface tokenDataAccessInterface, PasswordValidator passwordValidator, UsernameValidator usernameValidator) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
        this.tokenDataAccessInterface = tokenDataAccessInterface;
        this.passwordValidator = passwordValidator;
        this.usernameValidator = usernameValidator;
    }

    /**
     * This method attempts to sign the user up with the given data
     *
     * @param signupInputData Given data to use to sign up the user
     */
    @Override
    public void execute(SignupInputData signupInputData) {

        if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        } else if (userDataAccessObject.existsByName(signupInputData.getUsername())) {
            userPresenter.prepareFailView("User already exists.");
        } else if (!passwordValidator.passwordIsValid(signupInputData.getPassword())) {
            userPresenter.prepareFailView(passwordValidator.getRule());
        } else if (!usernameValidator.usernameIsValid(signupInputData.getUsername())) {
            userPresenter.prepareFailView(usernameValidator.getRule());
        } else {

            LocalDateTime now = LocalDateTime.now();
            CreationUser user = userFactory.create(signupInputData.getUsername(), signupInputData.getPassword(), now);
            ExistingUser user1 = userDataAccessObject.save(user);

            // save the token and id somewhere
            tokenDataAccessInterface.save_token(user1.getID(), user1.getToken());

            SignupOutputData signupOutputData = new SignupOutputData(user.getName(), now.toString(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }

    /**
     * Method for switching view back to main menu
     */
    @Override
    // added return to main menu (to be changed)
    public void returnMainMenu() {
        userPresenter.prepareMainMenuView();
    }
}