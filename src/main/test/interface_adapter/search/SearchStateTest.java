package interface_adapter.search;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SearchStateTest {


        @Test
        public void testGettersAndSetters() {
            // Arrange
            SearchState searchState = new SearchState();

            // Act
            searchState.setSearchCriteria("New York");
            searchState.setSearchError("Error");

            // Assert
            assertEquals("New York", searchState.getSearchCriteria());
            assertEquals("Error", searchState.getSearchError());
        }

        @Test
        public void testCopyConstructor() {
            // Arrange
            SearchState originalState = new SearchState();
            originalState.setSearchCriteria("Original Criteria");
            originalState.setSearchError("Original Error");

            // Act
            SearchState copyState = new SearchState(originalState);

            // Assert
            assertEquals("Original Criteria", copyState.getSearchCriteria());
            assertEquals("Original Error", copyState.getSearchError());
        }

        @Test
        public void testToString() {
            // Arrange
            SearchState searchState = new SearchState();
            searchState.setSearchCriteria("ToString Criteria");

            // Act
            String toStringResult = searchState.toString();

            // Assert
            assertTrue(toStringResult.contains("ToString Criteria"));
        }

}
