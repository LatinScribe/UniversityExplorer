package view;

import entity.UserPreferences;
import entity.UserProfile;
import interface_adapter.user_profiles.UserProfileController;
import interface_adapter.user_profiles.UserProfileState;
import interface_adapter.user_profiles.UserProfileViewModel;
import org.junit.Before;
import org.junit.Test;
import use_case.user_profile.UserProfileInputBoundary;
import use_case.user_profile.UserProfileOutputData;

import javax.swing.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserProfileViewTest {

    private UserProfileView view;
    private UserProfileViewModel viewModel;
    private UserProfileController controller;
    private UserProfileInteractorStub interactorStub;
    private ProfileDataAccessStub dataAccessStub;

    @Before
    public void setUp() {
        // Initialize the viewModel with a stub
        viewModel = new UserProfileViewModel("userProfile");

        // Create stubs for the interactor and data access objects
        interactorStub = new UserProfileInteractorStub();
        dataAccessStub = new ProfileDataAccessStub();

        // Create the controller with the interactor stub
        controller = new UserProfileController(interactorStub);

        // Initialize the view with the viewModel and controller
        view = new UserProfileView(viewModel, controller);

        // Set up the test environment for the view
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.add(view);
            frame.pack();
            frame.setVisible(true);
        });
    }

    @Test
    public void testViewingProfileDisplaysCorrectInformation() {
        // Assume that the viewModel has been populated with some data
        UserProfileState state = new UserProfileState();
        state.setFinAidRequirement(30000);
        state.setAvgSalary(75000);
        state.setLocationPreference("New York");
        state.setPreferredProgram("Computer Science");
        state.setUniversityRankingRange(new int[]{1, 10});
        viewModel.setState(state);

        // Trigger the view to update its display based on the viewModel's state
        viewModel.firePropertyChanged();

        // Now assert that the view reflects the state of the viewModel
        assertEquals("30000", view.finAidRequirementValue.getText());
        assertEquals("75000", view.avgSalaryValue.getText());
        assertEquals("New York", view.locationPreferenceValue.getText());
        assertEquals("Computer Science", view.preferredProgramValue.getText());
        /*assertEquals(new int[]{1,10}, view.universityRankingRangeValue.getText());*/
        // TODO: Find a new Assert function that works for lists of ints
    }

    @Test
    public void testEditProfileEnablesEditing() {
        // Trigger edit mode
        view.editProfile.doClick();

        // Assert that the text fields are enabled and populated with current profile data
        assertTrue(view.finAidRequirementField.isEnabled());
        assertTrue(view.avgSalaryField.isEnabled());
        assertTrue(view.locationPreferenceField.isEnabled());
        assertTrue(view.preferredProgramField.isEnabled());
        assertTrue(view.universityRankingRangeField.isEnabled());
    }

    @Test
    public void testSaveProfileUpdatesInformation() {
        // Set some text in the fields
        view.finAidRequirementField.setText("35000");
        view.avgSalaryField.setText("80000");
        view.locationPreferenceField.setText("Los Angeles");
        view.preferredProgramField.setText("Mathematics");
        view.universityRankingRangeField.setText("2, 15");

        // Simulate clicking the save button
        view.save.doClick();

        // Here we would assert that the controller has been invoked to save the profile
    }


    public static class UserProfileInteractorStub implements UserProfileInputBoundary {

        private final UserProfile userProfile; // This should be set to the expected profile for the test

        public UserProfileInteractorStub() {
            // Initialize userProfile with expected test data
            this.userProfile = new UserPreferences(
                    10000, "Computer Science", 50000, new int[]{1, 100}, "New York");
        }

        public void showPersonalProfileView(UserProfileOutputData userProfileOutputData) {

        }


        public void updateUserProfile(int finAidRequirement, int avgSalary, String locationPreference,
                                      String preferredProgram, int[] universityRankingRange) {
            // In a real stub, you might check the inputs against expected values
            // For now, we'll assume this method always succeeds
        }

        public void fetchUserProfileData() {
            // Directly call the output boundary with the pre-set user profile
            UserProfileOutputData outputData = new UserProfileOutputData(
                    userProfile.getFinAidRequirement(),
                    userProfile.getAvgSalary(),
                    userProfile.getLocationPreference(),
                    userProfile.getPreferredProgram(),
                    userProfile.getUniversityRankingRange());
            System.out.println(outputData);
        }

    }

    private static class ProfileDataAccessStub {
        public String saveProfile(UserProfile userProfile) {
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
}
