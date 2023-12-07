package use_case.search;

import org.json.JSONObject;

public interface SearchUserDataAccessInterface {
    JSONObject searchQuery(String searchParameters);
}
