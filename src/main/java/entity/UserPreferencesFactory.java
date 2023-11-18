package entity;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class UserPreferencesFactory {

    // Method to create a UserPreferences object with all fields
    public static UserPreferences createUserPreferences(Integer finAidRequirement, String preferredProgram,
                                                        Integer avgSalary, ArrayList<Integer> universityRankingRange,
                                                        String locationPreference) {
        return new UserPreferences(finAidRequirement, preferredProgram, avgSalary,
                universityRankingRange, locationPreference);
    }

}
