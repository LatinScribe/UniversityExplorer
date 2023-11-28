package use_case.prefapply;

import entity.University;
import entity.UniversityFactory;
import org.json.JSONArray;
import org.json.JSONObject;

public class PrefApplyInteractor implements PrefApplyInputBoundary{
    final PrefApplyDataAccessInterface prefapplyDataAccessObject;
    final PrefApplyOutputBoundary prefapplyPresenter;
    final UniversityFactory universityFactory;

    public PrefApplyInteractor(PrefApplyDataAccessInterface prefapplyDataAccessObject, PrefApplyOutputBoundary prefapplyOutputBoundary, UniversityFactory universityFactory) {
        this.prefapplyDataAccessObject = prefapplyDataAccessObject;
        this.prefapplyPresenter = prefapplyOutputBoundary;
        this.universityFactory = universityFactory;
    }

    @Override
    public void execute(PrefApplyInputData prefapplyInputData) {
        String actScore = prefapplyInputData.getActScore();
        String satScore = prefapplyInputData.getSatScore();
        int intsatScore = Integer.parseInt(satScore);
        int intactScore = Integer.parseInt(actScore);
        String queryParameters1 = "2018.admissions.sat_scores.average.overall__range="+Integer.toString(intsatScore-100)+"..."+Integer.toString(intsatScore+50);
        String optionalParameters = "fields=id,school.name,school.state,school.city,admissions.admission_rate.overall,cost.tuition.in_state,cost.tuition.out_of_state,2018.admissions.sat_scores.average.overall,admissions.act_scores.midpoint.cumulative,school.school_url";
        JSONObject query1= prefapplyDataAccessObject.basicQuery(queryParameters1,optionalParameters);
        String queryParameters2 = "2018.admissions.act_scores.midpoint.cumulative__range="+Integer.toString(intactScore-2)+"..."+Integer.toString(intactScore+2);
        JSONObject query2 = prefapplyDataAccessObject.basicQuery(queryParameters2,optionalParameters);
        University uni1 = executeHelper(query1.getJSONArray("results"));
        University uni2 = executeHelper(query2.getJSONArray("results"));
        University chosenUni = null;
        if (uni1.getAverageSATScore() == null && uni2.getAverageSATScore() == null){ prefapplyPresenter.prepareFailView("Error");}
        else if (uni2.getAverageSATScore() == null) { chosenUni = uni1;}
        else if (uni1.getAverageSATScore() == null) { chosenUni = uni2;}
        else {
            if (uni1.getAverageSATScore() >= uni2.getAverageSATScore()){
                chosenUni = uni1;
            }
            else {
                chosenUni = uni2;
            }}
        JSONObject metadata1 = query1.getJSONObject("metadata");
        JSONObject metadata2 = query2.getJSONObject("metadata");

        if (metadata1.getInt("total") == 0 && metadata2.getInt("total") == 0) {
            prefapplyPresenter.prepareFailView("Error");
        }
        else {
            PrefApplyOutputData prefapplyOutputData = new PrefApplyOutputData(chosenUni);
            prefapplyPresenter.prepareSuccessView(prefapplyOutputData);
        }





    }

    @Override
    public void executeBack(){
        prefapplyPresenter.prepareBackView();
    }

    private University executeHelper(JSONArray results) {
        int largestindex = 0;
        Integer largestSat = 0;
        for (int i = 0; i < results.length(); i++) {
            // After every object is extracted, temporarily save its value as an object and check if it's null before implementing.
            JSONObject university = (JSONObject) results.get(i);
            Object avgSATCheck = university.get("2018.admissions.sat_scores.average.overall");
            Integer avgSAT = integerChecker(avgSATCheck) ;
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
        Object avgSATCheck = university.get("2018.admissions.sat_scores.average.overall");
        Integer avgSAT = integerChecker(avgSATCheck) ;
        Object avgACTCheck = university.get("admissions.act_scores.midpoint.cumulative");
        Double avgACT = doubleChecker(avgACTCheck);
        Object urlCheck = university.get("school.school_url");
        String url = stringChecker(urlCheck);
        University newUniversity = universityFactory.create(id, name, state, city, admRate, inTuit, outTuit, Double.valueOf(avgSAT), avgACT, url);
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
