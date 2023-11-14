package entity;

import java.time.LocalDateTime;

public interface CreationUserFactory {
    CreationUser create(String name, String password, LocalDateTime ltd);
}
