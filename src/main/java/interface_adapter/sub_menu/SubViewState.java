package interface_adapter.sub_menu;

import java.util.List;

/**
 * The SubViewState stores and returns attributes
 * @author Diego
 */
public class SubViewState {
    private String username = "";

    /**
     * Instantiates the SubViewState
     * @param copy
     */
    public SubViewState(SubViewState copy) {
        username = copy.username;
    }

    /**
     * Instantiates the SubViewState without any parameters so that the previous
     * constructor can work properly.
     */
    public SubViewState() {
    }

    /**
     * Returns the username attribute
     * @return
     */
    public String getUsernames() {
        return username;
    }

    /**
     * Sets the username attribute
     * @param usernames
     */
    public void setUsernames(List<String> usernames) {
        this.username = username;
    }
}
