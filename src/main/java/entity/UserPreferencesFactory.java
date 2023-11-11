package entity;

import java.lang.reflect.Array;

public class UserPreferencesFactory {

    // Method to create a UserPreferences object with all fields
    public static UserPreferences createUserPreferences(Integer finAidRequirement, String preferredProgram,
                                                        Integer avgSalary, Array universityRankingRange,
                                                        String locationPreference) {
        return new UserPreferences(finAidRequirement, preferredProgram, avgSalary,
                universityRankingRange, locationPreference);
    }

}
