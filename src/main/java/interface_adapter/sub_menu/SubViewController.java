// Author: Diego
package interface_adapter.sub_menu;


import use_case.sub_menu.SubViewInputBoundary;
import use_case.sub_menu.SubViewInputData;

import java.util.Objects;

/**
 * A class that creates SubViewInputData and pass it to the SubViewInteractor.
 * then, the interactor executes the change in view.
 * @author Diego
 */
public class SubViewController {

    final SubViewInputBoundary userSubViewUseCaseInteractor;

    /**
     * Instantiates the SubViewController
     * @param userSubViewUseCaseInteractor
     */
    public SubViewController(SubViewInputBoundary userSubViewUseCaseInteractor) {
        this.userSubViewUseCaseInteractor = userSubViewUseCaseInteractor;
    }

    /**
     * Executes the change in views in the SubViewInteractor
     * @param next_panel
     */
    public void execute(String next_panel) {
        SubViewInputData subViewInputData = new SubViewInputData(next_panel);
        userSubViewUseCaseInteractor.execute(subViewInputData);
    }
}
