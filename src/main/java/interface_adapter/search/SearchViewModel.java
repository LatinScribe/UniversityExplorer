package interface_adapter.search;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * A class which contains a SearchState (with attributes needed to alert the View) as well as methods that alert the
 * SearchView on any changes to the active views and the SearchState.
 * @author Andre
 */
public class SearchViewModel extends ViewModel {
    public final static String TITLE_LABEL = "Search View";
    public final String SEARCH_LABEL = "Enter search criteria";

    public static final String SEARCH_BUTTON_LABEL = "Search";
    public static final String BACK_BUTTON_LABEL = "Back";

    private SearchState state = new SearchState();

    /**
     * Instantiates the SearchViewModel.
     */
    public SearchViewModel() {
        super("search");
    }

    /**
     * Sets the SearchState attribute of the SearchViewModel object.
     * @param state
     */
    public void setState(SearchState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Alerts the SearchView that it is the new active view.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("change view", null, this.state);
    }

    /**
     * Alerts the SearchView that there has been an error in the query or a lack of results found.
     */
    public void fireFailChange() {
        support.firePropertyChange("failure", null, this.state);
    }

    /**
     * This method would add a PropertyChangeListener, which would add an observer which would then be alerted with a
     * propertyChanged method. This was not used for this use case.
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Returns the SearchState attribute of the SearchViewModel object.
     * @return SearchState
     */
    public SearchState getState() {
        return state;
    }
}
