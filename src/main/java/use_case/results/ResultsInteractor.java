package use_case.results;

import entity.University;
import entity.UniversityFactory;
import org.json.JSONArray;
import org.json.JSONObject;

public class ResultsInteractor implements ResultsInputBoundary{

    final ResultsUserDataAccessInterface resultsDataAccessObject;
    final ResultsOutputBoundary resultsPresenter;
    final UniversityFactory universityFactory;

    public ResultsInteractor(ResultsUserDataAccessInterface resultsDataAccessObject,
                            ResultsOutputBoundary resultsPresenter, UniversityFactory universityFactory) {
        this.resultsDataAccessObject = resultsDataAccessObject;
        this.resultsPresenter = resultsPresenter;
        this.universityFactory = universityFactory;
    }

    @Override
    public void executeUniPress(ResultsInputData resultsInputData) {
        String stringAccumulator = "";
        String input = resultsInputData.getUniversityName();
        for (int i = 0; i < input.length(); i++) {
            String cursor = input.substring(i, i+1);
            if (cursor.equals(" ")) {
                stringAccumulator += "%20";
            }
            else {
                stringAccumulator += cursor;
            }
        }
        JSONObject query = resultsDataAccessObject.searchQuery("school.name=" + stringAccumulator);
        JSONObject metadata = query.getJSONObject("metadata");
        if (metadata.getInt("total") == 0) {
            resultsPresenter.prepareFailView("University not found in database!");
        } else if (metadata.getInt("total") >= 2) {
            resultsPresenter.prepareFailView("Unexpected error: more than 1 university found");
        } else {
            University university = executeHelper(query.getJSONArray("results"));
            ResultsOutputData resultsOutputData = new ResultsOutputData(university);
            resultsPresenter.prepareUniPopup(resultsOutputData);
        }

    }

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
        Object admRateCheck = university.get("admissions.admission_rate.overall");
        Double admRate = doubleChecker(admRateCheck);
        Object outTuitCheck = university.get("cost.tuition.out_of_state");
        Integer outTuit = integerChecker(outTuitCheck);
        Object inTuitCheck = university.get("cost.tuition.in_state");
        Integer inTuit = integerChecker(inTuitCheck);
        Object avgSATCheck = university.get("admissions.sat_scores.average.overall");
        Double avgSAT = doubleChecker(avgSATCheck) ;
        Object avgACTCheck = university.get("admissions.act_scores.midpoint.cumulative");
        Double avgACT = doubleChecker(avgACTCheck);
        Object urlCheck = university.get("school.school_url");
        String url = stringChecker(urlCheck);

        return universityFactory.create(id, name, state, city, admRate, inTuit, outTuit, avgSAT, avgACT, url);
    }

    // The following 3 new methods are meant to check if the object returns a null type (JSONObject.null) or the proper type (Double, String, Integer).
    private Double doubleChecker(Object object) {
        String checker = object.toString();
        if (checker.equals("null")) {
            return null;
        }
        Float converter = (Float) object;
        return converter.doubleValue();
    }

    private Integer integerChecker(Object object) {
        String checker = object.toString();
        if (checker.equals("null")) {
            return null;
        }
        return (Integer) object;
    }

    private String stringChecker(Object object) {
        String checker = object.toString();
        if (checker.equals("null")) {
            return null;
        }
        return (String) object;
    }

    @Override
    public void executeBack() {
        resultsPresenter.prepareBackView();
    }
}
