// Author: André

package data_access;

import api.JsonCollegeScorecardDB;
import org.json.JSONObject;
import use_case.search.SearchUserDataAccessInterface;

public class SearchDataAccessObject implements SearchUserDataAccessInterface {

    private final JsonCollegeScorecardDB database = new JsonCollegeScorecardDB();

    @Override
    public JSONObject searchQuery(String searchparameters) {
        return database.basicQuery(searchparameters, "fields=id,school.name,school.state,school.city,admissions.admission_rate.overall,cost.tuition.in_state,cost.tuition.out_of_state,admissions.sat_scores.average.overall,admissions.act_scores.midpoint.cumulative,school.school_url&per_page=100");
    }
}
