package use_case.sub_menu;

import interface_adapter.sub_menu.SubViewPresenter;
import use_case.signup.SignupOutputBoundary;

import java.util.Objects;

public class SubViewInteractor implements SubViewInputBoundary{
    final SubViewOutputBoundary userPresenter;

    public SubViewInteractor(SubViewOutputBoundary subViewOutputBoundary) {
        this.userPresenter = subViewOutputBoundary;
    }

    public void execute(SubViewInputData subViewInputData) {
        if (Objects.equals(subViewInputData.getNext_panel(), "search")) {
            userPresenter.prepareSearchView();
        } else if (Objects.equals(subViewInputData.getNext_panel(), "recommendation")) {
            userPresenter.prepareApplyView();
        } else if (Objects.equals(subViewInputData.getNext_panel(), "zip_search")) {
            userPresenter.prepareZipSearchView();
        }
    }
}