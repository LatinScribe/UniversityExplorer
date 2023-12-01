// Author: André
package use_case.results;

import use_case.search.SearchInputData;

public interface ResultsInputBoundary {
    void executeUniPress(ResultsInputData resultsInputData);

    void executeBack();
}
