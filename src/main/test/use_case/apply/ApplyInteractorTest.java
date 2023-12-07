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
        assert (applyPresenter.presentFailCalled);
    }

    @Test
    public void whenExecuteSearchReturnsUniversities_thenSuccessShouldBeCalled() {
        ApplyInputData inputData = new ApplyInputData("1200", "28");
        interactor.execute(inputData);
        assert (applyPresenter.presentSuccessCalled);
        assert (applyPresenter.applyOutputData.getUniversity().getSchoolName().equals("John Brown University"));
    }

    @Test (expected = NumberFormatException.class)
    public void whenSATIsNotNumerical_thenNumberFormatExceptionCalled() {
        ApplyInputData inputData = new ApplyInputData("this", "20");
        interactor.execute(inputData);
    }

    @Test (expected = NumberFormatException.class)
    public void whenACTIsNotNumerical_thenNumberFormatExceptionCalled() {
        ApplyInputData inputData = new ApplyInputData("1000", "this");
        interactor.execute(inputData);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenNoSATProvided_thenIllegalArgumentExceptionCalled() {
        ApplyInputData inputData = new ApplyInputData("", "28");
        interactor.execute(inputData);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenSATTooLow_thenIllegalArgumentExceptionCalled() {
        ApplyInputData inputData = new ApplyInputData("-5", "28");
        interactor.execute(inputData);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenSATTooHigh_thenIllegalArgumentExceptionCalled() {
        ApplyInputData inputData = new ApplyInputData("2000", "28");
        interactor.execute(inputData);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenACTNotProvided_thenIllegalArgumentExceptionCalled() {
        ApplyInputData inputData = new ApplyInputData("1000", "");
        interactor.execute(inputData);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenACTTooLow_thenIllegalArgumentExceptionCalled() {
        ApplyInputData inputData = new ApplyInputData("1000", "-5");
        interactor.execute(inputData);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenACTTooHigh_thenIllegalArgumentExceptionCalled() {
        ApplyInputData inputData = new ApplyInputData("1000", "50");
        interactor.execute(inputData);
    }

    @Test
    public void whenSATOnlyValid_thenUniversityGiven() {
        ApplyInputData inputData = new ApplyInputData("1200", "0");
        interactor.execute(inputData);
        assert (applyPresenter.presentSuccessCalled);
    }

    @Test
    public void whenACTOnlyValid_thenUniversityGiven() {
        ApplyInputData inputData = new ApplyInputData("0", "28");
        interactor.execute(inputData);
        assert (applyPresenter.presentSuccessCalled);
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
        private boolean presentFailCalled = false;
        private ApplyOutputData applyOutputData;

        @Override
        public void prepareSuccessView(ApplyOutputData university) {
            presentSuccessCalled = true;
            applyOutputData = university;
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
