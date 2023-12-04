package use_case.results;

import api.JsonCollegeScorecardDB;
import entity.CommonUniversityFactory;
import entity.University;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import use_case.apply.*;

public class ResultsInteractorTest {

    private ResultsPresenterStub resultsPresenter;
    private ResultsDataAccessStub resultsDataAccessStub;
    private ResultsInteractor interactor;

    @Before
    public void setUp() {
        resultsPresenter = new ResultsPresenterStub();
        resultsDataAccessStub = new ResultsDataAccessStub();
        interactor = new ResultsInteractor(resultsDataAccessStub, resultsPresenter, new CommonUniversityFactory());
    }

    @Test
    public void whenBackIsPressed_thenViewModelShouldBeUpdated() {
        interactor.executeBack();
        assert (resultsPresenter.presentBackCalled);
    }

    @Test
    public void whenSearchFailsUnexpectedly_thenPrepareFailViewShouldBeCalled() {
        ResultsInputData resultsInputData = new ResultsInputData("this is wrong");
        interactor.executeUniPress(resultsInputData);
        assert (resultsPresenter.presentFailCalled);
    }

    @Test
    public void whenSearchWorksProperly_thenPrepareSuccessViewShouldBeCalled() {
        ResultsInputData resultsInputData = new ResultsInputData("John Brown University");
        interactor.executeUniPress(resultsInputData);
        assert (resultsPresenter.presentUniPopupCalled);
        assert (resultsPresenter.resultsOutputData.getUniversity().getSchoolName().equals("John Brown University"));
    }

    @Test
    public void whenAdmissionRateIsOne_thenPrepareSuccessViewShouldBeCalled() {
        ResultsInputData resultsInputData = new ResultsInputData("California Jazz Conservatory");
        interactor.executeUniPress(resultsInputData);
        assert (resultsPresenter.presentUniPopupCalled);
        assert (resultsPresenter.resultsOutputData.getUniversity().getSchoolName().equals("California Jazz Conservatory"));
    }

    private static class ResultsDataAccessStub implements ResultsUserDataAccessInterface {
        @Override
        public JSONObject searchQuery(String searchParameters) {
            JsonCollegeScorecardDB jsonCollegeScorecardDB = new JsonCollegeScorecardDB();
            return jsonCollegeScorecardDB.basicQuery(searchParameters, "fields=id,school.name,school.state,school.city,2018.admissions.admission_rate.overall,2018.cost.tuition.in_state,2018.cost.tuition.out_of_state,2018.admissions.sat_scores.average.overall,2018.admissions.act_scores.midpoint.cumulative,school.school_url");
        }
    }

    private static class ResultsPresenterStub implements ResultsOutputBoundary {
        private boolean presentBackCalled = false;
        private boolean presentUniPopupCalled = false;
        private boolean presentFailCalled = false;
        private ResultsOutputData resultsOutputData;

        @Override
        public void prepareUniPopup(ResultsOutputData university) {
            presentUniPopupCalled = true;
            resultsOutputData = university;
        }

        @Override
        public void prepareFailView(String error) {
            presentFailCalled = true;
        }

        @Override
        public void prepareBackView() {
            presentBackCalled = true;
        }
    }
}
