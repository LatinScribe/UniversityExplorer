package use_case.zip_search;

import entity.University;

import java.util.List;

/**
 * A class compiling a list of University entities into a SearchOutputData object.
 * This will be passed onto the ZipSearchPresenter to display the data found here to the user.
 * @author Diego
 */
public class ZipSearchOutputData {
    private List<University> universities;

    /**
     * Instantiates a ZipSearchOutputData object.
     * @param universities The list of universities
     */
    public ZipSearchOutputData(List<University> universities) {
        this.universities = universities;
    }

    /**
     * Returns the universities attribute of the ZipSearchOutputData object.
     * @return String
     */
    public List<University> getUniversities() {
        return universities;
    }
}
