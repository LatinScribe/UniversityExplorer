package entity;

import java.time.LocalDateTime;

/**
 * This is the interface for the data entity for EXISTING users (they have an actual id and token). It should NOT be used
 * for initial user creation.
 *
 * @author Henry
 */
public interface ExistingUser extends User {
    String getName();

    int getID();

    void setToken(String token);

    String getToken();

    String getPassword();

    LocalDateTime getCreationTime();
}
