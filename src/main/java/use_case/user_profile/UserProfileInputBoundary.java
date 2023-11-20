package use_case.user_profile;

import java.lang.reflect.Array;

public interface UserProfileInputBoundary {

    void showPersonalProfileView(UserProfileOutputData userProfileOutputData);

    void updateUserProfile(int finAidRequirement, int avgSalary, String locationPreference, String preferredProgram,
                           Integer[] universityRankingRange);
}
