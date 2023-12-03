package use_case.apply;

import api.JsonCollegeScorecardDB;
import data_access.ApplyDataAccessObject;
import data_access.ProfileDataAccessInterface;
import entity.CommonUniversityFactory;
import entity.UserPreferences;
import entity.UserProfile;
import interface_adapter.UserProfileInteractorTest;
import interface_adapter.apply.ApplyPresenter;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import use_case.user_profile.UserProfileInteractor;
import use_case.user_profile.UserProfileOutputBoundary;
import use_case.user_profile.UserProfileOutputData;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class ApplyInteractorTest {

    private ApplyPresenterStub applyPresenter;
    private ApplyDataAccessStub profileDataAccessStub;
    private ApplyInteractor interactor;

    @Before
    public void setUp() {
        applyPresenter = new ApplyPresenterStub();
        profileDataAccessStub = new ApplyDataAccessStub();
        interactor = new ApplyInteractor(profileDataAccessStub, applyPresenter, new CommonUniversityFactory());
    }

    @Test
    public void whenBackIsPressed_thenViewModelShouldBeUpdated() {
        interactor.executeBack();
        assert (applyPresenter.presentBackCalled);
    }

    @Test
    public void whenExecuteSearchReturnsNull_thenResultsNotFoundShouldBeCalled() {
        ApplyInputData inputData = new ApplyInputData("0", "0");
        interactor.execute(inputData);
        assert (applyPresenter.presentBackCalled);
    }

    @Test
    public void whenExecuteSearchReturnsUniversities_thenSuccessViewShouldBeCalled() {
        ApplyInputData inputData = new ApplyInputData("1200", "26");
        interactor.execute(inputData);
        assert (applyPresenter.presentSuccessCalled);
        assert (applyPresenter.applyOutputData.getUniversity().getSchoolName().equals("The University of Alabama"));
    }

    private static class ApplyDataAccessStub implements ApplyDataAccessInterface {
        @Override
        public JSONObject basicQuery(String queryParameters, String optionalParameters) {
            JsonCollegeScorecardDB jsonCollegeScorecardDB = new JsonCollegeScorecardDB();
            return jsonCollegeScorecardDB.basicQuery(queryParameters, optionalParameters);
        }
    }

    private static class ApplyPresenterStub implements ApplyOutputBoundary {
        private boolean presentBackCalled = false;
        private boolean presentSuccessCalled = false;
        private boolean prepareFailView = false;
        private ApplyOutputData applyOutputData;

        @Override
        public void prepareSuccessView(ApplyOutputData university) {
            presentSuccessCalled = true;
            applyOutputData = university;
        }

        @Override
        public void prepareFailView(String error) {
            prepareFailView = true;
        }

        @Override
        public void prepareBackView() {
            presentBackCalled = true;
        }

        ;
    }
}
