package entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ExistingCommonUserTest {
    LocalDateTime time = LocalDateTime.now();
    ExistingCommonUser user = new ExistingCommonUser("BillyBob", 20,"GoldFish", time, "TOKEN");
    @Test
    void setToken() {
        user.setToken("NEWTOKEN");
        assert user.getToken().equals("NEWTOKEN");
    }

    @Test
    void getPassword() {
        assert user.getPassword().equals("GoldFish");
    }

    @Test
    void getCreationTime() {
        assert user.getCreationTime().equals(time);
    }
}