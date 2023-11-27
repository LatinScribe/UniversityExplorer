package app;

import entity.CommonUniversityFactory;
import entity.UniversityFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.results.ResultsController;
import interface_adapter.results.ResultsPresenter;
import interface_adapter.results.ResultsState;
import interface_adapter.results.ResultsViewModel;
import interface_adapter.search.SearchController;
import interface_adapter.search.SearchPresenter;
import interface_adapter.search.SearchViewModel;
import interface_adapter.sub_menu.SubViewModel;
import use_case.results.ResultsInputBoundary;
import use_case.results.ResultsInteractor;
import use_case.results.ResultsOutputBoundary;
import use_case.results.ResultsUserDataAccessInterface;
import use_case.search.SearchInputBoundary;
import use_case.search.SearchInteractor;
import use_case.search.SearchOutputBoundary;
import use_case.search.SearchUserDataAccessInterface;
import view.ResultsView;
import view.SearchView;

import javax.swing.*;
import javax.xml.transform.Result;
import java.io.IOException;
import java.util.Collections;

public class ResultsUseCaseFactory {
    private ResultsUseCaseFactory() {}

    public static ResultsView create(
            ViewManagerModel viewManagerModel, ResultsViewModel resultsViewModel, SearchViewModel searchViewModel, ResultsUserDataAccessInterface resultsUserDataAccessInterface) {

        try {
            ResultsController resultsController = createResultsUseCase(viewManagerModel, resultsViewModel, searchViewModel, resultsUserDataAccessInterface);
            return new ResultsView(resultsController, resultsViewModel, Collections.emptyList());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "API unable to be accessed.");
            return null;
        }
    }

    private static ResultsController createResultsUseCase (ViewManagerModel viewManagerModel, ResultsViewModel resultsViewModel, SearchViewModel searchViewModel, ResultsUserDataAccessInterface resultsUserDataAccessInterface) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        // Creating a new factory.
        UniversityFactory universityFactory = new CommonUniversityFactory();
        ResultsOutputBoundary resultsOutputBoundary = new ResultsPresenter(viewManagerModel, resultsViewModel, searchViewModel);

        ResultsInputBoundary resultsInteractor = new ResultsInteractor(
                resultsUserDataAccessInterface, resultsOutputBoundary, universityFactory);

        return new ResultsController(resultsInteractor);
    }
}