// Author: Henry
package use_case.login;

import entity.CreationUser;
import entity.ExistingUser;

public interface LoginUserDataAccessInterface {
    boolean existsByName(String identifier);

    String save(CreationUser user);

    ExistingUser get(String username, String password);
}
