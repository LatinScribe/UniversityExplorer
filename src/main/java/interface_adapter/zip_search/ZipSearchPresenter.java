// Author: Diego
package interface_adapter.zip_search;

import interface_adapter.ViewManagerModel;
import interface_adapter.results.ResultsState;
import interface_adapter.results.ResultsViewModel;
import interface_adapter.sub_menu.SubViewModel;
import interface_adapter.sub_menu.SubViewState;
import use_case.zip_search.ZipSearchOutputBoundary;
import use_case.zip_search.ZipSearchOutputData;

public class ZipSearchPresenter implements ZipSearchOutputBoundary {
    private ZipSearchViewModel zipSearchViewModel;
    private ViewManagerModel viewManagerModel;
    private ResultsViewModel resultsViewModel;
    private SubViewModel subViewModel;

    public ZipSearchPresenter(ViewManagerModel viewManagerModel, ZipSearchViewModel zipSearchViewModel, ResultsViewModel resultsViewModel, SubViewModel subViewModel) {
        this.zipSearchViewModel = zipSearchViewModel;
        this.viewManagerModel = viewManagerModel;
        this.resultsViewModel = resultsViewModel;
        this.subViewModel = subViewModel;
    }

    @Override
    public void prepareSuccessView(ZipSearchOutputData response) {
        ResultsState resultsState = resultsViewModel.getState();
        resultsState.setPreviousView("zip_search");
        resultsState.setUniversities(response.getUniversities());
        this.resultsViewModel.setState(resultsState);
        this.resultsViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(resultsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareResultsNotFoundView(String error) {
        ZipSearchState zipSearchState = zipSearchViewModel.getState();
        zipSearchState.setZipSearchError(error);
        this.zipSearchViewModel.fireFailChange();
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
