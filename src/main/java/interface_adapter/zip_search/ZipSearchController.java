// Author: Diego
package interface_adapter.zip_search;

import use_case.search.SearchInputBoundary;
import use_case.search.SearchInputData;
import use_case.zip_search.ZipSearchInputBoundary;
import use_case.zip_search.ZipSearchInputData;

public class ZipSearchController {

    final ZipSearchInputBoundary userZipSearchUseCaseInteractor;

    public ZipSearchController(ZipSearchInputBoundary userZipSearchUseCaseInteractor) {
        this.userZipSearchUseCaseInteractor = userZipSearchUseCaseInteractor;
    }
    public void executeSearch(String zipSearchCriteria, String radSearchCriteria) {
        ZipSearchInputData zipSearchInputData = new ZipSearchInputData(zipSearchCriteria, radSearchCriteria);

        userZipSearchUseCaseInteractor.executeSearch(zipSearchInputData);
    }

    public void executeBack() {
        userZipSearchUseCaseInteractor.executeBack();
    }
}
