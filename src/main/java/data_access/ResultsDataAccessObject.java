package data_access;

import api.JsonCollegeScorecardDB;
import org.json.JSONObject;
import use_case.results.ResultsUserDataAccessInterface;

public class ResultsDataAccessObject implements ResultsUserDataAccessInterface {

    private final JsonCollegeScorecardDB database = new JsonCollegeScorecardDB();

    @Override
    public JSONObject searchQuery(String searchparameters) {
        return database.basicQuery(searchparameters, "fields=id,school.name,school.state,school.city,2018.admissions.admission_rate.overall,2018.cost.tuition.in_state,2018.cost.tuition.out_of_state,2018.admissions.sat_scores.average.overall,2018.admissions.act_scores.midpoint.cumulative,school.school_url");
    }
}
