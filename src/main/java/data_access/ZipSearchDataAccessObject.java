package data_access;

import api.JsonCollegeScorecardDB;
import org.json.JSONObject;
import use_case.zip_search.ZipSearchUserDataAccessInterface;

/**
 * A DataAccessObject class that uses the API to query universities
 * @author Diego
 */
public class ZipSearchDataAccessObject implements ZipSearchUserDataAccessInterface {
    private final JsonCollegeScorecardDB database = new JsonCollegeScorecardDB();

    /**
     * Given the zipcode and radius parameters, the method queries for universities
     * @param zipCodeParameter
     * @param radiusParameter
     * @return
     */
    @Override
    public JSONObject searchQuery(String zipCodeParameter, String radiusParameter) {
        return database.zipcodeQuery(zipCodeParameter, radiusParameter);
    }
}
