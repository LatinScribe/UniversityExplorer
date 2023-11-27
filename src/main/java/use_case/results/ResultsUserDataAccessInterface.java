package use_case.results;

import org.json.JSONObject;

public interface ResultsUserDataAccessInterface {

    JSONObject searchQuery(String searchParameters);
}
