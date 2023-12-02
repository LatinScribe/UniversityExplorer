package api;

import org.json.JSONObject;

// Author: Henry

public interface CollegeScorecardDB {
    // this version is for full customisation
    JSONObject basicQuery(String endpoint, String fileFormat, String queryParameters, String optionalParameters);

    // overloaded version where we default some values
    JSONObject basicQuery(String queryParameters, String optionalParameters);

    // returns the names of the institutions within a radius miles (ex 50mi)
    JSONObject zipcodeQuery(String zipcode, String radius);

}
