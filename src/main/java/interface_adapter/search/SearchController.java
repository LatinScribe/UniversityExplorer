package interface_adapter.search;

import use_case.search.SearchInputBoundary;
import use_case.search.SearchInputData;

/**
 * A class that, depending on the action by the user, can create SearchInputData and pass this data off to be handled
 * by the SearchInteractor. In either case, the SearchInteractor is called to perform the action requested by the user.
 * @author Andre
 */
public class SearchController {

    final SearchInputBoundary userSearchUseCaseInteractor;

    /**
     * Instantiates a SearchController.
     * @param userSearchUseCaseInteractor
     */
    public SearchController(SearchInputBoundary userSearchUseCaseInteractor) {
        this.userSearchUseCaseInteractor = userSearchUseCaseInteractor;
    }

    /**
     * Instantiates a SearchInputData object and passes it through to the SearchInteractor. This SearchInputData object
     * contains a String used to query the database later on in the action.
     * @param searchCriteria
     */
    public void executeSearch(String searchCriteria) {
        SearchInputData searchInputData = new SearchInputData(searchCriteria);

        userSearchUseCaseInteractor.executeSearch(searchInputData);
    }

    /**
     * Calls on the SearchInteractor to instantiate the back action. As this only involves the switching of views,
     * no SearchInputData is passed in.
     */
    public void executeBack() {
        userSearchUseCaseInteractor.executeBack();
    }


}
