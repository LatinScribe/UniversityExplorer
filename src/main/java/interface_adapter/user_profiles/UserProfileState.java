// Author: Kanish
package interface_adapter.user_profiles;

public class UserProfileState {

    private String confirmEditMessage;
    private String failedEditMessage;
    private String confirmPublishMessage;
    private String failPublishMessage;

    private Integer finAidRequirement;
    private String preferredProgram;
    private Integer avgSalary;
    private Integer[] universityRankingRange;
    private String locationPreference;

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

    public void setFinAidRequirement(Integer finAidRequirement) {
        this.finAidRequirement = finAidRequirement;
    }
    public void setPreferredProgram(String preferredProgram) {
        this.preferredProgram = preferredProgram;
    }
    public void setAvgSalary(Integer avgSalary) {
        this.avgSalary = avgSalary;
    }
    public void setUniversityRankingRange(Integer[] universityRankingRange) {
        this.universityRankingRange = universityRankingRange;
    }
    public void setLocationPreference(String locationPreference) {
        this.locationPreference = locationPreference;
    }
}
