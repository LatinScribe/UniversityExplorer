package use_case.prefapply;


public interface PrefApplyOutputBoundary {
    void prepareSuccessView(PrefApplyOutputData university);

    void prepareFailView(String error);

    void prepareBackView();
}
