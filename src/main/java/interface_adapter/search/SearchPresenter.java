// Author: André

package interface_adapter.search;

import interface_adapter.ViewManagerModel;
import interface_adapter.sub_menu.SubViewModel;
import use_case.search.SearchOutputBoundary;
import use_case.search.SearchOutputData;
import interface_adapter.sub_menu.SubViewState;
import view.SubView;

public class SearchPresenter implements SearchOutputBoundary {

    private SearchViewModel searchViewModel;
    private ViewManagerModel viewManagerModel;
//    private ResultsViewModel resultsViewModel;
    private SubViewModel subViewModel;

    public SearchPresenter(ViewManagerModel viewManagerModel, SearchViewModel searchViewModel, SubViewModel subViewModel) {
        this.searchViewModel = searchViewModel;
        this.viewManagerModel = viewManagerModel;
//        this.resultsViewModel = resultsViewModel;
        this.subViewModel = subViewModel;
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
        this.searchViewModel.fireFailChange();
    }

    @Override
    public void prepareBackView() {
        SubViewState subViewState = subViewModel.getState();
        this.subViewModel.setState(subViewState);
        this.subViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(subViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}