// Author: Diego
package interface_adapter.sub_menu;

import interface_adapter.ViewManagerModel;
import interface_adapter.apply.ApplyState;
import interface_adapter.apply.ApplyViewModel;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;

public class SubViewPresenter {
    private final SearchViewModel searchViewModel;
    private final ApplyViewModel applyViewModel;
    private ViewManagerModel viewManagerModel;

    public SubViewPresenter(SearchViewModel searchViewModel, ApplyViewModel applyViewModel, ViewManagerModel viewManagerModel) {
        this.searchViewModel = searchViewModel;
        this.applyViewModel = applyViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void prepareSearchView() {
        // On success, switch to the search view.
        SearchState searchState = searchViewModel.getState();
        this.searchViewModel.setState(searchState);
        searchViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(searchViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
    public void prepareApplyView() {
        // On success, switch to the apply view.
        ApplyState applyState = applyViewModel.getState();
        this.applyViewModel.setState(applyState);
        applyViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(applyViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
