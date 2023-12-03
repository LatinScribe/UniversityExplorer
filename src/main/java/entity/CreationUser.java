package entity;

import java.time.LocalDateTime;

/**
 * This is the interface for the data entity for users upon INITIAL creation. It should NOT be used
 * outside the signup use case.
 * <p>
 * Do not have an ID and token unlike existing users
 *
 * @author Henry
 */
public interface CreationUser extends User {
    String getName();

    String getPassword();

    LocalDateTime getCreationTime();
}
