// Author: André
package use_case.results;

import entity.University;

import java.util.List;

public class ResultsOutputData {
    private University university;

    public ResultsOutputData(University university) {
        this.university = university;
    }

    public University getUniversity() {
        return university;
    }
}
