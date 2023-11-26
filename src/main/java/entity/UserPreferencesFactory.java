package entity;

public class UserPreferencesFactory implements UserProfileFactory{

    // Method to create a UserPreferences object with all fields
    @Override
    public UserPreferences create(Integer finAidRequirement, String preferredProgram,
                                         Integer avgSalary, int[] universityRankingRange,
                                         String locationPreference) {
        return new UserPreferences(finAidRequirement, preferredProgram, avgSalary,
                universityRankingRange, locationPreference);
    }

}
