package entity;

import java.time.LocalDateTime;

public interface User {

    String getName();

    String getPassword();

    String getToken();

    LocalDateTime getCreationTime();

    void setToken(String token);
}
