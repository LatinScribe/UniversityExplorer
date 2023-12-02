package use_case.prefapply;

public interface PrefApplyInputBoundary {
    void execute(PrefApplyInputData prefapplyInputData);

    void executeBack();
}
