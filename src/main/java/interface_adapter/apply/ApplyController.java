// Author: Bora
package interface_adapter.apply;

import use_case.apply.ApplyInputBoundary;
import use_case.apply.ApplyInputData;

public class ApplyController {
    final ApplyInputBoundary applyUseCaseInteractor;
    public ApplyController( ApplyInputBoundary applyUseCaseInteractor) {
        this.applyUseCaseInteractor = applyUseCaseInteractor;
    }

    public void execute(String satScore , String actScore) throws IllegalArgumentException {

        ApplyInputData applyInputData = new ApplyInputData(satScore,actScore);
        applyUseCaseInteractor.execute(applyInputData);

    }
    public void executeBack() {
        applyUseCaseInteractor.executeBack();
    }
}
