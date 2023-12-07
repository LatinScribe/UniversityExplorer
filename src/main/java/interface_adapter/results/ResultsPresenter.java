package interface_adapter.results;

import entity.University;
import interface_adapter.ViewManagerModel;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;
import interface_adapter.zip_search.ZipSearchState;
import interface_adapter.zip_search.ZipSearchViewModel;
import use_case.results.ResultsOutputBoundary;
import use_case.results.ResultsOutputData;

/**
 * A class that updates the ResultsViewModel and ResultsState based on the output received from the ResultsInteractor.
 * This data is displayed to the user.
 * @author Andre, Diego
 */
public class ResultsPresenter implements ResultsOutputBoundary {
    private ViewManagerModel viewManagerModel;
    private ResultsViewModel resultsViewModel;
    private SearchViewModel searchViewModel;
    private ZipSearchViewModel zipSearchViewModel;

    /**
     * Instantiates the ResultsPresenter.
     * @param viewManagerModel
     * @param resultsViewModel
     * @param searchViewModel
     * @param zipSearchViewModel
     */
    public ResultsPresenter(ViewManagerModel viewManagerModel, ResultsViewModel resultsViewModel, SearchViewModel searchViewModel, ZipSearchViewModel zipSearchViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.resultsViewModel = resultsViewModel;
        this.searchViewModel = searchViewModel;
        this.zipSearchViewModel = zipSearchViewModel;
    }

    /**
     * Prepares the ResultsState and ResultsViewModel with all the names of the universities found.
     * @param response
     */
    @Override
    public void prepareUniPopup(ResultsOutputData response) {
        ResultsState resultsState = resultsViewModel.getState();
        University university = response.getUniversity();
        String uniString = university.toString();
        resultsState.setChosenUniversityString(uniString);
        this.resultsViewModel.setState(resultsState);
        this.resultsViewModel.fireUniChanged();
    }

    /**
     * If there is an error in the query, prepare the ResultsState and ResultsViewModel to report this error.
     * @param error
     */
    public void prepareFailView(String error) {
        ResultsState resultsState = resultsViewModel.getState();
        resultsState.setSearchError(error);
        this.resultsViewModel.setState(resultsState);
        this.resultsViewModel.fireErrorChanged();
    }

    /**
     * Switches the active view to the previous view used (either the SearchView or the ZipSearchView) through alerting
     * their respective view models.
     */
    @Override
    public void prepareBackView() {
        ResultsState resultsState = resultsViewModel.getState();
        if (resultsState.prevView.equals("zip_search")) {
            ZipSearchState zipSearchState = zipSearchViewModel.getState();
            this.zipSearchViewModel.setState(zipSearchState);
            this.zipSearchViewModel.firePropertyChanged();

            viewManagerModel.setActiveView(zipSearchViewModel.getViewName());
            viewManagerModel.firePropertyChanged();
            resultsState.setPreviousView("");
        }
        else {
            SearchState searchState = searchViewModel.getState();
            this.searchViewModel.setState(searchState);
            this.searchViewModel.firePropertyChanged();

            viewManagerModel.setActiveView(searchViewModel.getViewName());
            viewManagerModel.firePropertyChanged();
        }
    }
}
