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

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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

    @Test
    public void testUpdateUserProfile() {
        // Setup test data
        int finAidRequirement = 5000;
        int avgSalary = 70000;
        String locationPreference = "New York";
        String preferredProgram = "Data Science";
        int[] universityRankingRange = new int[]{5, 15};

        // Call the method under test
        interactor.updateUserProfile(finAidRequirement, avgSalary, locationPreference, preferredProgram, universityRankingRange);

        // Verify the updateProfile method was called correctly
        assertTrue("ProfileDataAccessInterface updateProfile method should be called with correct UserPreferences",
                profileDataAccessStub.wasUpdateProfileCalledWith(finAidRequirement, avgSalary, locationPreference, preferredProgram, universityRankingRange)
        );
    }

    @Test
    public void testSaveNewUserProfile() {
        // Setup test data
        int finAidRequirement = 8000;
        int avgSalary = 85000;
        String locationPreference = "London";
        String preferredProgram = "Artificial Intelligence";
        int[] universityRankingRange = new int[]{2, 20};

        // Call the method under test
//        try {
//            interactor.saveNewUserProfile(finAidRequirement, avgSalary, locationPreference, preferredProgram, universityRankingRange);
//        } catch (IOException e) {
//            fail("IOException should not be thrown");
//        }

        // Verify the saveProfile method was called correctly
        assertTrue("ProfileDataAccessInterface saveProfile method should be called with correct UserPreferences",
                profileDataAccessStub.wasSaveProfileCalledWith(finAidRequirement, avgSalary, locationPreference, preferredProgram, universityRankingRange)
        );
    }

    @Test
    public void testFetchUserProfileData() {
        // Call the method under test
        interactor.fetchUserProfileData();

        // Verify the correct data was presented
        assertTrue("UserProfileOutputBoundary should present fetched user profile data",
                successPresenter.wasPresentUserProfileCalled()
        );
    }


    private static class ProfileDataAccessStub implements ProfileDataAccessInterface {
        private UserProfile lastSavedProfile;
        private UserProfile lastUpdatedProfile;

        @Override
        public String saveProfile(UserProfile userProfile) {
            this.lastSavedProfile = userProfile;
            return "Simulated saveProfile success";
        }

        @Override
        public String updateProfile(UserProfile userProfile) {
            this.lastUpdatedProfile = userProfile;
            return "Simulated updateProfile success";
        }

        @Override
        public UserProfile getProfile() {
            // Return a default UserProfile for testing purposes
            return new UserPreferences(10000, "Test Program", 50000, new int[]{1, 100}, "Test Location");
        }

        // Custom method to check if saveProfile was called with correct UserPreferences
        public boolean wasSaveProfileCalledWith(int finAidRequirement, int avgSalary, String locationPreference, String preferredProgram, int[] universityRankingRange) {
            UserPreferences expected = new UserPreferences(finAidRequirement, preferredProgram, avgSalary, universityRankingRange, locationPreference);
            return lastSavedProfile.equals(expected);
        }

        // Custom method to check if updateProfile was called with correct UserPreferences
        public boolean wasUpdateProfileCalledWith(int finAidRequirement, int avgSalary, String locationPreference, String preferredProgram, int[] universityRankingRange) {
            UserPreferences expected = new UserPreferences(finAidRequirement, preferredProgram, avgSalary, universityRankingRange, locationPreference);
            return lastUpdatedProfile.equals(expected);
        }
    }

    private static class UserProfileOutputBoundaryStub implements UserProfileOutputBoundary {
        private UserProfileOutputData lastOutputData;
        private boolean presentUserProfileCalled = false;

        @Override
        public void presentUserProfile(UserProfileOutputData userProfileOutputData) {
            this.lastOutputData = userProfileOutputData;
            this.presentUserProfileCalled = true;
        }

        @Override
        public void presentProfileEditConfirmation(boolean isSuccess, String message) {
            // Implementation for tests, if needed
        }

        @Override
        public void presentProfileViewError(String message) {
            // Implementation for tests, if needed
        }

        // Custom method to check if presentUserProfile was called with the expected data
        public boolean wasPresentUserProfileCalledWith(
                int finAidRequirement,
                int avgSalary,
                String location,
                String program,
                int[] rankingRange
        ) {
            if (!presentUserProfileCalled) return false;

            return lastOutputData.getFinAidRequirement() == finAidRequirement
                    && lastOutputData.getAvgSalary() == avgSalary
                    && lastOutputData.getLocationPreference().equals(location)
                    && lastOutputData.getPreferredProgram().equals(program)
                    && Arrays.equals(lastOutputData.getUniversityRankingRange(), rankingRange);
        }

        // Custom method to check if presentUserProfile was called at all
        public boolean wasPresentUserProfileCalled() {
            return presentUserProfileCalled;
        }
    }

}


