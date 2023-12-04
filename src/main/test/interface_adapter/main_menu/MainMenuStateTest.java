package interface_adapter.main_menu;

import org.junit.jupiter.api.Test;

class MainMenuStateTest {
    MainMenuState mainMenuState = new MainMenuState();

    @Test
    void getUsername() {
        mainMenuState.setUsername("Tester");
        assert mainMenuState.getUsername().equals("Tester");
    }

    @Test
    void getPassword() {
        mainMenuState.setPassword("Tester");
        assert mainMenuState.getPassword().equals("Tester");
    }

    @Test
    void setUsername() {
        mainMenuState.setUsername("Tester");
        assert mainMenuState.getUsername().equals("Tester");
    }

    @Test
    void setPassword() {
        mainMenuState.setPassword("Tester");
        assert mainMenuState.getPassword().equals("Tester");
    }

    @Test
    void testToString() {
        mainMenuState.setUsername("Tester");
        assert mainMenuState.getUsername().equals("Tester");
        mainMenuState.setPassword("Tester");
        assert mainMenuState.getPassword().equals("Tester");
        System.out.println(mainMenuState.toString());
        assert mainMenuState.toString().equals("SignupState{username='Tester', password='Tester', repeatPassword=''}");
    }

    @Test
    void testCopy() {
        mainMenuState.setUsername("Tester");
        assert mainMenuState.getUsername().equals("Tester");
        mainMenuState.setPassword("Tester");
        assert mainMenuState.getPassword().equals("Tester");
        MainMenuState mainMenuState1 = new MainMenuState(mainMenuState);
        assert mainMenuState1.getUsername().equals("Tester");
        assert mainMenuState1.getPassword().equals("Tester");
    }
}