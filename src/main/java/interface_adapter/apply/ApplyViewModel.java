package interface_adapter.apply;

import interface_adapter.ViewModel;
import interface_adapter.main_menu.MainMenuState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ApplyViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Application Form";
    public static final String USERNAME_LABEL = "Enter username";
    public static final String GPA_LABEL = "Enter your gpa";
    public static final String SUBMIT_BUTTON_LABEL = "Submit";
    private ApplyState state = new ApplyState();
    public void setState(ApplyState state){this.state=state;}


    public ApplyViewModel() {
        super("Submit form");
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

    public ApplyState getState() {return state;}


}