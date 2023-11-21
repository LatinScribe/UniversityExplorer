// Author: André

package use_case.search;

import java.security.KeyException;

public interface SearchInputBoundary {
    void executeSearch(SearchInputData searchInputData);

    void executeBack();
}
