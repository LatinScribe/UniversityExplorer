// Author: Henry
package use_case.signup;

import entity.CreationUser;

public interface SignupUserDataAccessInterface {
    boolean existsByName(String identifier);

    String save(CreationUser user);
}
