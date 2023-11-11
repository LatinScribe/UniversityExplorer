package use_case.login;

import entity.User;

public interface LoginUserDataAccessInterface {
    boolean existsByName(String identifier);

    String save(User user);

    User get(String username, String password);
}
