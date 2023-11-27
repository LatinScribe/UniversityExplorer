// Author: André

package interface_adapter.results;

import interface_adapter.ViewManagerModel;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;
import interface_adapter.sub_menu.SubViewModel;
import interface_adapter.sub_menu.SubViewState;
import use_case.results.ResultsOutputBoundary;
import use_case.results.ResultsOutputData;
import use_case.search.SearchOutputData;
import view.ResultsView;
import view.SearchView;

public class ResultsPresenter implements ResultsOutputBoundary {
    private ViewManagerModel viewManagerModel;
    private ResultsViewModel resultsViewModel;
    private SearchViewModel searchViewModel;


    public ResultsPresenter(ViewManagerModel viewManagerModel, ResultsViewModel resultsViewModel, SearchViewModel searchViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.resultsViewModel = resultsViewModel;
        this.searchViewModel = searchViewModel;
    }

    @Override
    public void prepareUniPopup(ResultsOutputData response) {
        ResultsState resultsState = resultsViewModel.getState();
        resultsState.setChosenUniversity(response.getUniversity());
        this.resultsViewModel.setState(resultsState);
        this.resultsViewModel.fireUniChanged();
    }

    public void prepareFailView(String error) {
        ResultsState resultsState = resultsViewModel.getState();
        resultsState.setSearchError(error);
        this.resultsViewModel.setState(resultsState);
        this.resultsViewModel.fireErrorChanged();
    }

    @Override
    public void prepareBackView() {
        SearchState searchState = searchViewModel.getState();
        this.searchViewModel.setState(searchState);
        this.searchViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(searchViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
