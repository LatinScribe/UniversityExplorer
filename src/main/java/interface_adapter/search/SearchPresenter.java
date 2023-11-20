// Author: André

package interface_adapter.search;

import interface_adapter.ViewManagerModel;
import use_case.search.SearchOutputBoundary;
import use_case.search.SearchOutputData;

public class SearchPresenter implements SearchOutputBoundary {

    private SearchViewModel searchViewModel;
    private ViewManagerModel viewManagerModel;
//    private ResultsViewModel resultsViewModel;

    public SearchPresenter(ViewManagerModel viewManagerModel, SearchViewModel searchViewModel) {
        this.searchViewModel = searchViewModel;
        this.viewManagerModel = viewManagerModel;
//        this.resultsViewModel = resultsViewModel;
    }

    @Override
    public void prepareSuccessView(SearchOutputData response) {
        SearchState searchState = searchViewModel.getState();
        searchState.setUniversities(response.getUniversities());
        this.searchViewModel.setState(searchState);
        this.searchViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(searchViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareResultsNotFoundView(String error) {
        SearchState searchState = searchViewModel.getState();
        searchState.setSearchError(error);
        searchViewModel.fireFailChange();
    }
}