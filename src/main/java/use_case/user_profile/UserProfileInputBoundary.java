package use_case.user_profile;

import entity.UserProfile;

import java.io.IOException;

public interface UserProfileInputBoundary {

    void showPersonalProfileView(UserProfileOutputData userProfileOutputData);

    void updateUserProfile(int finAidRequirement, int avgSalary, String locationPreference, String preferredProgram,
                           int[] universityRankingRange);

    void fetchUserProfileData();

    void prepareMainMenuView();

    void saveNewUserProfile(int finAidRequirement, int avgSalary, String locationPreference, String preferredProgram, int[] universityRankingRange) throws IOException;
}
