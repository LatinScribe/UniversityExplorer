// Author: Henry
package use_case.login;

public interface LoginInputBoundary {
    void execute(LoginInputData loginInputData);

    // added return to main menu (to be changed)
    void returnMainMenu();
}
