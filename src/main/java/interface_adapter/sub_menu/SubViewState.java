// Author: Diego
package interface_adapter.sub_menu;

import java.util.List;

public class SubViewState {
    private String username = "";

    public SubViewState(SubViewState copy) {
        username = copy.username;
    }

    public SubViewState() {
    }

    public String getUsernames() {
        return username;
    }

    public void setUsernames(List<String> usernames) {
        this.username = username;
    }
}
