// Author: Bora
package interface_adapter.apply;

import use_case.apply.ApplyInputBoundary;

public class ApplyController {
    final ApplyInputBoundary applyUseCaseInteractor;
    public ApplyController(ApplyViewModel applyViewModel, ApplyInputBoundary applyUseCaseInteractor) {
        this.applyUseCaseInteractor = applyUseCaseInteractor;
    }

    public void execute(){

    }
}
