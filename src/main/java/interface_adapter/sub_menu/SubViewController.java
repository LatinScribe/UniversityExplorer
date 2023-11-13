// Author: Diego
package interface_adapter.sub_menu;


import use_case.sub_menu.SubViewInputBoundary;
import use_case.sub_menu.SubViewInputData;

import java.util.Objects;

public class SubViewController {

    final SubViewInputBoundary userSubViewUseCaseInteractor;
    public SubViewController(SubViewInputBoundary userSubViewUseCaseInteractor) {
        this.userSubViewUseCaseInteractor = userSubViewUseCaseInteractor;
    }

    public void execute(String next_panel) {
        SubViewInputData subViewInputData = new SubViewInputData(next_panel);
        userSubViewUseCaseInteractor.execute(subViewInputData);
    }
}
