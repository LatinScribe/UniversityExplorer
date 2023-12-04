package interface_adapter.signup;

import org.junit.jupiter.api.Test;

class SignupStateTest {
    SignupState signupState = new SignupState();

    @Test
    void testToString() {
        assert signupState.toString().equals("SignupState{username='', password='', repeatPassword=''}");
    }
    @Test
    void testCopy() {
        assert signupState.toString().equals("SignupState{username='', password='', repeatPassword=''}");
        SignupState signupState1 = new SignupState(signupState);
        assert signupState1.toString().equals("SignupState{username='', password='', repeatPassword=''}");
    }
}