package use_case.user_profile;

import java.lang.reflect.Array;

public class UserProfileOutputData {

    private final Integer finAidRequirements;
    private final Integer avgSalary;
    private final String locationPreference;
    private final String preferredProgram;
    private final int[] universityRankingRange;

    public UserProfileOutputData(Integer finAidRequirements, Integer avgSalary, String locationPreference,
                                 String preferredProgram, int[] universityRankingRange) {

        this.finAidRequirements = finAidRequirements;
        this.avgSalary = avgSalary;
        this.locationPreference = locationPreference;
        this.preferredProgram = preferredProgram;
        this.universityRankingRange = universityRankingRange;
    }

    public Integer getFinAidRequirement() {
        return this.finAidRequirements;
    }

    public Integer getAvgSalary() {
        return this.avgSalary;
    }

    public String getLocationPreference() {
        return this.locationPreference;
    }

    public String getPreferredProgram() {
        return this.preferredProgram;
    }

    public int[] getUniversityRankingRange() {
        return this.universityRankingRange;
    }
}
