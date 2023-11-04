package api;
import org.json.JSONException;
import org.json.JSONObject;

public interface CollegeScorecardDB {
    // this version is for full customisation
    JSONObject basicQuery(String endpoint, String fileFormat, String queryParameters, String optionalParameters);
    // overloaded version where we default some values
    JSONObject basicQuery(String queryParameters, String optionalParameters);

}
