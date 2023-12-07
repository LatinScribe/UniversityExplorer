package interface_adapter.login;

import org.junit.jupiter.api.Test;

class LoginStateTest {
    LoginState loginState = new LoginState();

    @Test
    void getPasswordError() {
        loginState.setPasswordError("Error");
        assert loginState.getPasswordError().equals("Error");
    }

    @Test
    void setPasswordError() {
        loginState.setPasswordError("Error");
        assert loginState.getPasswordError().equals("Error");
    }

    @Test
    void testCopy() {
        loginState.setPasswordError("Error");
        assert loginState.getPasswordError().equals("Error");
        LoginState loginState1 = new LoginState(loginState);
        assert loginState1.getPasswordError().equals("Error");
    }
}