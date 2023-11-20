package entity;

import java.time.LocalDateTime;

public interface ExistingUserFactory {
    ExistingUser create(String name, String id, String password, LocalDateTime ltd, String token);
}
