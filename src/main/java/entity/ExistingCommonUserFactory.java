package entity;

import java.time.LocalDateTime;

public class ExistingCommonUserFactory implements ExistingUserFactory{
    @Override
    public ExistingUser create(String name, String id, String password, LocalDateTime ltd, String token) {
        return new ExistingCommonUser(name, id, password, ltd, token);
    }
}
