package entity;

import java.time.LocalDateTime;

public interface ExistingUser extends User{
    String getName();

    String getID();
    void setToken(String token);

    String getToken();

    String getPassword();

    LocalDateTime getCreationTime();
}
