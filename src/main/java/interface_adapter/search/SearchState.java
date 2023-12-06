package interface_adapter.search;

/**
 * The SearchState class provides attributes that will be accessed by the SearchView and SearchViewModel later on. This
 * allows access to the searchCriteria used for the search query, the searchError used to report any errors by either
 * the API or user input and the prevView used to return to the previous view when back is pressed.
 * @author Andre
 */
public class SearchState {
    private String searchCriteria = "";
    private String searchError = null;
    private String prevView = "";

    /**
     * Instantiates the SearchState.
     * @param copy
     */
    public SearchState(SearchState copy) {
        searchCriteria = copy.searchCriteria;
        searchError = copy.searchError;
    }

    /**
     * Instantiates the SearchState without any parameters so that the previous constructor can work properly.
     */
    public SearchState() {
    }

    /**
     * Returns the searchCriteria attribute of the SearchState object.
     * @return searchCriteria
     */
    public String getSearchCriteria() {
        return searchCriteria;
    }

    /**
     * Returns the searchError attribute of the SearchState object.
     * @return searchError
     */
    public String getSearchError() {
        return searchError;
    }

    /**
     * Returns the prevView attribute of the SearchState object.
     * @return prevView
     */
    public String getPrevView() {return prevView;}

    /**
     * Sets the searchCriteria attribute of the SearchState object.
     */
    public void setSearchCriteria(String searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    /**
     * Sets the searchError attribute of the SearchState object.
     */
    public void setSearchError(String searchError) {
        this.searchError = searchError;
    }

    /**
     * Sets the prevView attribute of the SearchState object.
     */
    public void setPrevView(String prevView) {this.prevView = prevView;}

    /**
     * Returns the SignupState and its searchCriteria attribute as a String.
     * @return A formatted String of the SignupState with its searchCriteria.
     */
    @Override
    public String toString() {
        return "SignupState{" +
                "searchCriteria='" + searchCriteria + '\'' +
                " }";
        }
    }
