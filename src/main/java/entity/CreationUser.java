package entity;

import java.time.LocalDateTime;

public interface CreationUser extends User{
    String getName();

    String getPassword();

    LocalDateTime getCreationTime();
}
