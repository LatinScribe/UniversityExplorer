package use_case.zip_search;

import entity.University;
import entity.UniversityFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.search.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * A class which provides the main execution of the actions inputted by the user. This involves calling on data
 * access objects to query the JsonCollegeScorecardDB and creation of University entities to be displayed back
 * to the user later on in the ResultsView.
 * @author Diego
 */
public class ZipSearchInteractor implements ZipSearchInputBoundary {
    final ZipSearchUserDataAccessInterface zipSearchDataAccessObject;
    final ZipSearchOutputBoundary zipSearchPresenter;
    final UniversityFactory universityFactory;

    /**
     * The constructor of the ZipSearchInteractor class
     * @param zipSearchUserDataAccessInterface
     * @param zipSearchPresenter
     * @param universityFactory
     */
    public ZipSearchInteractor(ZipSearchUserDataAccessInterface zipSearchUserDataAccessInterface,
            ZipSearchOutputBoundary zipSearchPresenter, UniversityFactory universityFactory) {
        this.zipSearchDataAccessObject = zipSearchUserDataAccessInterface;
        this.zipSearchPresenter = zipSearchPresenter;
        this.universityFactory = universityFactory;
    }

    /**
     * Provides the ZipSearchOutputData in the form of a list of Universities to send to the
     * ZipSearchPresenter. The list of Universities is done based on whether the institution's
     * zipcode matches, and it is part of the radius distance provided by the ZipSearchInputData.
     * @param zipSearchInputData
     */
    @Override
    public void executeSearch(ZipSearchInputData zipSearchInputData) {
        String stringAccumulator = "";
        String stringAccumulator2 = "";
        String zipCodeInput = zipSearchInputData.getZipSearch();
        String radInput = zipSearchInputData.getRadSearch();
        for (int i = 0; i < zipCodeInput.length(); i++) {
            String cursor = zipCodeInput.substring(i, i + 1);
            if (cursor.equals(" ")) {
                stringAccumulator += "%20";
            } else {
                stringAccumulator += cursor;
            }
        }
        for (int i = 0; i < radInput.length(); i++) {
            String cursor2 = radInput.substring(i, i + 1);
            if (cursor2.equals(" ")) {
                stringAccumulator2 += "%20";
            } else {
                stringAccumulator2 += cursor2;
            }
        }
        try {
            JSONObject query = zipSearchDataAccessObject.searchQuery(stringAccumulator, stringAccumulator2);
            JSONObject metadata = query.getJSONObject("metadata");
            if (metadata.getInt("total") == 0) {
                zipSearchPresenter.prepareResultsNotFoundView("Results not found!");
            } else {
                List<University> universityList = executeHelper(query.getJSONArray("results"));
                ZipSearchOutputData zipSearchOutputData = new ZipSearchOutputData(universityList);
                zipSearchPresenter.prepareSuccessView(zipSearchOutputData);
            }
        } catch (JSONException e) {
                zipSearchPresenter.prepareResultsNotFoundView("JSON Error! (" + e + ")");
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

    /**
     * A helper function that helps to determine the type of the object returned from a specific query result. If this
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
     * A helper function that helps to determine the type of the object returned from a specific query result. If this
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
     * A helper function that helps to determine the type of the object returned from a specific query result. If this
     * object is not null, return the value of the String passed in.
     * @param object
     * @return String
     */
    private String stringChecker(Object object) {
        String checker = object.toString();
        if (checker.equals("null")) {
        //    return null;
        }
        return (String) object;
    }
    /**
     * A use case interactor of the ZipSearch use case. Calls the presenter to switch the active view to the previous view.
     */
    public void executeBack(){
        zipSearchPresenter.prepareBackView();
    }
}