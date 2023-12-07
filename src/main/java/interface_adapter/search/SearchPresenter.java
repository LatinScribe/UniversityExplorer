// Author: André

package interface_adapter.search;

import entity.University;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.results.ResultsState;
import interface_adapter.results.ResultsViewModel;
import interface_adapter.sub_menu.SubViewModel;
import use_case.search.SearchOutputBoundary;
import use_case.search.SearchOutputData;
import interface_adapter.sub_menu.SubViewState;
import view.SubView;

import java.util.ArrayList;
import java.util.List;

public class SearchPresenter implements SearchOutputBoundary {

    private SearchViewModel searchViewModel;
    private ViewManagerModel viewManagerModel;
    private ResultsViewModel resultsViewModel;
    private SubViewModel subViewModel;
    private LoggedInViewModel loggedInViewModel;

    public SearchPresenter(ViewManagerModel viewManagerModel, SearchViewModel searchViewModel, ResultsViewModel resultsViewModel, SubViewModel subViewModel, LoggedInViewModel loggedInViewModel) {
        this.searchViewModel = searchViewModel;
        this.viewManagerModel = viewManagerModel;
        this.resultsViewModel = resultsViewModel;
        this.subViewModel = subViewModel;
        this.loggedInViewModel = loggedInViewModel;
    }

    @Override
    public void prepareSuccessView(SearchOutputData response) {
        ResultsState resultsState = resultsViewModel.getState();
        List<String> names = new ArrayList<String>();
        for (University uni : response.getUniversities()) {
            names.add(uni.getSchoolName());
        }
        resultsState.setUniversityNames(names);
        this.resultsViewModel.setState(resultsState);
        this.resultsViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(resultsViewModel.getViewName());
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
        SearchState searchState = searchViewModel.getState();
        if (searchState.getPrevView().equals("Logged In")) {
            searchState.setPrevView("");
            LoggedInState loggedInState = loggedInViewModel.getState();
            this.loggedInViewModel.setState(loggedInState);
            this.loggedInViewModel.firePropertyChanged();;

            viewManagerModel.setActiveView(loggedInViewModel.getViewName());
            viewManagerModel.firePropertyChanged();
        } else {
            SubViewState subViewState = subViewModel.getState();
            this.subViewModel.setState(subViewState);
            this.subViewModel.firePropertyChanged();

            viewManagerModel.setActiveView(subViewModel.getViewName());
            viewManagerModel.firePropertyChanged();
        }
    }

}