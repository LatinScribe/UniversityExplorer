package use_case.signup;

import entity.User;

public interface SignupUserDataAccessInterface {
    boolean existsByName(String identifier);

    String save(User user);
}
