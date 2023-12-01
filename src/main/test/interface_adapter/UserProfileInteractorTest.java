package interface_adapter;

import data_access.ProfileDataAccessInterface;
import entity.User;
import entity.UserPreferences;
import entity.UserProfile;
import interface_adapter.user_profiles.UserProfilePresenter;
import interface_adapter.user_profiles.UserProfileViewModel;
import org.junit.Before;
import org.junit.Test;
import use_case.user_profile.UserProfileInteractor;
import use_case.user_profile.UserProfileOutputBoundary;
import use_case.user_profile.UserProfileOutputData;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class UserProfileInteractorTest {

    private UserProfileOutputBoundaryStub successPresenter;
    private ProfileDataAccessStub profileDataAccessStub;
    private UserProfileInteractor interactor;

    @Before
    public void setUp() {
        successPresenter = new UserProfileOutputBoundaryStub();
        profileDataAccessStub = new ProfileDataAccessStub();
        interactor = new UserProfileInteractor(successPresenter, profileDataAccessStub);
    }

    @Test
    public void testShowPersonalProfileView() {
        // Setup expected data
        int expectedFinAidRequirement = 10000;
        int expectedAvgSalary = 10000;
        String expectedLocation = "Singapore";
        String expectedProgram = "Computer Science";
        int[] expectedRankingRange = new int[]{1, 10};

        // Create output data with expected values
        UserProfileOutputData expectedOutput = new UserProfileOutputData(
                expectedFinAidRequirement, expectedAvgSalary, expectedLocation, expectedProgram, expectedRankingRange
        );

        // Call the method under test
        interactor.showPersonalProfileView(expectedOutput);

        // Assert that the presenter was called with the correct data
        assertTrue("Presenter should be called with the expected output data",
                successPresenter.wasPresentUserProfileCalledWith(
                        expectedFinAidRequirement,
                        expectedAvgSalary,
                        expectedLocation,
                        expectedProgram,
                        expectedRankingRange
                )
        );
    }


    private static class ProfileDataAccessStub implements ProfileDataAccessInterface {
        public String saveProfile(UserProfile userProfile) {
            // Simulated behavior
            System.out.println("saveProfile called with: " + userProfile);
            return "Simulated saveProfile success"; // or return null to indicate success
        }

        public String updateProfile(UserProfile userProfile) {
            // Simulated behavior
            System.out.println("updateProfile called with: " + userProfile);
            return "Simulated updateProfile success"; // or return null to indicate success
        }

        public UserProfile getProfile() {
            System.out.println("getProfile called");
            return new UserPreferences(10000, "Test Program", 50000, new int[]{1, 100}, "Test Location");
        }
    }

    private static class UserProfileOutputBoundaryStub implements UserProfileOutputBoundary {
        private boolean presentUserProfileCalled = false;
        private UserProfileOutputData lastOutputData;

        @Override
        public void presentUserProfile(UserProfileOutputData userProfileOutputData) {
            this.lastOutputData = userProfileOutputData;
            this.presentUserProfileCalled = true;
        }

        @Override
        public void presentProfileEditConfirmation(boolean isSuccess, String message) {

        }

        @Override
        public void presentProfileViewError(String message) {

        }





        // Custom method to check if the presenter was called with the expected data
        public boolean wasPresentUserProfileCalledWith(
                int finAidRequirement,
                int avgSalary,
                String location,
                String program,
                int[] rankingRange
        ) {
            if (!presentUserProfileCalled) return false;

            // Check each field individually
            return lastOutputData.getFinAidRequirement() == finAidRequirement
                    && lastOutputData.getAvgSalary() == avgSalary
                    && lastOutputData.getLocationPreference().equals(location)
                    && lastOutputData.getPreferredProgram().equals(program)
                    && Arrays.equals(lastOutputData.getUniversityRankingRange(), rankingRange);
        }
    }

}


