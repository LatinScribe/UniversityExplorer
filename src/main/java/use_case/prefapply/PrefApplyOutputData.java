package use_case.prefapply;

import entity.University;

public class PrefApplyOutputData {
    private University university;
    public PrefApplyOutputData(University university){this.university=university;}
    public University getUniversity(){return university;}
}
