// Author: André,

package app;

import entity.CommonUniversityFactory;
import entity.UniversityFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.results.ResultsViewModel;
import interface_adapter.search.SearchController;
import interface_adapter.search.SearchPresenter;
import interface_adapter.search.SearchViewModel;
import interface_adapter.sub_menu.SubViewModel;
import okhttp3.Call;
import use_case.search.SearchInputBoundary;
import use_case.search.SearchInteractor;
import use_case.search.SearchOutputBoundary;
import use_case.search.SearchUserDataAccessInterface;
import view.SearchView;

import javax.swing.*;
import java.io.IOException;

public class SearchUseCaseFactory {
    /** Prevent instantiation. */
    private SearchUseCaseFactory() {}

    public static SearchView create(
            ViewManagerModel viewManagerModel, SearchViewModel searchViewModel, SubViewModel subViewModel, ResultsViewModel resultsViewModel, SearchUserDataAccessInterface searchUserDataAccessObject) {

        try {
            SearchController searchController = createSearchUseCase(viewManagerModel, searchViewModel, subViewModel, resultsViewModel, searchUserDataAccessObject);
            return new SearchView(searchController, searchViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "API unable to be accessed.");
            return null;
        }
    }

    private static SearchController createSearchUseCase(ViewManagerModel viewManagerModel, SearchViewModel signupViewModel, SubViewModel subViewModel, ResultsViewModel resultsViewModel, SearchUserDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        // Creating a new factory.
        UniversityFactory universityFactory = new CommonUniversityFactory();
        SearchOutputBoundary searchOutputBoundary = new SearchPresenter(viewManagerModel, signupViewModel, resultsViewModel, subViewModel);

        SearchInputBoundary searchInteractor = new SearchInteractor(
                userDataAccessObject, searchOutputBoundary, universityFactory);

        return new SearchController(searchInteractor);
    }

}
