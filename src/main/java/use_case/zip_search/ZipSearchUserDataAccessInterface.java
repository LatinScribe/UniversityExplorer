// Author: Diego
package use_case.zip_search;

import org.json.JSONObject;

public interface ZipSearchUserDataAccessInterface {
    JSONObject searchQuery(String zipCodeParameter, String radiusParameter);
}
