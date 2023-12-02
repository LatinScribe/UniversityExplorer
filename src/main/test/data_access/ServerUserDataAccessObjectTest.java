package data_access;

import entity.CreationCommonUserFactory;
import entity.CreationUser;
import entity.ExistingCommonUserFactory;
import entity.ExistingUser;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.fail;

class   ServerUserDataAccessObjectTest {
    ServerUserDataAccessObject db = new ServerUserDataAccessObject(new ExistingCommonUserFactory());
    CreationCommonUserFactory fact = new CreationCommonUserFactory();
    LocalDateTime time = LocalDateTime.now();

    @Test
    void get() {
        int randomNum = ThreadLocalRandom.current().nextInt(100, 123344 + 1);
        ExistingUser myuser = db.get("johny1234", "12342324");
        System.out.println("We expect true");
        System.out.println(myuser.getToken().equals("1kRrWzHPUZnSFlFevLuMBoQi2lFeXP8z"));
        assert (myuser.getToken().equals("1kRrWzHPUZnSFlFevLuMBoQi2lFeXP8z"));
        System.out.println("We expect true");
        System.out.println(myuser.getID() == 8);
        assert (myuser.getID() == 8);

        try {
            ExistingUser myuser2 = db.get("johny" + randomNum, "12342324");
        } catch (Exception e) {
            assert (e.getMessage().equals("PASSWORD OR USERNAME INCORRECT"));
        }

        try {
            ExistingUser myuser2 = db.get("abcd" + randomNum, "12342324");
        } catch (Exception e) {
            assert (e.getMessage().equals("PASSWORD OR USERNAME INCORRECT"));
        }
    }

    @Test
    void existsByName() {
        boolean result = db.existsByName("johny");
        System.out.println("This should return true");
        System.out.println(result);
        assert (result);

        boolean result2 = db.existsByName("somerandomuser");
        System.out.println("This should return false");
        System.out.println(result2);
        assert (!result2);
    }

    @Test
    void save() {
        int randomNum = ThreadLocalRandom.current().nextInt(100, 123344 + 1);
        try {
            CreationUser user = fact.create("abcd", "1234", time);
            ExistingUser user4 = db.save(user);
            fail("We should get a username already exists error");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            System.out.println("This should be because user already exists");
            assert (e.getMessage().equals("USERNAME ALREADY EXISTS"));
        }

        try {
            CreationUser user = fact.create("Test" + randomNum, "1234654", time);
            ExistingUser user4 = db.save(user);
            assert (!user4.getToken().isEmpty());
        } catch (RuntimeException e) {
            fail("We shouldn't get a user already exists error. Try running the test again, maybe unlucky?");
        }
    }
}