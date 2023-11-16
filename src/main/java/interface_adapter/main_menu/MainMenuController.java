// Author: Henry
package interface_adapter.main_menu;

import use_case.main_menu.MainMenuInputBoundary;

public class MainMenuController {
    final MainMenuInputBoundary mainMenuInputBoundary;

    public MainMenuController(MainMenuInputBoundary mainMenuInputBoundary) {
        this.mainMenuInputBoundary = mainMenuInputBoundary;
    }

    public void switchToLoginView() {
        this.mainMenuInputBoundary.showLoginView();
    }

    public void switchToSignupView() {
        this.mainMenuInputBoundary.showSignUpView();
    }

//    public void execute(String username, String password) {
//    }
}
