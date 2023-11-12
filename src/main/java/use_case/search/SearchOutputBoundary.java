// Author: André

package use_case.search;

import entity.University;

import java.util.List;

public interface SearchOutputBoundary {
    void prepareSuccessView(SearchOutputData universities);

    void prepareResultsNotFoundView(String error);

}