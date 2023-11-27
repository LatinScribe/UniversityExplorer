// Author: Diego
package interface_adapter.zip_search;

import interface_adapter.ViewModel;
import interface_adapter.search.SearchState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ZipSearchViewModel extends ViewModel {
    public final static String TITLE_LABEL = "Zip Search View";
    public final String ZIPCODE_LABEL = "Enter Zip Code: ";
    public final String RADIUS_LABEL = "Enter Radius distance in Miles: ";

    public static final String SEARCH_BUTTON_LABEL = "Search";
    public static final String BACK_BUTTON_LABEL = "Back";

    private SearchState state = new SearchState();

    public ZipSearchViewModel() {
        super("zip_search");
    }

    public void setState(SearchState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Signup Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("change view", null, this.state);
    }

    public void fireFailChange() {
        support.firePropertyChange("failure", null, this.state);
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public SearchState getState() {
        return state;
    }
}
