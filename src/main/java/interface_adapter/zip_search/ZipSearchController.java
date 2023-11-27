// Author: Diego
package interface_adapter.zip_search;

import use_case.search.SearchInputBoundary;
import use_case.search.SearchInputData;
import use_case.zip_search.ZipSearchInputBoundary;

public class ZipSearchController {

    final ZipSearchInputBoundary userZipSearchUseCaseInteractor;

    public ZipSearchController(ZipSearchInputBoundary userZipSearchUseCaseInteractor) {
        this.userZipSearchUseCaseInteractor = userZipSearchUseCaseInteractor;
    }
    public void executeSearch(String searchCriteria) {
        SearchInputData searchInputData = new SearchInputData(searchCriteria);

        userZipSearchUseCaseInteractor.executeSearch(searchInputData);
    }

    public void executeBack() {
        userZipSearchUseCaseInteractor.executeBack();
    }
}
