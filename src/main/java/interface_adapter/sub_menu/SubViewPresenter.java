// Author: Diego
package interface_adapter.sub_menu;

import interface_adapter.ViewManagerModel;
import interface_adapter.apply.ApplyState;
import interface_adapter.apply.ApplyViewModel;
import interface_adapter.main_menu.MainMenuState;
import interface_adapter.main_menu.MainMenuViewModel;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;
import interface_adapter.zip_search.ZipSearchState;
import interface_adapter.zip_search.ZipSearchViewModel;
import use_case.sub_menu.SubViewOutputBoundary;

public class SubViewPresenter implements SubViewOutputBoundary {
    private final SearchViewModel searchViewModel;
    private final ApplyViewModel applyViewModel;
    private final ZipSearchViewModel zipSearchViewModel;

    private final MainMenuViewModel mainMenuViewModel;

    private ViewManagerModel viewManagerModel;

    public SubViewPresenter(SearchViewModel searchViewModel, ApplyViewModel applyViewModel, ZipSearchViewModel zipSearchViewModel,
                            MainMenuViewModel mainMenuViewModel, ViewManagerModel viewManagerModel) {
        this.searchViewModel = searchViewModel;
        this.applyViewModel = applyViewModel;
        this.zipSearchViewModel = zipSearchViewModel;
        this.mainMenuViewModel = mainMenuViewModel;
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
        this.applyViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(applyViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    public void prepareZipSearchView() {
        // On success, switch to the zip search view.
        ZipSearchState zipSearchState = zipSearchViewModel.getState();
        this.zipSearchViewModel.setState(zipSearchState);
        this.zipSearchViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(zipSearchViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    public void prepareMainMenuView() {
        // On success, go back to the main menu
        MainMenuState mainMenuState = mainMenuViewModel.getState();
        this.mainMenuViewModel.setState(mainMenuState);
        this.mainMenuViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(mainMenuViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }
}
