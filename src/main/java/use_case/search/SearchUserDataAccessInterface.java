// Author: André

package use_case.search;

import org.json.JSONObject;

public interface SearchUserDataAccessInterface {
    JSONObject basicQuery(String queryParameters, String optionalParameters);
}
