package interface_adapter.prefapply;

import use_case.apply.ApplyInputBoundary;
import use_case.apply.ApplyInputData;
import use_case.prefapply.PrefApplyInputBoundary;
import use_case.prefapply.PrefApplyInputData;

public class PrefApplyController {
    final PrefApplyInputBoundary prefapplyUseCaseInteractor;
    public PrefApplyController( PrefApplyInputBoundary prefapplyUseCaseInteractor) {
        this.prefapplyUseCaseInteractor = prefapplyUseCaseInteractor;
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


        PrefApplyInputData prefapplyInputData = new PrefApplyInputData(satScore,actScore);
        prefapplyUseCaseInteractor.execute(prefapplyInputData);

    }
    public void executeBack() {
        prefapplyUseCaseInteractor.executeBack();
    }
}
