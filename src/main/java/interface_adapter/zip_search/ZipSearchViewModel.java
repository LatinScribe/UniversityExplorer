package interface_adapter.zip_search;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * A class which contains a ZipSearchState and methods to use in the ZipSearchView
 * in order to show the view
 * @author Diego
 */
public class ZipSearchViewModel extends ViewModel {
    public final static String TITLE_LABEL = "Zip Search View";
    public static final String ZIPCODE_LABEL = "Enter Zip Code: ";
    public static final String RADIUS_LABEL = "Enter Radius distance in Miles: ";

    public static final String SEARCH_BUTTON_LABEL = "Search";
    public static final String BACK_BUTTON_LABEL = "Back";

    private ZipSearchState state = new ZipSearchState();

    /**
     * Instantiates the ZipSearchViewModel
     */
    public ZipSearchViewModel() {
        super("zip_search");
    }

    /**
     * Sets the ZipSearchState
     * @param state
     */
    public void setState(ZipSearchState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Alerts the ZipSearchView that it is the new active view.
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
     * This method adds a PropertyChangeListener to be alerted with a propertyChanged method.
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Returns the ZipSearchState
     * @return ZipSearchState
     */
    public ZipSearchState getState() {
        return state;
    }
}
