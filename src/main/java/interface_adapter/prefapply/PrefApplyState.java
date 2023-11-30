package interface_adapter.prefapply;

import entity.University;
import interface_adapter.apply.ApplyState;

public class PrefApplyState {
    private String act = "";
    private String sat = "";
    private String actError = null;
    private String satError = null;
    private String submitStatus = null;
    private University university = null;

    private String universityError = null;

    public PrefApplyState(PrefApplyState copy) {
        act = copy.act;
        sat = copy.sat;
        actError = copy.actError;
        satError = copy.satError;
        submitStatus = copy.submitStatus;
        university = copy.university;
        universityError = copy.universityError;
    }

    public PrefApplyState() {
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
    public University getUni(){return university;}
    public String getUniversityError(){return universityError;}

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
    public void setUni(University university){this.university = university;}
    public void setUniversityError(String universityError){this.universityError = universityError;}

    @Override
    public String toString() {
        return "prefApplyState{" +
                "act='" + act + '\'' +
                ", sat=" + sat +
                '}';
    }
}
