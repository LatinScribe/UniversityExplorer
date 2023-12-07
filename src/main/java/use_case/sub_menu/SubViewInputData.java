package use_case.sub_menu;

/**
 * A class that returns the next_panel strings in order to change view.
 * @author Diego
 */
public class SubViewInputData {
    final private String next_panel;

    /**
     * Instantiates the SubViewInputData
     * @param next_panel
     */
    public SubViewInputData(String next_panel) {
        this.next_panel = next_panel;
    }

    /**
     * Returns the next_pannel string
     * @return String
     */
    String getNext_panel() {
        return next_panel;
    }
}

