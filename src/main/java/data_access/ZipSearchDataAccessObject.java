// Author: Diego
package data_access;

import api.JsonCollegeScorecardDB;
import org.json.JSONObject;
import use_case.zip_search.ZipSearchUserDataAccessInterface;

public class ZipSearchDataAccessObject implements ZipSearchUserDataAccessInterface {
    private final JsonCollegeScorecardDB database = new JsonCollegeScorecardDB();

    @Override
    public JSONObject searchQuery(String zipCodeParameter, String radiusParameter) {
        return database.zipcodeQuery(zipCodeParameter, radiusParameter);
    }
}
