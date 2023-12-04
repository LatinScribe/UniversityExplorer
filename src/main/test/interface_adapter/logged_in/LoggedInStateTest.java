package interface_adapter.logged_in;

import interface_adapter.login.LoginState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoggedInStateTest {
    LoggedInState loggedInState = new LoggedInState();

    @Test
    void testCopy() {
        LoggedInState loggedInState1 = new LoggedInState(loggedInState);
        assert loggedInState1.getUsername().isEmpty();
    }

}