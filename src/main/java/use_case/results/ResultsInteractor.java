package use_case.results;

import entity.University;
import entity.UniversityFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;

/**
 * A class which provides the main execution of the actions inputted by the user. This involves calling on data
 * access objects to query the JsonCollegeScorecardDB and creation of University entities to be displayed back
 * to the user later on in a popup in the ResultsView, as well as alerting the ResultsPresenter to
 * @author Andre
 */
public class ResultsInteractor implements ResultsInputBoundary{

    final ResultsUserDataAccessInterface resultsDataAccessObject;
    final ResultsOutputBoundary resultsPresenter;
    final UniversityFactory universityFactory;

    /**
     * Instantiates the ResultsInteractor.
     * @param resultsDataAccessObject
     * @param resultsPresenter
     * @param universityFactory
     */
    public ResultsInteractor(ResultsUserDataAccessInterface resultsDataAccessObject,
                            ResultsOutputBoundary resultsPresenter, UniversityFactory universityFactory) {
        this.resultsDataAccessObject = resultsDataAccessObject;
        this.resultsPresenter = resultsPresenter;
        this.universityFactory = universityFactory;
    }

    /**
     * A msthod of the SearchInteractor, which provides ResultsOutputData in the form of a single university to send to
     * the ResultsPresenter. This is queried through the database.
     * @param resultsInputData (containing a string used for the database query)
     */
    @Override
    public void executeUniPress(ResultsInputData resultsInputData) {
        String stringAccumulator = "";
        String input = resultsInputData.getUniversityName();
        if (input == null) {
            resultsPresenter.prepareFailView("No uni selected!");
            return;
        }
        for (int i = 0; i < input.length(); i++) {
            String cursor = input.substring(i, i+1);
            if (cursor.equals(" ")) {
                stringAccumulator += "%20";
            }
            else {
                stringAccumulator += cursor;
            }
        }
        try {
            JSONObject query = resultsDataAccessObject.searchQuery("school.name=" + stringAccumulator);
            JSONObject metadata = query.getJSONObject("metadata");
            if (metadata.getInt("total") == 0) {
                resultsPresenter.prepareFailView("Results not found!");
            } else {
                University university = executeHelper(query.getJSONArray("results"));
                ResultsOutputData resultsOutputData = new ResultsOutputData(university);
                resultsPresenter.prepareUniPopup(resultsOutputData);
            }
        } catch (JSONException e) {
            resultsPresenter.prepareFailView("JSON Error! (" + e + ")");
        }
    }

    /**
     * Takes in a JsonArray and reformats it to a single University object.
     * @param results
     * @return University
     */
    private University executeHelper(JSONArray results) {
        JSONObject university = (JSONObject) results.get(0);
        Object idCheck = university.get("id");
        Integer id = integerChecker(idCheck);
        Object nameCheck = university.get("school.name");
        String name = stringChecker(nameCheck);
        Object stateCheck = university.get("school.state");
        String state = stringChecker(stateCheck);
        Object cityCheck = university.get("school.city");
        String city = stringChecker(cityCheck);
        Object admRateCheck = university.get("2018.admissions.admission_rate.overall");
        Double admRate = null;
        if (admRateCheck.toString().equals("0") || admRateCheck.toString().equals("1")) {
            Integer transition = integerChecker(admRateCheck);
            admRate = transition.doubleValue();
        }
        else {
            admRate = doubleChecker(admRateCheck);
        }
        Object outTuitCheck = university.get("2018.cost.tuition.out_of_state");
        Integer outTuit = integerChecker(outTuitCheck);
        Object inTuitCheck = university.get("2018.cost.tuition.in_state");
        Integer inTuit = integerChecker(inTuitCheck);
        Object avgSATCheck = university.get("2018.admissions.sat_scores.average.overall");
        Integer avgSAT = integerChecker(avgSATCheck) ;
        Object avgACTCheck = university.get("2018.admissions.act_scores.midpoint.cumulative");
        Integer avgACT = integerChecker(avgACTCheck);
        Object urlCheck = university.get("school.school_url");
        String url = stringChecker(urlCheck);

        return universityFactory.create(id, name, state, city, admRate, inTuit, outTuit, avgSAT, avgACT, url);
    }

    // The following 3 new methods are meant to check if the object returns a null type (JSONObject.null) or the proper
    // type (Double, String, Integer).

    /**
     * A helper method that helps to determine the type of the object returned from a specific query result. If this
     * object is not null, return the value of the BigDecimal value passed in as a Double.
     * @param object
     * @return Double
     */
    private Double doubleChecker(Object object) {
        String checker = object.toString();
        if (checker.equals("null")) {
            return null;
        }
        BigDecimal converter = (BigDecimal) object;
        return converter.doubleValue();
    }

    /**
     * A helper method that helps to determine the type of the object returned from a specific query result. If this
     * object is not null, return the value of the Integer passed in.
     * @param object
     * @return Integer
     */
    private Integer integerChecker(Object object) {
        String checker = object.toString();
        if (checker.equals("null")) {
            return null;
        }
        return (Integer) object;
    }

    /**
     * A helper method that helps to determine the type of the object returned from a specific query result. If this
     * object is not null, return the value of the String passed in.
     * @param object
     * @return String
     */
    private String stringChecker(Object object) {
        String checker = object.toString();
        if (checker.equals("null")) {
            return null;
        }
        return (String) object;
    }

    /**
     * A method of the Results use case. Calls the presenter to switch the active view back to the previous view.
     */
    @Override
    public void executeBack() {
        resultsPresenter.prepareBackView();
    }
}
