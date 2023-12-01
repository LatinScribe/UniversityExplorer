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

public class UserProfileInteractorTest {

    private UserProfilePresenterStub userProfilePresenter;
    private ProfileDataAccessStub profileDataAccessStub;


    @Before
    public void setUp() {
        profileDataAccessStub = new ProfileDataAccessStub();
        UserProfileInteractor testInteractor = new UserProfileInteractor(userProfilePresenter, profileDataAccessStub);





    }

    @Test
    public void showPersonalProfileTest() {
        int[] rankingRange = new int[]{1, 10};
        UserProfileOutputData userProfileOutputData = new UserProfileOutputData(10000, 10000,
                "Singapore", "Computer Science", rankingRange);
        this.userProfilePresenter.presentUserProfile(userProfileOutputData);

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

    private static class UserProfilePresenterStub implements UserProfileOutputBoundary {

        private UserProfileViewModel userProfileViewModel;
        private ViewManagerModel viewManagerModel;

        public UserProfilePresenterStub(ViewManagerModel viewManagerModel,UserProfileViewModel viewModel) {
            this.userProfileViewModel = viewModel;
            this.viewManagerModel = viewManagerModel;
        }

        @Override
        public void presentUserProfile(UserProfileOutputData userProfileOutputData) {
            System.out.println("presentUserProfile Called Successfully");
        }

        @Override
        public void presentProfileEditConfirmation(boolean isSuccess, String message) {

        }

        @Override
        public void presentProfileViewError(String message) {

        }
    }
}


