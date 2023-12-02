package entity;

import java.time.LocalDateTime;

// This is the data entity for users upon INITIAL creation. It should NOT be used
// outside of the signup use case

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
