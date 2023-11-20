package use_case.user_profile;

import java.lang.reflect.Array;
import java.util.Arrays;

// Mock implementation for testing
public class MockUserProfileInputBoundary implements UserProfileInputBoundary {
    @Override
    public void showPersonalProfileView(UserProfileOutputData userProfileOutputData) {

    }

    @Override
    public void updateUserProfile(int finAidRequirement, int avgSalary, String locationPreference,
                                  String preferredProgram, Integer[] universityRankingRange) {
        System.out.println("Mock updateUserProfile called with:");
        System.out.println("Financial Aid Requirement: " + finAidRequirement);
        System.out.println("Average Salary: " + avgSalary);
        System.out.println("Location Preference: " + locationPreference);
        System.out.println("Preferred Program: " + preferredProgram);
        System.out.println("University Ranking Range: " + Arrays.toString(universityRankingRange));
    }

    public void showPersonalProfileView() {
        System.out.println("Mock showPersonalProfileView called.");
    }
}
