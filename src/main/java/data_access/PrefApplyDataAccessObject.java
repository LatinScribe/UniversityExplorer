package data_access;

import api.JsonCollegeScorecardDB;
import entity.UserProfile;
import entity.UserProfileFactory;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.prefapply.PrefApplyDataAccessInterface;

import java.io.IOException;

public class PrefApplyDataAccessObject implements PrefApplyDataAccessInterface {

    @Override
    public JSONObject basicQuery(String queryParameters, String optionalParameters) {
        JsonCollegeScorecardDB jsonCollegeScorecardDB = new JsonCollegeScorecardDB();
        return jsonCollegeScorecardDB.basicQuery(queryParameters, optionalParameters);

    }
}
