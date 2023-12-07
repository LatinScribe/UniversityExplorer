package app;


import interface_adapter.ViewManagerModel;
import interface_adapter.apply.ApplyViewModel;
import interface_adapter.main_menu.MainMenuViewModel;
import interface_adapter.search.SearchViewModel;
import interface_adapter.sub_menu.SubViewController;
import interface_adapter.sub_menu.SubViewModel;
import interface_adapter.sub_menu.SubViewPresenter;
import interface_adapter.zip_search.ZipSearchViewModel;
import use_case.sub_menu.SubViewInputBoundary;
import use_case.sub_menu.SubViewInteractor;
import use_case.sub_menu.SubViewOutputBoundary;
import view.SubView;

import javax.swing.*;
import java.io.IOException;

/**
 * Creates SubViews
 * @author Diego
 */
public class SubViewUseCaseFactory {

    /**
     * Prevent instantiation.
     */
    private SubViewUseCaseFactory() {
    }

    /**
     * This method creates the SubViews
     * @param viewManagerModel
     * @param searchViewModel
     * @param applyViewModel
     * @param zipSearchViewModel
     * @param mainMenuViewModel
     * @param subViewModel
     * @return
     */
    public static SubView create(
            ViewManagerModel viewManagerModel, SearchViewModel searchViewModel, ApplyViewModel applyViewModel, ZipSearchViewModel zipSearchViewModel,
            MainMenuViewModel mainMenuViewModel, SubViewModel subViewModel) {

        try {
            SubViewController subViewController = createSubViewUseCase(viewManagerModel, searchViewModel, applyViewModel, zipSearchViewModel, mainMenuViewModel);
            return new SubView(subViewModel, subViewController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    /**
     * Creates the SubViewController needed for the SubView
     * @param viewManagerModel
     * @param searchViewModel
     * @param applyViewModel
     * @param zipSearchViewModel
     * @param mainMenuViewModel
     * @return
     * @throws IOException
     */
    private static SubViewController createSubViewUseCase(
            ViewManagerModel viewManagerModel,
            SearchViewModel searchViewModel,
            ApplyViewModel applyViewModel,
            ZipSearchViewModel zipSearchViewModel, MainMenuViewModel mainMenuViewModel) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        SubViewOutputBoundary subViewOutputBoundary = new SubViewPresenter(searchViewModel, applyViewModel, zipSearchViewModel,
                mainMenuViewModel, viewManagerModel);

        SubViewInputBoundary subViewInteractor = new SubViewInteractor(
                subViewOutputBoundary);

        return new SubViewController(subViewInteractor);
    }
}
