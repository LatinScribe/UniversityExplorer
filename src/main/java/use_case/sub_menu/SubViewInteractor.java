package use_case.sub_menu;

import java.util.Objects;

/**
 * A class which provides the view changes chosen by the user
 * @author Diego
 */
public class SubViewInteractor implements SubViewInputBoundary{
    final SubViewOutputBoundary userPresenter;

    /**
     * The constructor of the SubViewInteractor
     * @param subViewOutputBoundary
     */
    public SubViewInteractor(SubViewOutputBoundary subViewOutputBoundary) {
        this.userPresenter = subViewOutputBoundary;
    }

    /**
     * A use case interactor of the SubView view changes use cases.
     * Calls the presenter to switch the active view to the chosen view.
     * @param subViewInputData
     */
    public void execute(SubViewInputData subViewInputData) {
        if (Objects.equals(subViewInputData.getNext_panel(), "search")) {
            userPresenter.prepareSearchView();
        } else if (Objects.equals(subViewInputData.getNext_panel(), "recommendation")) {
            userPresenter.prepareApplyView();
        } else if (Objects.equals(subViewInputData.getNext_panel(), "zip_search")) {
            userPresenter.prepareZipSearchView();
        } else {
            userPresenter.prepareMainMenuView();
        }
    }
}