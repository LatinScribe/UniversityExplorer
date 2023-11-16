// Author: Kanish
package interface_adapter.user_profiles;

public class UserProfileState {

    private String confirmEditMessage;

    private String failedEditMessage;

    private String confirmPublishMessage;

    private String failPublishMessage;

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
}
