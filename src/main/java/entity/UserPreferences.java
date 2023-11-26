package entity;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class UserPreferences {

    // Refer to the API documentation for the meaning of these fields.

    private Integer finAidRequirement;

    private String preferredProgram;

    private Integer avgSalary;

    private int[] universityRankingRange;

    private String locationPreference;


    public UserPreferences(Integer finAidRequirement, String preferredProgram, Integer avgSalary,
                           int[] universityRankingRange, String locationPreference) {
        this.finAidRequirement = finAidRequirement;
        this.preferredProgram = preferredProgram;
        this.avgSalary = avgSalary;
        this.universityRankingRange = universityRankingRange;
        this.locationPreference = locationPreference;
    }

    public Integer getFinAidRequirement() {
        return finAidRequirement;
    }

    public String getPreferredProgram() {
        return preferredProgram;
    }

    public Integer getAvgSalary() {
        return avgSalary;
    }

    public int[] getUniversityRankingRange() {
        return universityRankingRange;
    }

    public String getLocationPreference() {
        return locationPreference;
    }
}
