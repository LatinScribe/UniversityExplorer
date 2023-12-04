package interface_adapter.search;

import entity.CommonUniversity;
import entity.University;
import interface_adapter.ViewManagerModel;
import interface_adapter.ViewModel;
import interface_adapter.results.ResultsState;
import interface_adapter.results.ResultsViewModel;
import interface_adapter.sub_menu.SubViewModel;
import org.junit.Before;
import org.junit.Test;
import use_case.search.SearchOutputData;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SearchPresenterTest {
    private SearchPresenter searchPresenter;
    private ViewManagerModel viewManagerModel;
    private SearchViewModel searchViewModel;
    private ResultsViewModel resultsViewModel;
    private SubViewModel subViewModel;

    @Before
    public void setUp() {
        viewManagerModel = new ViewManagerModel();
        searchViewModel = new SearchViewModel();
        resultsViewModel = new ResultsViewModel();
        subViewModel = new SubViewModel();

        searchPresenter = new SearchPresenter(viewManagerModel, searchViewModel, resultsViewModel, subViewModel);
    }

    @Test
    public void testPrepareSuccessView() {
        // Arrange
        University university = new CommonUniversity(1, "tester", "NY", "New York", 50.3, 5000, 10000, 750, 250, "test.com");
        List<University> universities = new ArrayList<>();
        universities.add(university);
        SearchOutputData searchOutputData = new SearchOutputData(universities);

        //Expected
        List<String> expectedlist = new ArrayList<>();
        expectedlist.add("tester");

        // Act
        searchPresenter.prepareSuccessView(searchOutputData);

        // Assert
        ResultsState state = resultsViewModel.getState();
        assertEquals(expectedlist,state.getUniversityNames());

//        assertTrue(resultsViewModelMock.stateSet);
//        assertTrue(resultsViewModelMock.propertyChangedFired);
//        assertTrue(viewManagerModelMock.activeViewSet);
//        assertTrue(viewManagerModelMock.propertyChangedFired);
    }

    @Test
    public void testPrepareResultsNotFoundView() {
        // Arrange
        String error = "Results not found";

        // Act
        searchPresenter.prepareResultsNotFoundView(error);

        // Assert
//        assertTrue(searchViewModelMock.stateSet);
//        assertTrue(searchViewModelMock.failChangeFired);
    }

    @Test
    public void testPrepareBackView() {
        // Arrange

        // Act
        searchPresenter.prepareBackView();

        // Assert

//        assertTrue(subViewModelMock.stateSet);
//        assertTrue(subViewModelMock.propertyChangedFired);
//        assertTrue(viewManagerModelMock.activeViewSet);
//        assertTrue(viewManagerModelMock.propertyChangedFired);
    }

    // Mock classes


}
