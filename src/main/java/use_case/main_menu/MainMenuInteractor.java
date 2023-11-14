// Author: Henry
package use_case.main_menu;

public class MainMenuInteractor implements MainMenuInputBoundary{

    final MainMenuOutputBoundary mainMenuOutputBoundary;

    public MainMenuInteractor(MainMenuOutputBoundary mainMenuOutputBoundary) {
        this.mainMenuOutputBoundary = mainMenuOutputBoundary;
    }

    @Override
    public void showLoginView() {
        this.mainMenuOutputBoundary.prepareLoginView();
    }

    @Override
    public void showSignUpView() {
        this.mainMenuOutputBoundary.prepareSignUpView();
    }
}
