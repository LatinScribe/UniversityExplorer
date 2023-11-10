package entity;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class UserPreferences {

    // Refer to the API documentation for the meaning of these fields.

    private Integer finAidRequirement;

    private String preferredProgram;

    private Integer avgSalary;

    private Array universityRankingRange;

    private String locationPreference;


    public UserPreferences(Integer finAidRequirement, String preferredProgram, Integer avgSalary,
                           Array universityRankingRange, String locationPreference) {
        this.finAidRequirement = finAidRequirement;
        this.preferredProgram = preferredProgram;
        this.avgSalary = avgSalary;
        this.universityRankingRange = universityRankingRange;
        this.locationPreference = locationPreference;
    }

}
