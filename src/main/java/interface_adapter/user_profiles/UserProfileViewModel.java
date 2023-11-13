//Author: Kanish
package interface_adapter.user_profiles;

import interface_adapter.ViewModel;
import interface_adapter.main_menu.MainMenuState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class UserProfileViewModel extends ViewModel {

    public final String TITLE_LABEL = "User Profile View";
    public final String USERNAME_LABEL = "Enter Username";
    public final String PASSWORD_LABEL = "Enter Password";


    public static final String PROFILE_BUTTON_LABEL = "View Your Profile";

    public static final String EDIT_BUTTON_LABEL = "Edit Profile";

    public static final String RETURN_TO_LOGGEDIN = "Return to Main Menu ";

    private UserProfileState state = new UserProfileState();
    public UserProfileViewModel() {
        super("userProfileView");
    }

    public void setState(UserProfileState state) {this.state = state;}

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public UserProfileState getState() {
        return state;
    }

    public void updateState(UserProfileState userProfileState) {
        this.state = userProfileState;
    }
}
