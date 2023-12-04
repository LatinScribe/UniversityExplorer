package use_case.prefapply;

import data_access.ProfileDataAccessInterface;
import entity.University;
import entity.UniversityFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class PrefApplyInteractor implements PrefApplyInputBoundary{
    final PrefApplyDataAccessInterface prefapplyDataAccessObject;
    final PrefApplyOutputBoundary prefapplyPresenter;
    final UniversityFactory universityFactory;
    final ProfileDataAccessInterface profileDataAccessInterface;

    public PrefApplyInteractor(PrefApplyDataAccessInterface prefapplyDataAccessObject, PrefApplyOutputBoundary prefapplyOutputBoundary, UniversityFactory universityFactory, ProfileDataAccessInterface profileDataAccessInterface) {
        this.prefapplyDataAccessObject = prefapplyDataAccessObject;
        this.prefapplyPresenter = prefapplyOutputBoundary;
        this.universityFactory = universityFactory;
        this.profileDataAccessInterface = profileDataAccessInterface;

    }

    @Override
    public void execute(PrefApplyInputData prefapplyInputData) {
        String actScore = prefapplyInputData.getActScore();
        String satScore = prefapplyInputData.getSatScore();
        int intsatScore = Integer.parseInt(satScore);
        int intactScore = Integer.parseInt(actScore);
        try {
            Integer avgSal = profileDataAccessInterface.getProfile().getAvgSalary();
            //String loc = profileDataAccessInterface.getProfile().getLocationPreference();
            //Integer avgSal = 80000;
            //https://api.data.gov/ed/collegescorecard/v1/schools.json?earnings.10_yrs_after_entry.median__range=80000..1000000&fields=id,school.name,2013.earnings.10_yrs_after_entry.median,2013.admissions.sat_scores.average.overall&per_page=100&sort=admissions.admission_rate.overall&api_key=AwypPp5bIHx6H4EevsB5sFs4gw5NK1M7SNc4eXwl

            String queryParameters1 = "earnings.10_yrs_after_entry.median__range="+Integer.toString(avgSal)+"...1000000";
            String optionalParameters = "fields=school.name,2013.admissions.sat_scores.average.overall,2013.earnings.10_yrs_after_entry.median&per_page=100&sort=admissions.admission_rate.overall";
            JSONObject results = prefapplyDataAccessObject.basicQuery(queryParameters1,optionalParameters);
            University chosenUni = executeHelper(results.getJSONArray("results"),intsatScore);
            JSONObject metadata1 = results.getJSONObject("metadata");


            if (metadata1.getInt("total") == 0)  {
                prefapplyPresenter.prepareFailView("Error");
            }
            else {
                PrefApplyOutputData prefapplyOutputData = new PrefApplyOutputData(chosenUni);
                prefapplyPresenter.prepareSuccessView(prefapplyOutputData);
            }



        } catch (IOException e){
            prefapplyPresenter.prepareFailView("User doesnt have avg salary or we cant access it");
        }










    }

    @Override
    public void executeBack(){
        prefapplyPresenter.prepareBackView();
    }

    private University executeHelper(JSONArray results,int satscore) {
        int smallestdiffernceindex = 0;
        Integer smallestdifference = 400;
        for (int i = 0; i < results.length(); i++) {
            // After every object is extracted, temporarily save its value as an object and check if it's null before implementing.
            JSONObject university = (JSONObject) results.get(i);
            Object avgSATCheck = university.get("2013.admissions.sat_scores.average.overall");
            Integer avgSAT = integerChecker(avgSATCheck) ;
            if (avgSAT != null){
                if (Math.abs(avgSAT - satscore) < smallestdifference){ smallestdifference = Math.abs(avgSAT - satscore);
                    smallestdiffernceindex = i;}
            }
        }
        JSONObject university = (JSONObject) results.get(smallestdiffernceindex);
//        Object idCheck = university.get("id");
//        Integer id = integerChecker(idCheck);
        Object nameCheck = university.get("school.name");
        String name = stringChecker(nameCheck);
//        Object stateCheck = university.get("school.state");
//        String state = stringChecker(stateCheck);
//        Object cityCheck = university.get("school.city");
//        String city = stringChecker(cityCheck);
//        Object admRateCheck = university.get("admissions.admission_rate.overall");
//        Double admRate = doubleChecker(admRateCheck);
//        Object outTuitCheck = university.get("cost.tuition.out_of_state");
//        Integer outTuit = integerChecker(outTuitCheck);
//        Object inTuitCheck = university.get("cost.tuition.in_state");
//        Integer inTuit = integerChecker(inTuitCheck);
        Object avgSATCheck = university.get("2013.admissions.sat_scores.average.overall");
        Integer avgSAT = integerChecker(avgSATCheck) ;
//        Object avgACTCheck = university.get("admissions.act_scores.midpoint.cumulative");
//        Double avgACT = doubleChecker(avgACTCheck);
//        Object urlCheck = university.get("school.school_url");
//        String url = stringChecker(urlCheck);
        University newUniversity = universityFactory.create(null, name, null, null, null, null, null, avgSAT, null, null);
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