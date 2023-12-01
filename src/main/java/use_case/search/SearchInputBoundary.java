// Author: André

package use_case.search;

public interface SearchInputBoundary {
    void executeSearch(SearchInputData searchInputData);

    void executeBack();
}
