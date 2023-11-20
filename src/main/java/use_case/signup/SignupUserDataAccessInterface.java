// Author: Henry
package use_case.signup;

import entity.CreationUser;
import entity.ExistingUser;

public interface SignupUserDataAccessInterface {
    boolean existsByName(String identifier);

    ExistingUser save(CreationUser user);
}
