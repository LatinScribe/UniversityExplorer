// Author: André

package interface_adapter.results;

import interface_adapter.ViewModel;
import interface_adapter.search.SearchState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ResultsViewModel extends ViewModel {
    public final static String TITLE_LABEL = "Results View";
    public final String Instructions = "Enter search criteria";

    public static final String BACK_BUTTON_LABEL = "Back";

    private ResultsState state = new ResultsState();

    public ResultsViewModel() {
        super("results");
    }

    public void setState(ResultsState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Signup Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("change view", null, this.state);
    }

    public void fireUniChanged() {support.firePropertyChange("uni selected", null, this.state);}
    public void fireErrorChanged() {support.firePropertyChange("error", null, this.state);}

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public ResultsState getState() {
        return state;
    }
}


