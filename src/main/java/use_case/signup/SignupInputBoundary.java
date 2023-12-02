// Author: Henry
package use_case.signup;

public interface SignupInputBoundary {
    void execute(SignupInputData signupInputData);

    void returnMainMenu();
}
