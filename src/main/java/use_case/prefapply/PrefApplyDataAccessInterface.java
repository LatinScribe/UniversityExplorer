package use_case.prefapply;

import org.json.JSONObject;

public interface PrefApplyDataAccessInterface {
    JSONObject basicQuery(String queryParameters, String optionalParameters);
}
