package interface_adapter.zip_search;

import entity.University;
import interface_adapter.ViewManagerModel;
import interface_adapter.results.ResultsState;
import interface_adapter.results.ResultsViewModel;
import interface_adapter.sub_menu.SubViewModel;
import interface_adapter.sub_menu.SubViewState;
import use_case.zip_search.ZipSearchOutputBoundary;
import use_case.zip_search.ZipSearchOutputData;

import java.util.ArrayList;
import java.util.List;

/**
 * Receives output from ZipSearchInteractor and updates ZipSearchViewModel and ZipSearchState
 * @author Diego
 */
public class ZipSearchPresenter implements ZipSearchOutputBoundary {
    private ZipSearchViewModel zipSearchViewModel;
    private ViewManagerModel viewManagerModel;
    private ResultsViewModel resultsViewModel;
    private SubViewModel subViewModel;

    /**
     * Instantiates the ZipSearchPresenter
     * @param viewManagerModel
     * @param zipSearchViewModel
     * @param resultsViewModel
     * @param subViewModel
     */
    public ZipSearchPresenter(ViewManagerModel viewManagerModel, ZipSearchViewModel zipSearchViewModel, ResultsViewModel resultsViewModel, SubViewModel subViewModel) {
        this.zipSearchViewModel = zipSearchViewModel;
        this.viewManagerModel = viewManagerModel;
        this.resultsViewModel = resultsViewModel;
        this.subViewModel = subViewModel;
    }

    /**
     * Prepares the ResultsState and ResultsViewModel with all the names of the universities found.
     * Switches the active view to the ResultsView, which is displayed to the user.
     * @param response
     */
    @Override
    public void prepareSuccessView(ZipSearchOutputData response) {
        ResultsState resultsState = resultsViewModel.getState();
        resultsState.setPreviousView("zip_search");
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

    /**
     * If no results are found in the search query or any errors are found in the API,
     * update the ZipSearchState and ZipSearchViewModel in order to report this error.
     * @param error
     */
    @Override
    public void prepareResultsNotFoundView(String error) {
        ZipSearchState zipSearchState = zipSearchViewModel.getState();
        zipSearchState.setZipSearchError(error);
        this.zipSearchViewModel.fireFailChange();
    }

    /**
     * Switches the active view to the SubView
     * through alerting the respective view model.
     */
    @Override
    public void prepareBackView() {
        SubViewState subViewState = subViewModel.getState();
        this.subViewModel.setState(subViewState);
        this.subViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(subViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
