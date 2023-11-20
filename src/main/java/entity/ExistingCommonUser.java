package entity;

import java.time.LocalDateTime;

public class ExistingCommonUser implements ExistingUser{

    private final String name;
    private final String id;

    private final String password;
    private final LocalDateTime creationTime;
    private String token;

    /**
     * Requires: should match entries in server database
     *
     * @param name
     * @param id
     * @param password
     */
    ExistingCommonUser(String name, String id, String password, LocalDateTime creationTime, String token) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.creationTime = creationTime;
        this.token = token;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getID() {
        return id;
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
    public String getPassword() {
        return password;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }
}
