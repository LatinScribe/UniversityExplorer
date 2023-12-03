// Author: André: FOR TESTING PURPOSES ONLY
package data_access;

import entity.CreationUser;
import entity.ExistingCommonUserFactory;
import entity.ExistingUser;
import org.json.JSONObject;
import use_case.apply.ApplyDataAccessInterface;

public class MemoryApplyDataAccessObject implements ApplyDataAccessInterface {

    public JSONObject universities;

    public MemoryApplyDataAccessObject(JSONObject universities) {
        this.universities = universities;
    }

//    public boolean existsByName(String identifier) {
//        return users.containsKey(identifier);
//    }

    @Override
    public JSONObject basicQuery(String queryParameters, String optionalParameters) {
        return universities;
    }
}
