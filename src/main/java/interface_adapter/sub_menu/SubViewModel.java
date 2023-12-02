// Author: Diego
package interface_adapter.sub_menu;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SubViewModel extends ViewModel {
    // For view model: make 2 buttons, one is get recommendation the other search for uni
    public static final String TITLE_LABEL = "I Want To...";
    public static final String RECOMMENDATION_BUTTON_LABEL = "Get University Recommendations";

    public static final String SEARCH_BUTTON_LABEL = "Search Universities by Name";

    public static final String ZIP_SEARCH_BUTTON_LABEL = "Search Universities by ZIP Code";

    private SubViewState state = new SubViewState();

    public SubViewModel() {
        super("sub menu");
    }

    public void setState(SubViewState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("clear", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public SubViewState getState() {
        return state;
    }
}
