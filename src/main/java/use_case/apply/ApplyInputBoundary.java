package use_case.apply;

public interface ApplyInputBoundary {
    void execute(ApplyInputData applyInputData);

    void executeBack();
}
