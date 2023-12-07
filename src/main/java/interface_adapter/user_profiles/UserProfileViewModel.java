//Author: Kanish
package interface_adapter.user_profiles;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class UserProfileViewModel extends ViewModel {

    public final String TITLE_LABEL = "User Profile View";
    public final String USERNAME_LABEL = "Enter Username";
    public final String PASSWORD_LABEL = "Enter Password";


    public static final String PROFILE_BUTTON_LABEL = "View Your Profile";

    public static final String EDIT_BUTTON_LABEL = "Edit Profile";

    public static final String SAVE_BUTTON_LABEL = "Save";

    private UserProfileState state = new UserProfileState();


    /**
     * ViewModel for the User Profile View.
     * This class holds the state and data necessary for displaying and interacting with the user profile in the UI.
     * It extends the ViewModel class, allowing for property change support to update views when the state changes.
     */
    public UserProfileViewModel(String viewName) {
        super("userProfileView");
    }

    /**
     * Sets the current state of the user profile.
     *
     * @param state The new state to be set for the user profile.
     */
    public void setState(UserProfileState state) {
        this.state = state;
    }

    /**
     * Adds a PropertyChangeListener to the listener list.
     *
     * @param listener The PropertyChangeListener to be added.
     */
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Notifies all listeners about a change in the property 'state'.
     */
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Adds a PropertyChangeListener to the listener list.
     *
     * @param listener The PropertyChangeListener to be added.
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Gets the current state of the user profile.
     *
     * @return The current UserProfileState.
     */
    public UserProfileState getState() {
        return state;
    }
}
