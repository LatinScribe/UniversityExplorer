package interface_adapter.results;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * A class which contains a SearchState (with attributes used by the SearchView) as well as methods that alert
 * the ResultsView on any changes to the active views and the ResultsState.
 * @author Andre
 */
public class ResultsViewModel extends ViewModel {
    public final static String TITLE_LABEL = "Results View";
    public final String Instructions = "Enter search criteria";

    public static final String BACK_BUTTON_LABEL = "Back";
    public static final String CONFIRM_BUTTON_LABEL = "Confirm";

    private ResultsState state = new ResultsState();

    /**
     * Instantiates the ResultsViewModel.
     */
    public ResultsViewModel() {
        super("results");
    }

    /**
     * Sets the ResultsState attribute of the ResultsViewModel object.
     * @param state
     */
    public void setState(ResultsState state) {
        this.state = state;
    }


    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Alerts the ResultsView that it is the new active view.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("change view", null, this.state);
    }

    /**
     * Alerts the ResultsView to create a popup with the University's attributes.
     */
    public void fireUniChanged() {support.firePropertyChange("uni selected", null, this.state);}

    /**
     * Alerts the ResultsView that there has been an error in the query.
     */
    public void fireErrorChanged() {support.firePropertyChange("error", null, this.state);}

    /**
     * This method would add a PropertyChangeListener, which would add an observer which would then be alerted with a
     * propertyChanged method. This was not used for this use case.
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Returns the state attribute of the ResultsViewModel object.
     * @return ResultsState
     */
    public ResultsState getState() {
        return state;
    }
}


