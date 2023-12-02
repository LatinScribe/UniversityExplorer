package entity;

public class UserPreferencesFactory implements UserProfileFactory {

    // Method to create a UserPreferences object with all fields
// <<<<<<< KanishV2
//     public static UserPreferences createUserPreferences(Integer finAidRequirement, String preferredProgram,
//                                                         Integer avgSalary, int[] universityRankingRange,
//                                                         String locationPreference) {
// =======
    @Override
    public UserPreferences create(Integer finAidRequirement, String preferredProgram,
                                  Integer avgSalary, int[] universityRankingRange,
                                  String locationPreference) {
        return new UserPreferences(finAidRequirement, preferredProgram, avgSalary,
                universityRankingRange, locationPreference);
    }

}
