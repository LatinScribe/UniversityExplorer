package interface_adapter.prefapply;

import interface_adapter.ViewModel;
import interface_adapter.apply.ApplyState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PrefApplyViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Application Form";
    public static final String ACT_LABEL = "Enter ACT score";
    public static final String SAT_LABEL = "Enter your SAT score";
    public static final String SUBMIT_BUTTON_LABEL = "Submit";
    public static final String BACK_BUTTON_LABEL = "Back";
    private PrefApplyState state = new PrefApplyState();
    public void setState(PrefApplyState state){this.state=state;}


    public PrefApplyViewModel() {
        super("PrefSubmit form");
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Apply Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }
    public void exchangechange(){ support.firePropertyChange("success", null, this.state);

    }
    public void fireFailChange() {
        support.firePropertyChange("Error", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public PrefApplyState getState() {return state;}
}
