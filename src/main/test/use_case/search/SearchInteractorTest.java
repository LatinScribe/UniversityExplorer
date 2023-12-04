package use_case.search;

import data_access.SearchDataAccessObject;
import entity.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SearchInteractorTest {

    private SearchUserDataAccessStub searchDataAccessStub;
    private SearchOutputBoundaryStub searchPresenterStub;
    private UniversityFactoryStub universityFactoryStub;
    private SearchInteractor searchInteractor;
    @Before
    public void setUp() {
        searchDataAccessStub = new SearchUserDataAccessStub();
        searchPresenterStub = new SearchOutputBoundaryStub();
        universityFactoryStub = new UniversityFactoryStub();
        searchInteractor = new SearchInteractor(searchDataAccessStub, searchPresenterStub, universityFactoryStub);
    }
    @Test
    public void executeSearch_WithResults_ShouldCallSuccessView() {
        // Arrange
        SearchInputData searchInputData = new SearchInputData("New York");
        searchDataAccessStub.setSearchResults(createMockSearchResults(1));


        // Act
        searchInteractor.executeSearch(searchInputData);

        // Assert
        assertTrue(searchPresenterStub.successViewCalled);
        assertFalse(searchPresenterStub.resultsNotFoundViewCalled);
    }
    @Test
    public void testSearchForNewYork_ShouldReturnListOfUniversities() {
        // Arrange
        SearchInputData searchInputData = new SearchInputData("New York");

        // Create a mock JSON response with search results for New York
//        JSONObject mockSearchResults = createMockSearchResultsWithLength(43);
//        searchDataAccessStub.setSearchResults(mockSearchResults);

        // Mock the creation of a University object
//        University mockUniversity = new CommonUniversity(1, "Test University", "NY", "New York City", 0.80, 30000, 45000, 1300.0, 28.0, "http://test.edu");
//        universityFactoryStub.setUniversity(mockUniversity);

        // Act
        searchInteractor.executeSearch(searchInputData);


        // Verify that the presenter was called with a university list of length 43
        assertEquals(20, searchPresenterStub.lastSuccessViewData.getUniversities().size());
    }

    // Helper method to create a mock JSON search result with a specified length
    private JSONObject createMockSearchResultsWithLength(int length) {
        JSONObject result = new JSONObject();
        result.put("metadata", new JSONObject().put("total", length));

        JSONArray resultsArray = new JSONArray();
        for (int i = 0; i < length; i++) {
            JSONObject university = new JSONObject();
            university.put("school.name", "University " + (i + 1));
            resultsArray.put(university);
        }

        result.put("results", resultsArray);

        return result;
    }
    @Test
    public void executeSearch_WithNoResults_ShouldCallResultsNotFoundView() {
        // Arrange
        SearchInputData searchInputData = new SearchInputData("Nonexistent");
        searchDataAccessStub.setSearchResults(createMockSearchResults(0));

        // Act
        searchInteractor.executeSearch(searchInputData);

        // Assert
        assertFalse(searchPresenterStub.successViewCalled);
        assertTrue(searchPresenterStub.resultsNotFoundViewCalled);
    }
//    @Test
//    public void executeSearch_WithJSONException_ShouldCallResultsNotFoundView() {
//        // Arrange
//        SearchInputData searchInputData = new SearchInputData("Invalid JSON");
//        searchDataAccessStub.setThrowJSONException(true);
//
//        // Act
//        searchInteractor.executeSearch(searchInputData);
//
//        // Assert
//        assertFalse(searchPresenterStub.successViewCalled);
//        assertTrue(searchPresenterStub.resultsNotFoundViewCalled);
//    }

    @Test
    public void executeBack_ShouldCallBackView() {
        // Act
        searchInteractor.executeBack();

        // Assert
        assertTrue(searchPresenterStub.backViewCalled);
    }



    private JSONObject createMockSearchResults(int totalResults) {
        JSONObject result = new JSONObject();
        result.put("metadata", new JSONObject().put("total", totalResults));
        result.put("results", new JSONArray());
        return result;
    }

    private static class UniversityFactoryStub implements UniversityFactory {
        private University university;

        public void setUniversity(University university) {
            this.university = university;
        }

        @Override
        public CommonUniversity create(Integer id, String name, String state, String city, Double admRate,
                                       Integer inTuit, Integer outTuit, Integer avgSAT, Integer avgACT, String url) {
            return (CommonUniversity) university;
        }
    }


    private static class SearchUserDataAccessStub implements SearchUserDataAccessInterface {
        private JSONObject searchResults;
        private boolean throwJSONException;

        public void setSearchResults(JSONObject searchResults) {
            this.searchResults = searchResults;
        }

        public void setThrowJSONException(boolean throwJSONException) {
            this.throwJSONException = throwJSONException;
        }


        // testing the query
        @Override
        public JSONObject searchQuery(String searchParameters) {
            System.out.println("searchQuery called with: " + searchParameters);
            SearchDataAccessObject searchDataAccessObject = new SearchDataAccessObject();
            JSONObject query = searchDataAccessObject.searchQuery( searchParameters);
            this.searchResults = query;
            return searchResults;
        }
//        public String saveProfile(UserProfile userProfile) {
//            // Simulated behavior
//            System.out.println("saveProfile called with: " + userProfile);
//            return "Simulated saveProfile success"; // or return null to indicate success
//        }
//
//        public String updateProfile(UserProfile userProfile) {
//            // Simulated behavior
//            System.out.println("updateProfile called with: " + userProfile);
//            return "Simulated updateProfile success"; // or return null to indicate success
//        }
//
//        public UserProfile getProfile() {
//            System.out.println("getProfile called");
//            return new UserPreferences(10000, "Test Program", 50000, new int[]{1, 100}, "Test Location");
//        }
    }

    private static class SearchOutputBoundaryStub implements SearchOutputBoundary {
        private boolean successViewCalled;
        private boolean resultsNotFoundViewCalled;
        private boolean backViewCalled;
        private SearchOutputData lastSuccessViewData;

        @Override
        public void prepareSuccessView(SearchOutputData universities) {
            successViewCalled = true;
            lastSuccessViewData = universities;
        }

        @Override
        public void prepareResultsNotFoundView(String error) {
            resultsNotFoundViewCalled = true;
        }

        @Override
        public void prepareBackView() {
            backViewCalled = true;
        }
//        private boolean presentUserProfileCalled = false;
//        private UserProfileOutputData lastOutputData;
//
//        @Override
//        public void presentUserProfile(UserProfileOutputData userProfileOutputData) {
//            this.lastOutputData = userProfileOutputData;
//            this.presentUserProfileCalled = true;
//        }
//
//        @Override
//        public void presentProfileEditConfirmation(boolean isSuccess, String message) {
//
//        }
//
//        @Override
//        public void presentProfileViewError(String message) {
//
//        }





        // Custom method to check if the presenter was called with the expected data
//        public boolean wasPresentUserProfileCalledWith(
//                int finAidRequirement,
//                int avgSalary,
//                String location,
//                String program,
//                int[] rankingRange
//        ) {
//            if (!presentUserProfileCalled) return false;
//
//            // Check each field individually
//            return lastOutputData.getFinAidRequirement() == finAidRequirement
//                    && lastOutputData.getAvgSalary() == avgSalary
//                    && lastOutputData.getLocationPreference().equals(location)
//                    && lastOutputData.getPreferredProgram().equals(program)
//                    && Arrays.equals(lastOutputData.getUniversityRankingRange(), rankingRange);
//        }
      }

}
