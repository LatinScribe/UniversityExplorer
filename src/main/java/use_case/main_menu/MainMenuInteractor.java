package use_case.main_menu;

/**
 * Interactor of the main menu.
 * Contains the methods to switch views on button press
 *
 * @author Henry
 */
public class MainMenuInteractor implements MainMenuInputBoundary {

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

    @Override
    public void showSubView() {
        this.mainMenuOutputBoundary.prepareSubView();
    }

}
