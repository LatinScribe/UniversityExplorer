package use_case.apply;

import entity.University;
import entity.UniversityFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
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
        // it uses the sat and act data to choose a university by using the api
        String actScore = applyInputData.getActScore();
        actChecker(actScore);
        String satScore = applyInputData.getSatScore();
        satChecker(satScore);
        int intsatScore = Integer.parseInt(satScore);
        int intactScore = Integer.parseInt(actScore);
        String queryParameters1 = "2018.admissions.sat_scores.average.overall__range=" + Integer.toString(intsatScore - 50) + "..." + Integer.toString(intsatScore + 50);
        String queryParameters3 = "2018.admissions.sat_scores.average.overall=" + Integer.toString(intsatScore);

        String optionalParameters = "fields=id,school.name,school.state,school.city,2018.admissions.admission_rate.overall,2018.cost.tuition.in_state,2018.cost.tuition.out_of_state,2018.admissions.sat_scores.average.overall,2018.admissions.act_scores.midpoint.cumulative,school.school_url&per_page=200";
        JSONObject query1 = applyDataAccessObject.basicQuery(queryParameters1, optionalParameters);
        String queryParameters2 = "2018.admissions.act_scores.midpoint.cumulative__range=" + Integer.toString(intactScore - 2) + "..." + Integer.toString(intactScore + 2);
        JSONObject query2 = applyDataAccessObject.basicQuery(queryParameters2, optionalParameters);
        JSONObject metadata1 = query1.getJSONObject("metadata");
        JSONObject metadata2 = query2.getJSONObject("metadata");

        boolean metadata1Fail = false;
        boolean metadata2Fail = false;
        if (metadata1.getInt("total") == 0) {
            metadata1Fail = true;
        }
        if (metadata2.getInt("total") == 0) {
            metadata2Fail = true;
        }
        if (metadata1Fail && metadata2Fail) {
            applyPresenter.prepareFailView("Error: No universities found");
            return;
        } else if (metadata2Fail) {
            University uni1 = executeHelper(query1.getJSONArray("results"));
            ApplyOutputData applyOutputData = new ApplyOutputData(uni1);
            applyPresenter.prepareSuccessView(applyOutputData);
            return;
        } else if (metadata1Fail) {
            University uni2 = executeHelper(query2.getJSONArray("results"));
            ApplyOutputData applyOutputData = new ApplyOutputData(uni2);
            applyPresenter.prepareSuccessView(applyOutputData);
            return;
        }
        University uni1 = executeHelper(query1.getJSONArray("results"));
        University uni2 = executeHelper(query2.getJSONArray("results"));

        University chosenUni = null;
        if (uni1.getAverageSATScore() == null && uni2.getAverageSATScore() == null) {
            applyPresenter.prepareFailView("Error");
        } else if (uni2.getAverageSATScore() == null) {
            chosenUni = uni1;
        } else if (uni1.getAverageSATScore() == null) {
            chosenUni = uni2;
        } else {
            if (uni1.getAverageSATScore() >= uni2.getAverageSATScore()) {
                chosenUni = uni1;
            } else {
                chosenUni = uni2;
            }
        }

        ApplyOutputData applyOutputData = new ApplyOutputData(chosenUni);
        applyPresenter.prepareSuccessView(applyOutputData);
    }

    @Override
    public void executeBack(){
        applyPresenter.prepareBackView();
    }

    private University executeHelper(JSONArray results) {
        //this for loop finds the uni with the biggest sat score
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
        University newUniversity = universityFactory.create(id, name, state, city, admRate, inTuit, outTuit, avgSAT, avgACT, url);
        return newUniversity;}

    private void satChecker(String satScore) {
        if (satScore == "") {
            throw new IllegalArgumentException("sat score cant be empty");
        }
        if (Integer.parseInt(satScore) >= 1600 || Integer.parseInt(satScore) < 0) {
            throw new IllegalArgumentException("Sat Score has to be between 0-1600");
        }
    }

    private void actChecker(String actScore) {
        if (actScore == "") {
            throw new IllegalArgumentException("act score cant be empty");
        }
        if ( Integer.parseInt(actScore) >= 36 || Integer.parseInt(actScore) < 0 ) {
            throw new IllegalArgumentException("Act score should be between 0- 36");
        }
    }
    private Double doubleChecker(Object object) {
        String checker = object.toString();
        if (checker.equals("null")) {
            return null;
        }
        BigDecimal converter = (BigDecimal) object;
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
