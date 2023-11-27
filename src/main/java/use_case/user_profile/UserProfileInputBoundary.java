package use_case.user_profile;

public interface UserProfileInputBoundary {

    void showPersonalProfileView(UserProfileOutputData userProfileOutputData);

    void updateUserProfile(int finAidRequirement, int avgSalary, String locationPreference, String preferredProgram,
                           int[] universityRankingRange);

    void fetchUserProfileData();
}
