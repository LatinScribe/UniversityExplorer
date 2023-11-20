package use_case.user_profile;

import java.lang.reflect.Array;

public class UserProfileOutputData {

    private Integer finAidRequirements;
    private Integer avgSalary;
    private String locationPreference;
    private String preferredProgram;
    private Integer[] universityRankingRange;

    public UserProfileOutputData(Integer finAidRequirements, Integer avgSalary, String locationPreference,
                                 String preferredProgram, Integer[] universityRankingRange) {

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

    public Integer[] getUniversityRankingRange() {
        return this.universityRankingRange;
    }
}
