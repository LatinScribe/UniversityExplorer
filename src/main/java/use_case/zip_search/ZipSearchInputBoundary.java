// Author: Diego
package use_case.zip_search;

import use_case.search.SearchInputData;

public interface ZipSearchInputBoundary {
    void executeSearch(ZipSearchInputData zipSearchInputData);

    void executeBack();
}
