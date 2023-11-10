// Author: Bora
package interface_adapter.apply;

public class ApplyState {
    private String username = "";
    private double gpa = 0.0;
    private String usernameError = null;
    private String gpaError = null;
    private String submitStatus = null;

    public ApplyState(ApplyState copy) {
        username = copy.username;
        gpa = copy.gpa;
        usernameError = copy.usernameError;
        gpaError = copy.gpaError;
        submitStatus = copy.submitStatus;
    }

    public ApplyState() {
    }

    public String getUsername() {
        return username;
    }

    public double getGpa() {
        return gpa;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public String getGpaError() {
        return gpaError;
    }

    public String getSubmitStatus() {
        return submitStatus;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public void setGpaError(String gpaError) {
        this.gpaError = gpaError;
    }

    public void setSubmitStatus(String submitStatus) {
        this.submitStatus = submitStatus;
    }

    @Override
    public String toString() {
        return "ApplyState{" +
                "username='" + username + '\'' +
                ", gpa=" + gpa +
                '}';
    }
}
