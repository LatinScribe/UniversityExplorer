package interface_adapter.results;

import use_case.results.ResultsInputBoundary;
import use_case.results.ResultsInputData;

/**
 * A class that, depending on the action by the user, can create ResultsInputData and pass this data off to be handled
 * by the ResultsInteractor. In either case, the ResultsInteractor is called to perform the action requested by the
 * user.
 * @author Andre
 */
public class ResultsController {

    final ResultsInputBoundary resultsUseCaseInteractor;

    /**
     * Instantiates a ResultsController.
     * @param resultsUseCaseInteractor
     */
    public ResultsController(ResultsInputBoundary resultsUseCaseInteractor) {
        this.resultsUseCaseInteractor = resultsUseCaseInteractor;
    }

    /**
     * Instantiates a ResultsInputData object and passes it through to the ResultsInteractor. This ResultsInputData
     * object contains a String used to query the database later on in the action.
     * @param resultsCriteria
     */
    public void executeUniPress(String resultsCriteria) {
        ResultsInputData resultsInputData = new ResultsInputData(resultsCriteria);

        resultsUseCaseInteractor.executeUniPress(resultsInputData);
    }

    /**
     * Calls on the ResultsInteractor to instantiate the back action. As this only involves the switching of views,
     * no ResultsInputData is passed in.
     */
    public void executeBack() {
        resultsUseCaseInteractor.executeBack();
    }
}
