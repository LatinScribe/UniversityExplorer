package interface_adapter.search;

import org.junit.Before;
import org.junit.Test;
import use_case.search.SearchInputBoundary;
import use_case.search.SearchInputData;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SearchControllerTest {


        private SearchInputBoundaryStub searchInputBoundaryStub;
        private SearchController searchController;

        @Before
        public void setUp() {
            searchInputBoundaryStub = new SearchInputBoundaryStub();
            searchController = new SearchController(searchInputBoundaryStub);
        }

        @Test
        public void testExecuteSearch() {
            // Arrange
            String searchCriteria = "New York";

            // Act
            searchController.executeSearch(searchCriteria);

            // Assert
            assertTrue(searchInputBoundaryStub.executeSearchCalled);
            assertNotNull(searchInputBoundaryStub.lastExecuteSearchData);
            // You can add more assertions based on your specific requirements
        }

        @Test
        public void testExecuteBack() {
            // Act
            searchController.executeBack();

            // Assert
            assertTrue(searchInputBoundaryStub.executeBackCalled);
            // You can add more assertions based on your specific requirements
        }

        // Stub class for SearchInputBoundary
        private static class SearchInputBoundaryStub implements SearchInputBoundary {
            boolean executeSearchCalled = false;
            boolean executeBackCalled = false;
            SearchInputData lastExecuteSearchData;

            @Override
            public void executeSearch(SearchInputData searchInputData) {
                executeSearchCalled = true;
                lastExecuteSearchData = searchInputData;
            }

            @Override
            public void executeBack() {
                executeBackCalled = true;
            }
        }

}
