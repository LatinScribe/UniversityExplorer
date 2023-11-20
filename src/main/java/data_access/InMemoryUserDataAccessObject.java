// Author: Common (mostly Managed by Henry)
// Note: USE THIS FOR TESTING ONLY
package data_access;

import entity.*;
import use_case.signup.SignupUserDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface {

    private final Map<String, User> users = new HashMap<>();

    /**
     * @param identifier the user's username
     * @return whether the user exists
     */
    @Override
    public boolean existsByName(String identifier) {
        return users.containsKey(identifier);
    }

    /**
     * @param user the data to save
     */
    @Override
    public ExistingUser save(CreationUser user) {
        users.put(user.getName(), user);
        ExistingCommonUserFactory fact = new ExistingCommonUserFactory();
        return fact.create(user.getName(), 0, "fake", user.getCreationTime(), "fake");
    }
}
