package use_case.prefapply;

import api.JsonCollegeScorecardDB;
import data_access.ProfileDataAccessInterface;
import entity.*;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import use_case.search.SearchOutputData;

import java.io.IOException;

public class PrefApplyInteractorTest {

    private static class ProfileDataAccessStub implements ProfileDataAccessInterface {



        @Override
        public String saveProfile(UserProfile userProfile) throws IOException {
            return null;
        }

        @Override
        public String updateProfile(UserProfile userProfile) throws IOException {
            return null;
        }

        @Override
        public UserProfile getProfile() throws IOException {
            // Example: Creating a UserProfile object
            Integer finAidRequirement = 5000;
            String preferredProgram = "Computer Science";
            Integer avgSalary = 80000;
            int[] universityRankingRange = {1, 100};
            String locationPreference = "Urban";
            UserProfileFactory userProfileFactory = new UserPreferencesFactory();
            UserProfile userProfile = userProfileFactory.create(finAidRequirement,
                    preferredProgram,
                    avgSalary,
                    universityRankingRange,
                    locationPreference);


            return userProfile;
        }


    }

    private static class PrefApplyDataAccessStub implements PrefApplyDataAccessInterface {

        @Override
        public JSONObject basicQuery(String queryParameters, String optionalParameters)  {
            JsonCollegeScorecardDB jsonCollegeScorecardDB = new JsonCollegeScorecardDB();
            return jsonCollegeScorecardDB.basicQuery(queryParameters, optionalParameters);
        }
    }

    private static class UniversityFactoryStub implements UniversityFactory {

        @Override
        public CommonUniversity create(Integer schoolID, String schoolName, String state, String city, Double admissionRate, Integer averageInStateTuition, Integer averageOutOfStateTuition, Integer averageSATScore, Integer averageACTScore, String url) {
            return new CommonUniversity(schoolID, schoolName, state, city, admissionRate, averageInStateTuition, averageOutOfStateTuition, averageSATScore, averageACTScore, url);
        }
    }

    private static class PrefApplyOutputBoundaryStub implements PrefApplyOutputBoundary {
        private boolean successViewCalled = false;
        private boolean failviewcalled = false;
        private boolean backViewCalled = false;
        private PrefApplyOutputData lastSuccessViewData;

        @Override
        public void prepareSuccessView(PrefApplyOutputData universities) {
            successViewCalled = true;
            lastSuccessViewData = universities;
        }

        @Override
        public void prepareFailView(String error) {
            failviewcalled = true;
        }


        @Override
        public void prepareBackView() {
            backViewCalled = true;
        }
    }

    private PrefApplyDataAccessInterface prefApplyDataAccessObject;
    private PrefApplyOutputBoundaryStub prefApplyOutputBoundary;
    private UniversityFactory universityFactory;
    private ProfileDataAccessInterface profileDataAccessInterface;
    private PrefApplyInteractor prefApplyInteractor;

    @Before
    public void setUp() {
        prefApplyDataAccessObject = new PrefApplyDataAccessStub();
        prefApplyOutputBoundary = new PrefApplyOutputBoundaryStub();
        universityFactory = new UniversityFactoryStub();
        profileDataAccessInterface = new ProfileDataAccessStub(); // You may adjust Profile constructor based on your actual implementation
        prefApplyInteractor = new PrefApplyInteractor(
                prefApplyDataAccessObject,
                prefApplyOutputBoundary,
                universityFactory,
                profileDataAccessInterface
        );
    }

    @Test
    public void testExecuteSuccess() throws IOException {

        PrefApplyInputData  mockInputData= new PrefApplyInputData("1300","1400");
        prefApplyInteractor.execute(mockInputData);

        // Assert
        Assert.assertTrue(prefApplyOutputBoundary.successViewCalled);
        Assert.assertFalse(prefApplyOutputBoundary.failviewcalled);
        Assert.assertFalse(prefApplyOutputBoundary.backViewCalled);

        // You can add more assertions based on your specific use case
        Assert.assertNotNull(prefApplyOutputBoundary.lastSuccessViewData);
        Assert.assertEquals("Occidental College", prefApplyOutputBoundary.lastSuccessViewData.getUniversity().getSchoolName());
    }

    @Test
    public void backviewcall() throws IOException {
        prefApplyInteractor.executeBack();
        Assert.assertTrue(prefApplyOutputBoundary.backViewCalled);

    }

    // Add more tests for other scenarios as needed
}
