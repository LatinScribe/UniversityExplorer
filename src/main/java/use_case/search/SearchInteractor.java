package use_case.search;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import entity.University;
import entity.UniversityFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * A class which provides the main execution of the actions inputted by the user. This involves calling on data
 * access objects to query the JsonCollegeScorecardDB and creation of University entities to be displayed back
 * to the user later on in the ResultsView, as well as alerting the SearchPresenter to return to the previous view.
 * @author Andre
 */
public class SearchInteractor implements SearchInputBoundary {
    final SearchUserDataAccessInterface searchDataAccessObject;
    final SearchOutputBoundary searchPresenter;
    final UniversityFactory universityFactory;

    /**
     * The contructor for the SearchInteractor.
     * @param searchUserDataAccessInterface
     * @param searchPresenter
     * @param universityFactory
     */
    public SearchInteractor(SearchUserDataAccessInterface searchUserDataAccessInterface,
                            SearchOutputBoundary searchPresenter, UniversityFactory universityFactory) {
        this.searchDataAccessObject = searchUserDataAccessInterface;
        this.searchPresenter = searchPresenter;
        this.universityFactory = universityFactory;
    }

    /**
     * A msthod of the SearchInteractor, which provides SearchOutputData in the form of a list of
     * Universities to send to the SearchPresenter. The list of Universities is done based on
     * whether the institution's name matches part of the String provided by the searchInputData.
     * @param searchInputData (containing a string used for the database query)
     */
    @Override
    public void executeSearch(SearchInputData searchInputData) {
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
        try {
            JSONObject query = searchDataAccessObject.searchQuery("school.name=" + stringAccumulator);
            JSONObject metadata = query.getJSONObject("metadata");
            if (metadata.getInt("total") == 0) {
                searchPresenter.prepareResultsNotFoundView("Results not found!");
            } else {
                List<University> universityList = executeHelper(query.getJSONArray("results"));
                SearchOutputData searchOutputData = new SearchOutputData(universityList);
                searchPresenter.prepareSuccessView(searchOutputData);
            }
        } catch (JSONException e) {
            searchPresenter.prepareResultsNotFoundView("JSON Error! (" + e + ")");
            }
    }

    /**
     * Takes in a JsonArray and reformats it to a list of University objects.
     * @param results
     * @return List<University>
     */
    private List<University> executeHelper(JSONArray results) {
        List<University> universities = new ArrayList<University>();
        for (int i = 0; i < results.length(); i++) {
            // After every object is extracted, temporarily save its value as an object and check if it's null before implementing.
            JSONObject university = (JSONObject) results.get(i);
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
            universities.add(newUniversity);
        }
        return universities;

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
     * A method of the Search use case. Calls the presenter to switch the active view back to the previous view.
     */
    public void executeBack(){
        searchPresenter.prepareBackView();
    }


}