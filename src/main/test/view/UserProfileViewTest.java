package view;

import interface_adapter.user_profiles.UserProfileController;
import interface_adapter.user_profiles.UserProfileViewModel;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import javax.swing.*;

public class UserProfileViewTest {

    private UserProfileView userProfileView;
    private UserProfileViewModel viewModel;
    private UserProfileController controllerMock;

    @Before
    public void setUp() {
        viewModel = new UserProfileViewModel("userProfileView");
        controllerMock = mock(UserProfileController.class);
        userProfileView = new UserProfileView(viewModel, controllerMock);
    }

    @Test
    public void testInitialView() {
        // Assert initial state of the view
    }

    @Test
    public void testEditProfileButtonFunctionality() {
        JButton editButton = userProfileView.editProfile;
        assertNotNull(editButton);
        editButton.doClick();
        // Assert the state change or interaction
    }

    @Test
    public void testSaveButtonFunctionality() {
        userProfileView.finAidRequirementField.setText("30000");
        userProfileView.avgSalaryField.setText("50000");
        userProfileView.locationPreferenceField.setText("New York");
        userProfileView.preferredProgramField.setText("Computer Science");
        userProfileView.universityRankingRangeField.setText("1,10");

        JButton saveButton = userProfileView.save;
        assertNotNull(saveButton);
        saveButton.doClick();

        // Assert that controller's updateUserProfile method is called
        // This will require the controller to be a spy or a mock and you will need to verify the interaction
    }

    // Additional tests for other UI components and functionalities
}