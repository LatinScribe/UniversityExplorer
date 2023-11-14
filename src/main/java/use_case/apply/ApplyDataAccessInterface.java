package use_case.apply;

import org.json.JSONObject;

public interface ApplyDataAccessInterface {
    JSONObject basicQuery(String queryParameters, String optionalParameters);
}
