// Author: André

package use_case.search;

import interface_adapter.search.SearchPresenter;
import org.json.JSONArray;
import org.json.JSONObject;
import entity.University;
import entity.UniversityFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        JSONObject query = searchDataAccessObject.searchQuery("school.name=" + stringAccumulator);
        JSONObject metadata = query.getJSONObject("metadata");
        if (metadata.getInt("total") == 0) {
            searchPresenter.prepareResultsNotFoundView("Results not found!");
        }
        else {
            List<University> universityList = executeHelper(query.getJSONArray("results"));
            SearchOutputData searchOutputData = new SearchOutputData(universityList);
            searchPresenter.prepareSuccessView(searchOutputData);
        }
    }

    private List<University> executeHelper(JSONArray results) {
        List<University> universities = new ArrayList<University>();
        for (int i = 0; i < results.length(); i++) {
            JSONObject university = (JSONObject) results.get(i);
            Integer ID = university.getInt("id");
            String name = university.getString("school.name");
            String state = university.getString("school.state");
            String city = university.getString("school.city");
            Object admRate = university.get("admissions.admission_rate.overall");
            // Need to change object to float and accept null values
            if (admRate == "null") {
                Double admRateDouble = null;
            }
            Float admRateConvert = (Float) admRate;
            Double admRateDouble = admRateConvert.doubleValue();
            Integer outTuit = university.getInt("cost.tuition.out_of_state");
            Integer inTuit = university.getInt("cost.tuition.in_state");
            Float avgSAT = university.getFloat("admissions.sat_scores.average.overall");
            Double avgSATDouble = avgSAT.doubleValue();
            Float avgACT = university.getFloat("admissions.act_scores.midpoint.cumulative");
            Double avgACTDouble = avgACT.doubleValue();
            String url = university.getString("school.school_url");

            University newUniversity = universityFactory.create(ID, name, state, city, admRateDouble, outTuit, inTuit, avgSATDouble, avgACTDouble, url);
            universities.add(newUniversity);
        }
        return universities;
    }
}