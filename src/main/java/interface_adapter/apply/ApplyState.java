// Author: Bora
package interface_adapter.apply;

public class ApplyState {
    private String act = "";
    private String sat = "";
    private String actError = null;
    private String satError = null;
    private String submitStatus = null;

    public ApplyState(ApplyState copy) {
        act = copy.act;
        sat = copy.sat;
        actError = copy.actError;
        satError = copy.satError;
        submitStatus = copy.submitStatus;
    }

    public ApplyState() {
    }

    public String getAct() {
        return act;
    }

    public String getSat() {
        return sat;
    }

    public String getActError() {
        return actError;
    }

    public String getSatError() {
        return satError;
    }

    public String getSubmitStatus() {
        return submitStatus;
    }

    public void setAct(String username) {
        this.act = username;
    }

    public void setSat(String sat) {
        this.sat = sat;
    }

    public void setActError(String usernameError) {
        this.actError = actError;
    }

    public void setSatError(String satError) {
        this.satError = satError;
    }

    public void setSubmitStatus(String submitStatus) {
        this.submitStatus = submitStatus;
    }

    @Override
    public String toString() {
        return "ApplyState{" +
                "act='" + act + '\'' +
                ", sat=" + sat +
                '}';
    }
}
