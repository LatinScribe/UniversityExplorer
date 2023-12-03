package entity;

import java.time.LocalDateTime;

/**
 * This is the data entity for users upon INITIAL creation. It should NOT be used
 * outside the signup use case.
 * <p>
 * Do not have an ID and token unlike existing users
 *
 * @author Henry
 */
class CreationCommonUser implements CreationUser {

    private final String name;
    private final String password;
    private final LocalDateTime creationTime;

    /**
     * Requires: password is valid.
     *
     * @param name
     * @param password
     */
    CreationCommonUser(String name, String password, LocalDateTime creationTime) {
        this.name = name;
        this.password = password;
        this.creationTime = creationTime;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }
}
