package use_case;

import data_access.ProfileDataAccessInterface;
import entity.UserProfile;
import org.junit.Before;
import org.junit.Test;
import use_case.user_profile.UserProfileInteractor;
import use_case.user_profile.UserProfileOutputBoundary;

import java.io.IOException;

import static org.junit.Assert.*;

public class UserProfileInteractorTest {

    private UserProfileInteractor interactor;
    private UserProfileOutputBoundary outputBoundary;
    private ProfileDataAccessInterface dataAccess;

    @Before
    public void setUp() {
        // You would need to implement a concrete UserProfileOutputBoundary for testing
        outputBoundary = new UserProfileOutputBoundary() {
            // Implement the necessary methods for the test
        };

        // You would need to implement a concrete ProfileDataAccessInterface for testing
        dataAccess = new ProfileDataAccessInterface() {
            // Implement the necessary methods for the test
        };

        interactor = new UserProfileInteractor(outputBoundary, dataAccess);
    }

    @Test
    public void whenUpdatingUserProfile_thenDataAccessIsCalled() throws IOException {
        // Arrange
        int[] rankingRange = new int[]{1, 10};
        UserProfile userProfile = new UserProfile(10000, "Computer Science", 60000, rankingRange, "New York");

        // Act
        interactor.updateUserProfile(
                userProfile.getFinAidRequirement(),
                userProfile.getAvgSalary(),
                userProfile.getLocationPreference(),
                userProfile.getPreferredProgram(),
                userProfile.getUniversityRankingRange());

        // Assert
        // You need to verify that dataAccess.updateProfile was called
        // This is where a mocking framework would typically be used
    }

    @Test
    public void whenFetchingUserProfileData_thenOutputBoundaryIsCalled() throws IOException {
        // Arrange
        // Simulate the data access returning a UserProfile
        UserProfile expectedUserProfile = new UserProfile(10000, "Computer Science", 60000, new int[]{1, 10}, "New York");

        // Act
        interactor.fetchUserProfileData();

        // Assert
        // You need to verify that outputBoundary.presentUserProfile was called with the expected data
        // This is where a mocking framework would typically be used
    }

    // Additional test cases can be added here...
}
