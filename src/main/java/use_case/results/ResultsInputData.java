package use_case.results;

/**
 * A class that turns the universityName string (selected in the ResultsView by a user) into a ResultsInputData object.
 * @author Andre
 */
public class ResultsInputData {

    final private String universityName;

    /**
     * Instantiates a SearchInputData object.
     * @param universityName
     */
    public ResultsInputData(String universityName) {
        this.universityName = universityName;
    }

    /**
     * Returns the universityName attribute of the ResultsInputData object.
     * @return universityName
     */
    public String getUniversityName() {
        return universityName;
    }
}
