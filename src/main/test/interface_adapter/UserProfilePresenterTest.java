package interface_adapter;

import interface_adapter.ViewManagerModel;
import interface_adapter.user_profiles.UserProfilePresenter;
import interface_adapter.user_profiles.UserProfileState;
import interface_adapter.user_profiles.UserProfileViewModel;
import org.junit.Before;
import org.junit.Test;
import use_case.user_profile.UserProfileOutputData;

import static org.junit.Assert.*;

public class UserProfilePresenterTest {

    private UserProfilePresenter presenter;
    private ViewManagerModel viewManagerModel;
    private UserProfileViewModel userProfileViewModel;

    @Before
    public void setUp() {
        // Initialize the viewManagerModel and userProfileViewModel with actual implementations
        viewManagerModel = new ViewManagerModel();
        userProfileViewModel = new UserProfileViewModel("userProfileView");
        presenter = new UserProfilePresenter(viewManagerModel, userProfileViewModel);
    }

    @Test
    public void whenUserProfileDataIsPresented_thenViewModelShouldBeUpdated() {
        UserProfileOutputData outputData = new UserProfileOutputData(
                10000, // finAidRequirement
                60000, // avgSalary
                "New York", // locationPreference
                "Computer Science", // preferredProgram
                new int[]{1, 10} // universityRankingRange
        );

        // Act
        presenter.presentUserProfile(outputData);

        // Assert
        UserProfileState state = userProfileViewModel.getState();
        assertNotNull("ViewModel state should not be null", state);
        assertEquals("Financial aid requirement did not match", Integer.valueOf(10000), state.getFinAidRequirement());
        assertEquals("Average salary did not match", Integer.valueOf(60000), state.getAvgSalary());
        assertEquals("Location preference did not match", "New York", state.getLocationPreference());
        assertEquals("Preferred program did not match", "Computer Science", state.getPreferredProgram());
        assertArrayEquals("University ranking range did not match", new int[]{1, 10}, state.getUniversityRankingRange());
    }

    // Additional test cases can be added here...
}