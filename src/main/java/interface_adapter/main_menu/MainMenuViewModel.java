// Author: Henry
package interface_adapter.main_menu;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MainMenuViewModel extends ViewModel {

    public final String TITLE_LABEL = "Log In View";
    public final String USERNAME_LABEL = "Enter username";
    public final String PASSWORD_LABEL = "Enter password";

    public static final String SIGNUP_BUTTON_LABEL = "Sign up";
    public static final String GUEST_BUTTON_LABEL = "Continue as Guest";
    public static final String SIGNIN_BUTTON_LABEL = "Sign in";
    public static final String SETTINGS_BUTTON_LABEL = "Settings";

    private MainMenuState state = new MainMenuState();

    public MainMenuViewModel() {
        super("Main Menu");
    }

    public void setState(MainMenuState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Signup Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public MainMenuState getState() {
        return state;
    }
}
