package use_case.apply;


public interface ApplyOutputBoundary {
    void prepareSuccessView(ApplyOutputData university);

    void prepareFailView(String error);
}
