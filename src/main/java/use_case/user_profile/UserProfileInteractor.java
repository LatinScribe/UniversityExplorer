package use_case.user_profile;

import data_access.ProfileDataAccessInterface;
import entity.UserPreferences;
import entity.UserProfile;

import java.io.IOException;

/**
 * A use case interactor class for user profile operations.
 * This class is responsible for handling the business logic related to user profiles,
 * interacting with data access interfaces, and communicating with the presenter.
 */
public class UserProfileInteractor implements UserProfileInputBoundary {

    final UserProfileOutputBoundary userProfileOutputBoundary;
    // UserProfilePresenter implements this and will be the primary function called

    final ProfileDataAccessInterface profileDataAccessInterface;


    /**
     * Constructs a UserProfileInteractor with the specified output boundary and data access interface.
     *
     * @param userProfilePresenter The output boundary to present user profile data.
     * @param profileDataAccessInterface The data access interface for user profile operations.
     */
    public UserProfileInteractor(UserProfileOutputBoundary userProfilePresenter, ProfileDataAccessInterface profileDataAccessInterface){
        this.userProfileOutputBoundary = userProfilePresenter;
        this.profileDataAccessInterface = profileDataAccessInterface;
    }

    /**
     * Displays the personal profile view using the provided user profile output data.
     *
     * @param userProfileOutputData The data to be presented in the personal profile view.
     */
    public void showPersonalProfileView(UserProfileOutputData userProfileOutputData) {
        this.userProfileOutputBoundary.presentUserProfile(userProfileOutputData);
    }

    /**
     * Updates the user profile with the specified parameters.
     *
     * @param finAidRequirement The financial aid requirement.
     * @param avgSalary The average salary.
     * @param locationPreference The location preference.
     * @param preferredProgram The preferred program.
     * @param universityRankingRange The university ranking range.
     */
    @Override
    public void updateUserProfile(int finAidRequirement, int avgSalary, String locationPreference, String preferredProgram, int[] universityRankingRange) {
        try {
            UserPreferences userPreferences = new UserPreferences(finAidRequirement, preferredProgram, avgSalary, universityRankingRange, locationPreference);
            this.profileDataAccessInterface.updateProfile(userPreferences);
        } catch (IOException e) {
            throw new RuntimeException(e);
            // TODO: Make this error more descriptive
        }
    }

    /**
     * Saves a new user profile with the given parameters.
     *
     * @param finAidRequirement The financial aid requirement.
     * @param avgSalary The average salary.
     * @param locationPreference The location preference.
     * @param preferredProgram The preferred program.
     * @param universityRankingRange The university ranking range.
     * @throws IOException If an I/O error occurs.
     */
    public void saveNewUserProfile(int finAidRequirement, int avgSalary, String locationPreference, String preferredProgram, int[] universityRankingRange) throws IOException {
        try {
            UserPreferences userPreferences = new UserPreferences(finAidRequirement, preferredProgram, avgSalary, universityRankingRange, locationPreference);
            this.profileDataAccessInterface.saveProfile(userPreferences);
        } catch (IOException e) {
            throw new RuntimeException(e);
            // TODO: Make this error more descriptive
        }

    }

    /**
     * Prepares the main menu view.
     */
    public void prepareMainMenuView() {
        this.userProfileOutputBoundary.prepareMainMenuView();
    }

    /**
     * Fetches and presents the current user profile data.
     */
    @Override
    public void fetchUserProfileData() {
        try {
            UserProfile userProfilePreferences = profileDataAccessInterface.getProfile();
            UserProfileOutputData userProfileData = new UserProfileOutputData(userProfilePreferences.getFinAidRequirement(), userProfilePreferences.getAvgSalary(), userProfilePreferences.getLocationPreference(), userProfilePreferences.getPreferredProgram(), userProfilePreferences.getUniversityRankingRange());
            userProfileOutputBoundary.presentUserProfile(userProfileData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
