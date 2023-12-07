package interface_adapter.results;

import java.util.Collections;
import java.util.List;

/**
 * The ResultsState class provides attributes that will be accessed by the ResultsViewModel and the ResultsView (via the
 * ResultsViewModel) later on. This allows access to universities (a list of University entities found through search
 * queries from the Search or ZipSearch use case), chosenUniversityString (the name of the university selected to get
 * more information on), resultsError (a specified error in the query) and prevView (the name of the view used before
 * this one, the name of the new active view when back is pressed).
 * @author Andre, Diego
 */
public class ResultsState {
    private List<String> universities = Collections.emptyList();
    private String chosenUniversityString = null;
    private String resultsError = null;
    public String prevView = "";

    /**
     * Instantiates the ResultsState.
     * @param copy
     */
    public ResultsState(ResultsState copy) {
        universities = copy.universities;
        chosenUniversityString = copy.chosenUniversityString;
        resultsError = copy.resultsError;
    }

    /**
     * Instantiates the ResultsState without any parameters so that the previous constructor can work properly.
     */
    public ResultsState() {
    }

    /**
     * Returns the universities attribute in the ResultsState object.
     * @return universities
     */
    public List<String> getUniversityNames() {return universities;}

    /**
     * Returns the chosenUniversityString attribute in the ResultsState object.
     * @return chosenUniversityString
     */
    public String getChosenUniversityString() {return chosenUniversityString;}

    /**
     * Returns the resultsError attribute in the ResultsState object.
     * @return resultsError
     */
    public String getResultsError() {return resultsError;}

    /**
     * Sets the prevView attribute of the ResultsState object.
     * @param prevView
     */
    public void setPreviousView(String prevView) {this.prevView = prevView;}

    /**
     * Sets the universities attribute of the ResultsState object.
     * @param universities
     */
    public void setUniversityNames(List<String> universities) {
        this.universities = universities;
    }

    /**
     * Sets the chosenUniversityString attribute of the ResultsState object.
     * @param string
     */
    public void setChosenUniversityString(String string) {this.chosenUniversityString = string;}

    /**
     * Sets the resultsError attribute of the ResultsState object.
     * @param resultsError
     */
    public void setSearchError(String resultsError) {this.resultsError = resultsError;}

    /**
     * Returns the ResultsState and the universities attribute as a string.
     * @return A formatted string of ResultsState including its universities attribute.
     */
    @Override
    public String toString() {
        return "ResultsState{ " +
                "Universities='" + universities + '\'' +
                " }";
    }
}
