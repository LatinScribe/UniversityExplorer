// Author: Kanish
package interface_adapter.user_profiles;

import java.lang.reflect.Array;

public class UserProfileState {

    private String confirmEditMessage;
    private String failedEditMessage;
    private String confirmPublishMessage;
    private String failPublishMessage;

    private Integer finAidRequirement;
    private Integer avgSalary;
    private String locationPreference;
    private String preferredProgram;
    private Integer[] universityRankingRange; // Assuming it's Integer[]

    public String getConfirmEditMessage() {return confirmEditMessage;}

    public String getFailedEditMessage() {return failedEditMessage;}

    public String getConfirmPublishMessage() {return confirmPublishMessage;}

    public String getFailPublishMessage() {return failPublishMessage;}

    public void setConfirmEditMessage(String s) {this.confirmEditMessage = s;}

    public void setFailedEditMessage(String failedEditMessage) {
        this.failedEditMessage = failedEditMessage;
    }

    public void setConfirmPublishMessage(String s) {
        this.confirmPublishMessage = s;
    }

    public void setFailPublishMessage(String failPublishMessage) {
        this.failPublishMessage = failPublishMessage;
    }

    public Integer getFinAidRequirement() {
        return finAidRequirement;
    }

    public void setFinAidRequirement(Integer finAidRequirement) {
        this.finAidRequirement = finAidRequirement;
    }

    public Integer getAvgSalary() {
        return avgSalary;
    }

    public void setAvgSalary(Integer avgSalary) {
        this.avgSalary = avgSalary;
    }

    public String getLocationPreference() {
        return locationPreference;
    }

    public void setLocationPreference(String locationPreference) {
        this.locationPreference = locationPreference;
    }

    public String getPreferredProgram() {
        return preferredProgram;
    }

    public void setPreferredProgram(String preferredProgram) {
        this.preferredProgram = preferredProgram;
    }

    public Integer[] getUniversityRankingRange() {
        return universityRankingRange;
    }

    public void setUniversityRankingRange(Integer[] universityRankingRange) {
        this.universityRankingRange = universityRankingRange;
    }
}
