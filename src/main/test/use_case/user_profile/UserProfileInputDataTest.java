package use_case.user_profile;

import org.junit.Before;
import org.junit.Test;

public class UserProfileInputDataTest {

    UserProfileInputData inputData = new UserProfileInputData("atesterman", "Testerman", "Canadian", "Toronto", "U of T");

    @Test
    public void testGetUser() {
        assert (inputData.getUsername().equals("atesterman"));
    }

    @Test
    public void testGetName() {
        assert (inputData.getName().equals("Testerman"));
    }

    @Test
    public void testGetNationality() {
        assert (inputData.getNationality().equals("Canadian"));
    }

    @Test
    public void testGetLocation() {
        assert (inputData.getLocation().equals("Toronto"));
    }

    @Test
    public void testGetPreferredUni() {
        assert (inputData.getPreferredUni().equals("U of T"));
    }
}
