// Author: André

package use_case.search;

import api.JsonCollegeScorecardDB;
import org.json.JSONArray;
import org.json.JSONObject;
import entity.University;
import entity.UniversityFactory;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class SearchInteractor implements SearchInputBoundary {
    final SearchUserDataAccessInterface searchDataAccessObject;
    final SearchOutputBoundary searchPresenter;
    final UniversityFactory universityFactory;

    public SearchInteractor(SearchUserDataAccessInterface searchUserDataAccessInterface,
                            SearchOutputBoundary signupOutputBoundary, UniversityFactory universityFactory) {
        this.searchDataAccessObject = searchUserDataAccessInterface;
        this.searchPresenter = signupOutputBoundary;
        this.universityFactory = universityFactory;
    }

    @Override
    public void execute(SearchInputData searchInputData) {
        String stringAccumulator = "";
        String input = searchInputData.getSearchCriteria();
        for (int i = 0; i < input.length(); i++) {
            String cursor = input.substring(i, i+1);
            if (cursor.equals(" ")) {
                stringAccumulator += "%20";
            }
            else {
                stringAccumulator += cursor;
            }
        }
        JSONObject outputData = searchDataAccessObject.basicQuery("school.name=" + stringAccumulator, "fields=school.name,school.state,school.city,admissions.admission_rate.overall,cost.tuition.in_state,cost.tuition.out_of_state");
        Integer total = outputData.getJSONObject("metadata").getInt("total");
        if (total.equals(0)) {
            searchPresenter.prepareResultsNotFoundView("No data found!");
        }
        else {
            JSONArray results = outputData.getJSONArray("results");
            List<University> universities = executeHelper(results);
            searchPresenter.prepareSuccessView((SearchOutputData) universities);
        }
    }

    private List<University> executeHelper(JSONArray results) {
        List<University> universities = new ArrayList<University>();
        for (int i = 0; i < results.length(); i ++) {
            JSONObject university = (JSONObject) results.get(i);
            String name = university.getString("school.name");
            String state = university.getString("school.state");
            String city = university.getString("school.city");
            Double admRate = university.getDouble("admissions.admission_rate.overall");
            Integer outTuit = university.getInt("cost.tuition.out_of_state");
            Integer inTuit = university.getInt("cost.tuition.in_state");
            University newUniversity = universityFactory.create(name, state, city, admRate, outTuit, inTuit);
            universities.add(newUniversity);
        }
        return universities;
    }
}