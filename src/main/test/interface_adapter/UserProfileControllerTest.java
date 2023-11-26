package interface_adapter;

import interface_adapter.user_profiles.UserProfileController;
import org.junit.Before;
import org.junit.Test;
import use_case.user_profile.UserProfileInputBoundary;

import static org.mockito.Mockito.*;

public class UserProfileControllerTest {

    private UserProfileController controller;
    private UserProfileInputBoundary inputBoundaryMock;

    @Before
    public void setUp() {
        inputBoundaryMock = mock(UserProfileInputBoundary.class);
        controller = new UserProfileController(inputBoundaryMock);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateUserProfileWithNegativeFinAidRequirement() {
        controller.updateUserProfile(-1, 50000, "New York", "Computer Science", new int[]{1, 10});
    }

    // Similar tests for other invalid inputs...

    @Test
    public void testUpdateUserProfileWithValidData() {
        int finAidRequirement = 10000;
        int avgSalary = 50000;
        String locationPreference = "New York";
        String preferredProgram = "Computer Science";
        int[] universityRankingRange = new int[]{1, 10};

        controller.updateUserProfile(finAidRequirement, avgSalary, locationPreference, preferredProgram, universityRankingRange);

        verify(inputBoundaryMock).updateUserProfile(finAidRequirement, avgSalary, locationPreference, preferredProgram, universityRankingRange);
    }

    @Test
    public void testGetProfileCallsFetchUserProfileData() {
        controller.getProfile();
        verify(inputBoundaryMock).fetchUserProfileData();
    }
}