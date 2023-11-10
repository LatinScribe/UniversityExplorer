// Author: André

package use_case.search;

import java.time.LocalDateTime;

public class SearchInteractor implements SearchInputBoundary {
    final SearchUserDataAccessInterface searchDataAccessObject;
    final SearchOutputBoundary searchPresenter;

    public SearchInteractor(SearchUserDataAccessInterface signupDataAccessInterface,
                            SearchOutputBoundary signupOutputBoundary) {
        this.searchDataAccessObject = signupDataAccessInterface;
        this.searchPresenter = signupOutputBoundary;
    }

    @Override
    public void execute(SearchInputData searchInputData) {

    }
}