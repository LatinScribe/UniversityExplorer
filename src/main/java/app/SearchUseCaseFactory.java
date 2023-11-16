// Author: André

package app;

import entity.UniversityFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.search.SearchController;
import interface_adapter.search.SearchPresenter;
import interface_adapter.search.SearchViewModel;
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
            ViewManagerModel viewManagerModel, SearchViewModel searchViewModel, SearchUserDataAccessInterface searchUserDataAccessObject, UniversityFactory shortUniversityFactory) {

        try {
            SearchController searchController = createSearchUseCase(viewManagerModel, searchViewModel, searchUserDataAccessObject, shortUniversityFactory);
            return new SearchView(searchController, searchViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static SearchController createSearchUseCase(ViewManagerModel viewManagerModel, SearchViewModel signupViewModel, SearchUserDataAccessInterface userDataAccessObject, UniversityFactory shortUniversityFactory) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        SearchOutputBoundary searchOutputBoundary = new SearchPresenter(viewManagerModel, signupViewModel);

        SearchInputBoundary searchSignupInteractor = new SearchInteractor(
                userDataAccessObject, searchOutputBoundary, shortUniversityFactory);

        return new SearchController(searchSignupInteractor);
    }

}
