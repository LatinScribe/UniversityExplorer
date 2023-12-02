package entity;

public interface UserProfileFactory {
    // Method to create a UserPreferences object with all fields
    UserProfile create(Integer finAidRequirement, String preferredProgram,
                       Integer avgSalary, int[] universityRankingRange,
                       String locationPreference);
}
