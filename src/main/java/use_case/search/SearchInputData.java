// Author: André

package use_case.search;

public class SearchInputData {

    final private String searchCriteria;

    public SearchInputData(String searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    public String getSearchCriteria() {
        return searchCriteria;
    }

}
