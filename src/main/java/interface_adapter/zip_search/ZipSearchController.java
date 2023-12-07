// Author: Diego
package interface_adapter.zip_search;


import use_case.zip_search.ZipSearchInputBoundary;
import use_case.zip_search.ZipSearchInputData;

/**
 * A class that creates ZipSearchInputData and pass it to the ZipSearchInteractor.
 * then, ZipSearchInteractor executes search for universities.
 * @author Diego
 */
public class ZipSearchController {

    final ZipSearchInputBoundary userZipSearchUseCaseInteractor;

    /**
     * Instantiates the ZipSearchController
     * @param userZipSearchUseCaseInteractor
     */
    public ZipSearchController(ZipSearchInputBoundary userZipSearchUseCaseInteractor) {
        this.userZipSearchUseCaseInteractor = userZipSearchUseCaseInteractor;
    }

    /**
     * Instantiates a ZipSearchInputData object and passes it to the ZipSearchInteractor.
     * @param zipSearchCriteria
     * @param radSearchCriteria
     */
    public void executeSearch(String zipSearchCriteria, String radSearchCriteria) {
        ZipSearchInputData zipSearchInputData = new ZipSearchInputData(zipSearchCriteria, radSearchCriteria);

        userZipSearchUseCaseInteractor.executeSearch(zipSearchInputData);
    }

    /**
     * Calls the zipSearchInteractor to instantiate the back use case
     */
    public void executeBack() {
        userZipSearchUseCaseInteractor.executeBack();
    }
}
