package use_case.results;

import entity.University;

/**
 * A class compiling University entity into a ResultsOutputData object. This will be passed onto the ResultsPresenter to
 * display the data found here to the user.
 * @author Andre
 */
public class ResultsOutputData {

    private University university;

    /**
     * Instantiates a ResultsOutputData object.
     * @param university
     */
    public ResultsOutputData(University university) {
        this.university = university;
    }

    /**
     * Returns the university attribute of the ResultsOutputData object.
     * @return university
     */
    public University getUniversity() {
        return university;
    }
}
