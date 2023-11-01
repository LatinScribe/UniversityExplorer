package interface_adapter.search;

public class SearchState {
    private String searchCriteria = "";
    private String searchError = null;

    public SearchState(SearchState copy) {
        searchCriteria = copy.searchCriteria;
        searchError = copy.searchError;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public SearchState() {
    }

    public String getSearchCriteria() {
        return searchCriteria;
    }

    public String getSearchError() {
        return searchError;
    }

    public void setSearchCriteria(String searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    public void setSearchError(String searchError) {
        this.searchError = searchError;
    }

    @Override
    public String toString() {
        return "SearchState{" +
                "username='" + searchCriteria + '\'' +
                '}';
    }
}
