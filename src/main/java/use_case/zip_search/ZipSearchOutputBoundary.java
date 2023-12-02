// Author: Diego
package use_case.zip_search;


public interface ZipSearchOutputBoundary {
    void prepareSuccessView(ZipSearchOutputData universities);

    void prepareResultsNotFoundView(String error);

    void prepareBackView();

}
