package use_case.zip_search;

import use_case.search.SearchInputData;

public interface ZipSearchInputBoundary {
    void executeSearch(SearchInputData searchInputData);

    void executeBack();
}
