package use_case.apply;

import entity.University;

public class ApplyOutputData {
    private University university;
    public ApplyOutputData(University university){this.university=university;}
    public University getUniversity(){return university;}
}
