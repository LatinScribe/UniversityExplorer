// Author: Diego
package app;


import interface_adapter.ViewManagerModel;
import interface_adapter.apply.ApplyViewModel;
import interface_adapter.search.SearchViewModel;
import interface_adapter.sub_menu.SubViewController;
import interface_adapter.sub_menu.SubViewModel;
import interface_adapter.sub_menu.SubViewPresenter;
import interface_adapter.zip_search.ZipSearchViewModel;
import use_case.sub_menu.SubViewInputBoundary;
import use_case.sub_menu.SubViewInteractor;
import use_case.sub_menu.SubViewOutputBoundary;
import view.SubView;

import javax.swing.*;
import java.io.IOException;

public class SubViewUseCaseFactory {

    /**
     * Prevent instantiation.
     */
    private SubViewUseCaseFactory() {
    }

    public static SubView create(
            ViewManagerModel viewManagerModel, SearchViewModel searchViewModel, ApplyViewModel applyViewModel, ZipSearchViewModel zipSearchViewModel, SubViewModel subViewModel) {

        try {
            SubViewController subViewController = createSubViewUseCase(viewManagerModel, searchViewModel, applyViewModel, zipSearchViewModel);
            return new SubView(subViewModel, subViewController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static SubViewController createSubViewUseCase(
            ViewManagerModel viewManagerModel,
            SearchViewModel searchViewModel,
            ApplyViewModel applyViewModel,
            ZipSearchViewModel zipSearchViewModel) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        SubViewOutputBoundary subViewOutputBoundary = new SubViewPresenter(searchViewModel, applyViewModel, zipSearchViewModel, viewManagerModel);

        SubViewInputBoundary subViewInteractor = new SubViewInteractor(
                subViewOutputBoundary);

        return new SubViewController(subViewInteractor);
    }
}
