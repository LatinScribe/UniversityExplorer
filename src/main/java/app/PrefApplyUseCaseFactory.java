package app;

import data_access.ProfileDataAccessInterface;
import entity.UniversityFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.prefapply.PrefApplyController;
import interface_adapter.prefapply.PrefApplyPresenter;
import interface_adapter.prefapply.PrefApplyViewModel;
import interface_adapter.sub_menu.SubViewModel;
import use_case.prefapply.PrefApplyDataAccessInterface;
import use_case.prefapply.PrefApplyInputBoundary;
import use_case.prefapply.PrefApplyInteractor;
import use_case.prefapply.PrefApplyOutputBoundary;
import view.PrefApplyview;

import javax.swing.*;
import java.io.IOException;

public class PrefApplyUseCaseFactory {
    public static PrefApplyview create(
            ViewManagerModel viewManagerModel, PrefApplyViewModel prefapplyViewModel, PrefApplyDataAccessInterface prefapplyUserDataAccessObject, UniversityFactory shortUniversityFactory, LoggedInViewModel mainMenuViewModel,ProfileDataAccessInterface profileDataAccessInterface) {

        try {
            PrefApplyController prefapplyController = createPrefApplyUseCase(viewManagerModel, prefapplyViewModel, prefapplyUserDataAccessObject, shortUniversityFactory, mainMenuViewModel,profileDataAccessInterface);
            return new PrefApplyview(prefapplyController, prefapplyViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "API unable to be accessed.");
        }

        return null;
    }

    private static PrefApplyController createPrefApplyUseCase(ViewManagerModel viewManagerModel, PrefApplyViewModel prefapplyViewModel, PrefApplyDataAccessInterface prefapplyDataAccessObject, UniversityFactory shortUniversityFactory, LoggedInViewModel mainMenuViewModel, ProfileDataAccessInterface profileDataAccessInterface) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        PrefApplyOutputBoundary prefapplyOutputBoundary = new PrefApplyPresenter(prefapplyViewModel, viewManagerModel, mainMenuViewModel);

        PrefApplyInputBoundary prefapplyInteractor = new PrefApplyInteractor(
                prefapplyDataAccessObject, prefapplyOutputBoundary, shortUniversityFactory,profileDataAccessInterface);

        return new PrefApplyController(prefapplyInteractor);
    }

}
