// Author: Diego
package interface_adapter.sub_menu;


import java.util.Objects;

public class SubViewController {

    public SubViewController() {
    }

    public void execute(String next_panel, SubViewPresenter subViewPresenter) {
        if (Objects.equals(next_panel, "search")) {
            subViewPresenter.prepareSearchView();
        } else if (Objects.equals(next_panel, "recommendation")) {
            subViewPresenter.prepareApplyView();
        }
    }
}
