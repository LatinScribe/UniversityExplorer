package entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class CreationCommonUserTest {
    LocalDateTime time = LocalDateTime.now();
    CreationCommonUser user = new CreationCommonUser("BillyBob", "GoldFish", time);

    @Test
    void getName() {
        assert user.getName().equals("BillyBob");
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