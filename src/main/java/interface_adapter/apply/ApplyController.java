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

        // Input validation
        if (satScore == ""){
            throw new IllegalArgumentException("sat score cant be empty");
        }

        if (Integer.parseInt(satScore) >= 1600 || Integer.parseInt(satScore) < 0) {
            throw new IllegalArgumentException("Sat Score has to be between 0-1600");
        }
        if ( Integer.parseInt(actScore) >= 36 || Integer.parseInt(actScore) < 0 ) {
            throw new IllegalArgumentException("Act score should be between 0- 36");
        }


        ApplyInputData applyInputData = new ApplyInputData(satScore,actScore);
        applyUseCaseInteractor.execute(applyInputData);

    }
    public void executeBack() {
        applyUseCaseInteractor.executeBack();
    }
}
