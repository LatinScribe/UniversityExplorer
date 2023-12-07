package interface_adapter.sub_menu;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * A class which contains a SubViewState and methods to use in the SubView
 * in order to show the chosen view
 * @author Diego
 */
public class SubViewModel extends ViewModel {
    // For view model: make 2 buttons, one is get recommendation the other search for uni
    public static final String TITLE_LABEL = "I Want To...";
    public static final String RECOMMENDATION_BUTTON_LABEL = "Get University Recommendations";

    public static final String SEARCH_BUTTON_LABEL = "Search Universities by Name";

    public static final String ZIP_SEARCH_BUTTON_LABEL = "Search Universities by ZIP Code";

    private SubViewState state = new SubViewState();

    /**
     * Instantiates the SubViewModel
     */
    public SubViewModel() {
        super("sub menu");
    }

    /**
     * Sets the SubViewState
     * @param state
     */
    public void setState(SubViewState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Alerts the SubView that it is the new active view.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("clear", null, this.state);
    }

    /**
     * This method adds a PropertyChangeListener to be alerted with a propertyChanged method.
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Returns the SubViewState
     * @return SubViewState
     */
    public SubViewState getState() {
        return state;
    }
}
