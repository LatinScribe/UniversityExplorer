package use_case.results;

public interface ResultsOutputBoundary {
    void prepareUniPopup(ResultsOutputData resultsOutputData);
    void prepareFailView(String error);
    void prepareBackView();
}
