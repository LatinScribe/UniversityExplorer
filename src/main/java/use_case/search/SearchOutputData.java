package use_case.search;

import entity.University;
import java.util.List;

/**
 * A class compiling a list of University entities into a SearchOutputData object. This will be passed onto the
 * SearchPresenter to display the data found here to the user.
 * @author Andre
 */
public class SearchOutputData {

    private List<University> universities;

    /**
     * Instantiates a SearchOutputData object.
     * @param universities
     */
    public SearchOutputData(List<University> universities) {
        this.universities = universities;
    }

    /**
     * Returns the universities attribute of the SearchOutputData object.
     * @return universities
     */
    public List<University> getUniversities() {
        return universities;
    }

}
