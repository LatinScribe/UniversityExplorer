package use_case.user_profile;

import data_access.ProfileDataAccessInterface;
import entity.User;
import entity.UserPreferences;
import entity.UserProfile;
import org.junit.Before;
import org.junit.Test;
import view.UserProfileViewTest;

import java.io.IOException;

public class UserProfileInteractorTest {

    private UserProfileDataAccessStub profileDataAccessStub;
    private ProfilePresenterStub profilePresenterStub;
    private UserProfileInteractor interactor;

    @Before
    public void setUp() {
        profileDataAccessStub = new UserProfileDataAccessStub();
        profilePresenterStub = new ProfilePresenterStub();
        interactor = new UserProfileInteractor(profilePresenterStub, profileDataAccessStub);
    }

    @Test
    public void testShowPersonProfile() {
        UserProfileOutputData userProfileOutputData = new UserProfileOutputData(5, 7, "tee", "compsci", null);
        interactor.showPersonalProfileView(userProfileOutputData);
        assert (profilePresenterStub.profileOutputData.equals(userProfileOutputData));
        assert (profilePresenterStub.presentUserProfileCalled);
    }

    @Test
    public void testUpdateUserProfile() {
        interactor.updateUserProfile(5, 6, "hi", "cs", null);
        assert (profileDataAccessStub.updateProfileStub);
    }

    @Test
    public void testSaveNewUserProfile() {
        interactor.saveNewUserProfile(5, 6, "hi", "cs", null);
        assert (profileDataAccessStub.saveProfileStub);
    }

    @Test
    public void testFetchUserProfileData() {
        interactor.fetchUserProfileData();
        assert (profilePresenterStub.presentUserProfileCalled);
    }

    private static class UserProfileDataAccessStub implements ProfileDataAccessInterface {

        private boolean saveProfileStub = false;
        private boolean updateProfileStub = false;
        private boolean getProfileStub = false;
        @Override
        public String saveProfile(UserProfile userProfile) throws IOException {
            saveProfileStub = true;
            return "";
        }

        @Override
        public String updateProfile(UserProfile userProfile) throws IOException {
            updateProfileStub = true;
            return "";
        }

        @Override
        public UserProfile getProfile() throws IOException {
            getProfileStub = true;
            return new UserPreferences(5000, "b", 100, null, "e");
        }
    }

    private static class ProfilePresenterStub implements UserProfileOutputBoundary {
        public boolean presentUserProfileCalled = false;
        public boolean presentProfileEditConfirmationCalled = false;
        public boolean presentProfileViewError = false;
        public UserProfileOutputData profileOutputData;

        @Override
        public void presentUserProfile(UserProfileOutputData userProfileOutputData) {
            presentUserProfileCalled = true;
            profileOutputData = userProfileOutputData;
        }

        @Override
        public void presentProfileEditConfirmation(boolean isSuccess, String message) {
            presentProfileEditConfirmationCalled = true;
        }

        @Override
        public void presentProfileViewError(String message) {
            presentProfileViewError = true;
        }
    }

}
