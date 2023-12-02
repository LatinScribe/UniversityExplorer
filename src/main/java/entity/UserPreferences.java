package entity;

public class UserPreferences implements UserProfile {

    // Refer to the API documentation for the meaning of these fields.

    private final Integer finAidRequirement;

    private final String preferredProgram;

    private final Integer avgSalary;

    private final int[] universityRankingRange;

    private final String locationPreference;


    public UserPreferences(Integer finAidRequirement, String preferredProgram, Integer avgSalary,
                           int[] universityRankingRange, String locationPreference) {
        this.finAidRequirement = finAidRequirement;
        this.preferredProgram = preferredProgram;
        this.avgSalary = avgSalary;
        this.universityRankingRange = universityRankingRange;
        this.locationPreference = locationPreference;
    }

    @Override
    public Integer getFinAidRequirement() {
        return finAidRequirement;
    }

    @Override
    public String getPreferredProgram() {
        return preferredProgram;
    }

    @Override
    public Integer getAvgSalary() {
        return avgSalary;
    }

    @Override
    public int[] getUniversityRankingRange() {
        return universityRankingRange;
    }

    @Override
    public String getLocationPreference() {
        return locationPreference;
    }
}
