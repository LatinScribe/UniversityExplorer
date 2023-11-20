package data_access;

import api.JsonCollegeScorecardDB;
import org.json.JSONObject;
import use_case.apply.ApplyDataAccessInterface;

public class ApplyDataAccessObject implements ApplyDataAccessInterface {
    @Override
    public JSONObject basicQuery(String queryParameters, String optionalParameters) {
        JsonCollegeScorecardDB jsonCollegeScorecardDB = new JsonCollegeScorecardDB();
        return jsonCollegeScorecardDB.basicQuery(queryParameters, optionalParameters);

    }
}
