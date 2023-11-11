package entity;

import java.time.LocalDateTime;

class InitialCommonUser implements User {

    private final String name;
    private final String password;
    private final LocalDateTime creationTime;
    private String token;

    /**
     * Requires: password is valid.
     * @param name
     * @param password
     */
    InitialCommonUser(String name, String password, LocalDateTime creationTime) {
        this.name = name;
        this.password = password;
        this.creationTime = creationTime;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public LocalDateTime getCreationTime() {
        return creationTime;
    }
}
