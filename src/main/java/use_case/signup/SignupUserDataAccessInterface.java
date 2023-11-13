package use_case.signup;

import entity.CreationUser;
import entity.User;

public interface SignupUserDataAccessInterface {
    boolean existsByName(String identifier);

    String save(CreationUser user);
}
