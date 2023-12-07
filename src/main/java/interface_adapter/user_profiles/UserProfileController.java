package interface_adapter.user_profiles;

import use_case.user_profile.UserProfileInputBoundary;
import use_case.user_profile.UserProfileOutputData;
import javax.swing.*;
import java.io.IOException;

/**
 * Controller class responsible for handling user profile-related operations.
 * This class serves as a mediator between the UI and the use case layer,
 * specifically for operations related to user profiles.
 */
public class UserProfileController {

    final UserProfileInputBoundary userProfileInputBoundary;

    /**
     * Constructs a UserProfileController with the specified UserProfileInputBoundary.
     *
     * @param userProfileInputBoundary the user profile input boundary to be used by this controller.
     */
    public UserProfileController(UserProfileInputBoundary userProfileInputBoundary) {
        this.userProfileInputBoundary = userProfileInputBoundary;
        // Takes in a UserProfileInteractor, which implements the Input Boundary
    }


    /**
     * Updates the user profile with the specified parameters.
     *
     * @param finAidRequirement The financial aid requirement.
     * @param avgSalary The average salary expected.
     * @param locationPreference The location preference.
     * @param preferredProgram The preferred program.
     * @param universityRankingRange The range of university rankings.
     * @throws IllegalArgumentException If any validation fails.
     */
    public void updateUserProfile(int finAidRequirement, int avgSalary, String locationPreference,
                                  String preferredProgram, int[] universityRankingRange)
            throws IllegalArgumentException {
        // Input validation
        if (finAidRequirement < 0) {
            throw new IllegalArgumentException("Financial aid requirement cannot be negative.");
        }
        if (avgSalary < 0) {
            throw new IllegalArgumentException("Average salary cannot be negative.");
        }
        if (locationPreference == null || locationPreference.trim().isEmpty()) {
            throw new IllegalArgumentException("Location preference cannot be null or empty.");
        }
        if (preferredProgram == null || preferredProgram.trim().isEmpty()) {
            throw new IllegalArgumentException("Preferred program cannot be null or empty.");
        }
        if (universityRankingRange == null || universityRankingRange.length == 0) {
            throw new IllegalArgumentException("University ranking range cannot be null or empty.");
        }

        // Call the use case layer/interactor to update the user profile
        userProfileInputBoundary.updateUserProfile(finAidRequirement, avgSalary, locationPreference,
                preferredProgram, universityRankingRange);
    }

    /**
     * Switches the view to show the personal profile based on the provided parameters.
     *
     * @param finAidRequirement The financial aid requirement.
     * @param avgSalary The average salary expected.
     * @param locationPreference The location preference.
     * @param preferredProgram The preferred program.
     * @param universityRankingRange The range of university rankings.
     */
    public void switchToPersonalProfile(int finAidRequirement, int avgSalary, String locationPreference,
                                        String preferredProgram, int[] universityRankingRange) {
        UserProfileOutputData userProfileOutputData = new UserProfileOutputData(finAidRequirement, avgSalary, locationPreference,
                preferredProgram, universityRankingRange);
        this.userProfileInputBoundary.showPersonalProfileView(userProfileOutputData);
    }

    /**
     * Switches the view to the main menu.
     */
    public void switchToMainMenu() {
        this.userProfileInputBoundary.prepareMainMenuView();
    }

    /**
     * Creates a new user profile with the provided parameters.
     *
     * @param finAidRequirement The financial aid requirement.
     * @param avgSalary The average salary expected.
     * @param locationPreference The location preference.
     * @param preferredProgram The preferred program.
     * @param universityRankingRange The range of university rankings.
     */
    public void createNewUserProfile(int finAidRequirement, int avgSalary, String locationPreference, String preferredProgram, int[] universityRankingRange) {
        try {
            // Validate financial aid requirement
            if (finAidRequirement < 0) {
                throw new IllegalArgumentException("Financial aid requirement cannot be negative.");
            }

            // Validate average salary
            if (avgSalary < 0) {
                throw new IllegalArgumentException("Average salary cannot be negative.");
            }

            // Validate location preference
            if (locationPreference == null || locationPreference.trim().isEmpty()) {
                throw new IllegalArgumentException("Location preference cannot be null or empty.");
            }

            // Validate preferred program
            if (preferredProgram == null || preferredProgram.trim().isEmpty()) {
                throw new IllegalArgumentException("Preferred program cannot be null or empty.");
            }

            // Validate university ranking range
            if (universityRankingRange == null || universityRankingRange.length != 2) {
                throw new IllegalArgumentException("University ranking range must have exactly two elements.");
            }
            if (universityRankingRange[0] >= universityRankingRange[1]) {
                throw new IllegalArgumentException("The start of the university ranking range must be less than the end.");
            }

            // If all validations pass, call the interactor to save the new profile
            userProfileInputBoundary.saveNewUserProfile(finAidRequirement, avgSalary, locationPreference, preferredProgram, universityRankingRange);

        } catch (IllegalArgumentException e) {
            // Handle validation errors
            JOptionPane.showMessageDialog(null, e.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Fetches the user profile data.
     */
    public void getProfile() {
        userProfileInputBoundary.fetchUserProfileData();
    }
}
