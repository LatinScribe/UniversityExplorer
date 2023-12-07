package data_access;

import api.JsonCollegeScorecardDB;
import org.json.JSONObject;
import use_case.search.SearchUserDataAccessInterface;

/**
 * A data access objected used by the search use case to query the JsonCollegeScorecardDB.
 */
public class SearchDataAccessObject implements SearchUserDataAccessInterface {

    private final JsonCollegeScorecardDB database = new JsonCollegeScorecardDB();

    /**
     * Returns a JSONObject which includes a JSONArray of elements containing a university's statistics.
     * @param searchparameters
     * @return JSONObject containing a JSONArray of elements containing the statistics of multiple university's.
     */
    @Override
    public JSONObject searchQuery(String searchparameters) {
        return database.basicQuery(searchparameters, "fields=id,school.name,school.state,school.city,2018.admissions.admission_rate.overall,2018.cost.tuition.in_state,2018.cost.tuition.out_of_state,2018.admissions.sat_scores.average.overall,2018.admissions.act_scores.midpoint.cumulative,school.school_url&per_page=100");
    }
}
