// Author: André

package use_case.search;

public interface SearchOutputBoundary {
    void prepareSuccessView(SearchOutputData universities);

    void prepareResultsNotFoundView(String error);

}