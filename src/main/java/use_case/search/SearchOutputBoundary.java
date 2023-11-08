// Author: André

package use_case.search;

public interface SearchOutputBoundary {
    void prepareSuccessView(SearchOutputData user);

    void prepareFailView(String error);
}