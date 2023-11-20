package use_case.login;

import entity.CreationUser;
import entity.ExistingUser;
import entity.User;

public interface LoginUserDataAccessInterface {
    boolean existsByName(String identifier);

    ExistingUser save(CreationUser user);

    ExistingUser get(String username, String password);
}
