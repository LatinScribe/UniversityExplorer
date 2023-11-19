package use_case.apply;

import entity.University;
import entity.UniversityFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class ApplyInteractor implements ApplyInputBoundary {
    final ApplyDataAccessInterface applyDataAccessObject;
    final ApplyOutputBoundary applyPresenter;
    final UniversityFactory universityFactory;

    public ApplyInteractor(ApplyDataAccessInterface applyDataAccessObject, ApplyOutputBoundary applyOutputBoundary, UniversityFactory universityFactory) {
        this.applyDataAccessObject = applyDataAccessObject;
        this.applyPresenter = applyOutputBoundary;
        this.universityFactory = universityFactory;
    }

    @Override
    public void execute(ApplyInputData applyInputData) {
        String actScore = applyInputData.getActScore();
        String satScore = applyInputData.getSatScore();
        String queryParameters1 = "2018.admissions.sat_scores.average.overall="+satScore;
        String optionalParameters = "fields=id,school.name,school.state,school.city,admissions.admission_rate.overall,cost.tuition.in_state,cost.tuition.out_of_state,admissions.sat_scores.average.overall,admissions.act_scores.midpoint.cumulative,school.school_url";
        JSONObject query1= applyDataAccessObject.basicQuery(queryParameters1,optionalParameters);
        String queryParameters2 = "2018.admissions.act_scores.midpoint.cumulative="+actScore;
        JSONObject query2 = applyDataAccessObject.basicQuery(queryParameters2,optionalParameters);
        University uni1 = executeHelper(query1.getJSONArray("results"));
        University uni2 = executeHelper(query2.getJSONArray("results"));
        University chosenUni = null;
        if (uni1.getAverageSATScore() >= uni2.getAverageSATScore()){
            chosenUni = uni1;
        }
        else {
            chosenUni = uni2;
        }
        JSONObject metadata1 = query1.getJSONObject("metadata");
        JSONObject metadata2 = query2.getJSONObject("metadata");

        if (metadata1.getInt("total") == 0 && metadata2.getInt("total") == 0) {
            applyPresenter.prepareFailView("Error");
        }
        else {
            ApplyOutputData applyOutputData = new ApplyOutputData(chosenUni);
            applyPresenter.prepareSuccessView(applyOutputData);
        }





    }
    private University executeHelper(JSONArray results) {
        int largestindex = 0;
        Double largestSat = 0.0;
        for (int i = 0; i < results.length(); i++) {
            // After every object is extracted, temporarily save its value as an object and check if it's null before implementing.
            JSONObject university = (JSONObject) results.get(i);
            Object avgSATCheck = university.get("admissions.sat_scores.average.overall");
            Double avgSAT = doubleChecker(avgSATCheck) ;
            if (avgSAT != null){
               if (avgSAT > largestSat){ largestSat = avgSAT;
                  largestindex = i;}
            }
        }
        JSONObject university = (JSONObject) results.get(largestindex);
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
        University newUniversity = universityFactory.create(id, name, state, city, admRate, inTuit, outTuit, avgSAT, avgACT, url);
        return newUniversity;}
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
}
