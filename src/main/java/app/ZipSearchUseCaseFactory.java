package app;

import entity.CommonUniversityFactory;
import entity.UniversityFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.results.ResultsViewModel;
import interface_adapter.sub_menu.SubViewModel;
import interface_adapter.zip_search.ZipSearchController;
import interface_adapter.zip_search.ZipSearchPresenter;
import interface_adapter.zip_search.ZipSearchViewModel;
import use_case.zip_search.ZipSearchInputBoundary;
import use_case.zip_search.ZipSearchInteractor;
import use_case.zip_search.ZipSearchOutputBoundary;
import use_case.zip_search.ZipSearchUserDataAccessInterface;
import view.ZipSearchView;

import javax.swing.*;
import java.io.IOException;

/**
 * Creates ZipSearchViews
 * @author Diego
 */
public class ZipSearchUseCaseFactory {
    /**
     * Prevent instantiation.
     */
    private ZipSearchUseCaseFactory() {
    }

    /**
     * This method creates the ZipSearchView
     * @param viewManagerModel
     * @param zipSearchViewModel
     * @param subViewModel
     * @param resultsViewModel
     * @param zipSearchUserDataAccessObject
     * @return
     */
    public static ZipSearchView create(
            ViewManagerModel viewManagerModel, ZipSearchViewModel zipSearchViewModel, SubViewModel subViewModel, ResultsViewModel resultsViewModel, ZipSearchUserDataAccessInterface zipSearchUserDataAccessObject) {

        try {
            ZipSearchController zipSearchController = createZipSearchUseCase(viewManagerModel, zipSearchViewModel, subViewModel, resultsViewModel, zipSearchUserDataAccessObject);
            return new ZipSearchView(zipSearchController, zipSearchViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "API unable to be accessed.");
            return null;
        }
    }

    /**
     * Creates the ZipSearchController needed for the ZipSearchView
     * @param viewManagerModel
     * @param zipSearchViewModel
     * @param subViewModel
     * @param resultsViewModel
     * @param zipSearchUserDataAccessObject
     * @return
     * @throws IOException
     */
    private static ZipSearchController createZipSearchUseCase(ViewManagerModel viewManagerModel, ZipSearchViewModel zipSearchViewModel, SubViewModel subViewModel, ResultsViewModel resultsViewModel, ZipSearchUserDataAccessInterface zipSearchUserDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        // Creating a new factory.
        UniversityFactory universityFactory = new CommonUniversityFactory();
        ZipSearchOutputBoundary zipSearchOutputBoundary = new ZipSearchPresenter(viewManagerModel, zipSearchViewModel, resultsViewModel, subViewModel);

        ZipSearchInputBoundary ZipSearchInteractor = new ZipSearchInteractor(
                zipSearchUserDataAccessObject, zipSearchOutputBoundary, universityFactory);

        return new ZipSearchController(ZipSearchInteractor);
    }
}
