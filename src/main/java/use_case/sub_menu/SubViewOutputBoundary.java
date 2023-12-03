package use_case.sub_menu;

import use_case.signup.SignupOutputData;

public interface SubViewOutputBoundary {
    void prepareSearchView();

    void prepareApplyView();

    void prepareZipSearchView();

    void prepareMainMenuView();
}
