// Author: André,

package app;

import entity.CommonUniversityFactory;
import entity.UniversityFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.results.ResultsViewModel;
import interface_adapter.search.SearchController;
import interface_adapter.search.SearchPresenter;
import interface_adapter.search.SearchViewModel;
import interface_adapter.sub_menu.SubViewModel;
import use_case.search.SearchInputBoundary;
import use_case.search.SearchInteractor;
import use_case.search.SearchOutputBoundary;
import use_case.search.SearchUserDataAccessInterface;
import view.SearchView;

import javax.swing.*;
import java.io.IOException;

public class SearchUseCaseFactory {
    /**
     * Prevent instantiation.
     */
    private SearchUseCaseFactory() {
    }

    /**
     * Instantiates the SearchView, as well as calling a function to instantiate the SearchController.
     * @param viewManagerModel
     * @param searchViewModel
     * @param subViewModel
     * @param resultsViewModel
     * @param loggedInViewModel
     * @param searchUserDataAccessObject
     * @return A SearchView object ready for usage in main.
     */
    public static SearchView create(
            ViewManagerModel viewManagerModel, SearchViewModel searchViewModel, SubViewModel subViewModel, ResultsViewModel resultsViewModel, LoggedInViewModel loggedInViewModel, SearchUserDataAccessInterface searchUserDataAccessObject) {

        try {
            SearchController searchController = createSearchUseCase(viewManagerModel, searchViewModel, subViewModel, resultsViewModel, loggedInViewModel, searchUserDataAccessObject);
            return new SearchView(searchController, searchViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "API unable to be accessed.");
            return null;
        }
    }

    /**
     * Instantiates a UniversityFactory, SearchPresenter, SearchInteractor and SearchController.
     * @param viewManagerModel
     * @param signupViewModel
     * @param subViewModel
     * @param resultsViewModel
     * @param loggedInViewModel
     * @param userDataAccessObject
     * @return SearchController to be used for the SearchView.
     * @throws IOException
     */
    private static SearchController createSearchUseCase(ViewManagerModel viewManagerModel, SearchViewModel signupViewModel, SubViewModel subViewModel, ResultsViewModel resultsViewModel, LoggedInViewModel loggedInViewModel, SearchUserDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        // Creating a new factory.
        UniversityFactory universityFactory = new CommonUniversityFactory();
        SearchOutputBoundary searchOutputBoundary = new SearchPresenter(viewManagerModel, signupViewModel, resultsViewModel, subViewModel, loggedInViewModel);

        SearchInputBoundary searchInteractor = new SearchInteractor(
                userDataAccessObject, searchOutputBoundary, universityFactory);

        return new SearchController(searchInteractor);
    }

}
