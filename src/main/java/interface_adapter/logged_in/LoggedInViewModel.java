package interface_adapter.logged_in;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoggedInViewModel extends ViewModel {
    public final String TITLE_LABEL = "Logged In View";

    private LoggedInState state = new LoggedInState();

    private String currentView;

    public static final String LOGOUT_BUTTON_LABEL = "Log out";
    public static final String PROFILE_BUTTON_LABEL = "View your Profile";
    private String loggedInUser;

    public LoggedInViewModel() {
        super("logged in");
    }

    public void setState(LoggedInState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Login Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public LoggedInState getState() {
        return state;
    }


    public String getLoggedInUser() {
        return loggedInUser;
    }

    private void setLoggedInUser(String loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
    // TODO: Check if this is nesscary, maybe set to private?
//    public void setCurrentView(String currentView) {
//        String oldView = this.currentView;
//        this.currentView = currentView;
//        System.out.println("setCurrentView called: It's called " + currentView);
//        support.firePropertyChange("view", oldView, currentView);
//
//        // TODO: Potentially an issue with the property name of userProfileView
//    }
}
