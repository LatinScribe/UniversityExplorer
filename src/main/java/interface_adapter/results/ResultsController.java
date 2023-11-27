// Author: André

package interface_adapter.results;

import use_case.results.ResultsInputBoundary;
import use_case.results.ResultsInputData;
import use_case.search.SearchInputBoundary;
import use_case.search.SearchInputData;

public class ResultsController {

    final ResultsInputBoundary resultsUseCaseInteractor;

    public ResultsController(ResultsInputBoundary resultsUseCaseInteractor) {
        this.resultsUseCaseInteractor = resultsUseCaseInteractor;
    }
    public void executeUniPress(String searchCriteria) {
        ResultsInputData searchInputData = new ResultsInputData(searchCriteria);

        resultsUseCaseInteractor.executeUniPress(searchInputData);
    }

    public void executeBack() {
        resultsUseCaseInteractor.executeBack();
    }
}
