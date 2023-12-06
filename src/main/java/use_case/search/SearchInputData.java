package use_case.search;

/**
 * A class that turns the searchCriteria string (input by the user) into a SearchInputData object.
 * @author Andre
 */
public class SearchInputData {

    final private String searchCriteria;

    /**
     * Instantiates a SearchInputData object.
     * @param searchCriteria
     */
    public SearchInputData(String searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    /**
     * Returns the searchCriteria attribute of the SearchInputData object.
     * @return String
     */
    public String getSearchCriteria() {
        return searchCriteria;
    }

}
