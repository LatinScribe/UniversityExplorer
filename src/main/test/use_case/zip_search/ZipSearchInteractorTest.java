package use_case.zip_search;

import data_access.ZipSearchDataAccessObject;
import entity.CommonUniversity;
import entity.University;
import entity.UniversityFactory;
import org.json.JSONObject;
import org.junit.Before;

public class ZipSearchInteractorTest {

    private ZipSearchUserDataAccessStub zipSearchDataAccessStub;
    private ZipSearchOutputBoundaryStub zipSearchPresenterStub;
    private UniversityFactoryStub universityFactoryStub;
    private ZipSearchInteractor zipSearchInteractor;
    @Before
    public void setUp() {
        zipSearchDataAccessStub = new ZipSearchUserDataAccessStub();
        zipSearchPresenterStub = new ZipSearchOutputBoundaryStub();
        universityFactoryStub = new UniversityFactoryStub();
        zipSearchInteractor = new ZipSearchInteractor(zipSearchDataAccessStub, zipSearchPresenterStub, universityFactoryStub);
    }

    private static class ZipSearchUserDataAccessStub implements ZipSearchUserDataAccessInterface {
        private JSONObject searchResults;
        public void setSearchResults(JSONObject searchResults) {
            this.searchResults = searchResults;
        }

        // testing the query
        @Override
        public JSONObject searchQuery(String zipCodeParameter, String radiusParameter) {
            System.out.println("searchQuery called with: " + zipCodeParameter + " and " + radiusParameter);
            ZipSearchDataAccessObject zipSearchDataAccessObject = new ZipSearchDataAccessObject();
            JSONObject query = zipSearchDataAccessObject.searchQuery(zipCodeParameter, radiusParameter);
            this.searchResults = query;
            return searchResults;
        }
    }

    private static class ZipSearchOutputBoundaryStub implements ZipSearchOutputBoundary {
        private boolean successViewCalled;
        private boolean resultsNotFoundViewCalled;
        private boolean backViewCalled;
        private ZipSearchOutputData lastSuccessViewData;

        @Override
        public void prepareSuccessView(ZipSearchOutputData universities) {
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
    }

    private static class UniversityFactoryStub implements UniversityFactory {
        private University university;

        public void setUniversity(University university) {
            this.university = university;
        }

        @Override
        public CommonUniversity create(Integer id, String name, String state, String city, Double admRate,
                                       Integer inTuit, Integer outTuit, Double avgSAT, Double avgACT, String url) {
            return (CommonUniversity) university;
        }
    }
}
