package view;

import app.Main;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MainMenuTest {

    public JButton getButton(int i) {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app); // found the window?

        Component root = app.getComponent(0);

//        System.out.println(root);

        Component cp = ((JRootPane) root).getContentPane();

//        System.out.println(cp);

        JPanel jp = (JPanel) cp;

//        System.out.println(jp.getUIClassID());

        JPanel jp2 = (JPanel) jp.getComponent(0);

//        System.out.println(jp2.getUIClassID());

        MainMenuView sv = (MainMenuView) jp2.getComponent(0);

        JPanel buttons = (JPanel) sv.getComponent(1);
//
//        JButton button1 = (JButton) buttons.getComponent(0);
////        System.out.println(button1.getText());
//
//        JButton button2 = (JButton) buttons.getComponent(1);
//        System.out.println(button2.getText());
//
//        JButton button3 = (JButton) buttons.getComponent(2);
//        System.out.println(button3.getText());
//
//        JButton button4 = (JButton) buttons.getComponent(3);
//        System.out.println(button4.getText());

        return (JButton) buttons.getComponent(i); // this should be the clear button
    }

    public boolean getShowing(int i) {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app); // found the window?

        Component root = app.getComponent(0);

        Component cp = ((JRootPane) root).getContentPane();

        JPanel jp = (JPanel) cp;

        JPanel jp2 = (JPanel) jp.getComponent(0);

//        MainMenuView sv = ( MainMenuView) jp2.getComponent(0);
//
//        LoginView logv = (LoginView) jp2.getComponent(1);
//
//        System.out.println(sv.isShowing());
//        System.out.println(logv.isShowing());
//
//        System.out.println(sv.viewName);

        return jp2.getComponent(i).isShowing();
    }

    /**
     * Test that the Signup button is present and where it is expected to be
     * And that pressing the button switches to SignUpView
     */
    @org.junit.Test
    public void testSignUpButtonPresent() {
        Main.main(null);
        JButton button = getButton(0);
        assert (button.getText().equals("Sign up"));
    }

    @org.junit.Test
    public void testSignUpButtonPressed() {

        Main.main(null);
        JButton button = getButton(0);
        button.doClick();

        assert (getShowing(2));
    }

    /**
     * Test that the guest button is present and where it is expected to be
     * And that pressing the button switches to subview
     */
    @org.junit.Test
    public void testContinueAsGuestButtonPresent() {
        Main.main(null);
        JButton button = getButton(1);
        assert (button.getText().equals("Continue as Guest"));
    }

    @org.junit.Test
    public void testContinueAsGuestButtonPressed() {
        Main.main(null);
        JButton button = getButton(1);
        button.doClick();

        assert (getShowing(5));
    }

    /**
     * Test that the signin button is present and where it is expected to be
     * And that pressing the buttons switches to the signin view
     */
    @org.junit.Test
    public void testSignInButtonPresent() {
        Main.main(null);
        JButton button = getButton(2);
        assert (button.getText().equals("Sign in"));
    }

    @org.junit.Test
    public void testSignInButtonPressed() {
        Main.main(null);
        JButton button = getButton(2);
        button.doClick();

        assert (getShowing(1));
    }

    /**
     * Test that the settings button is present and where it is expected to be
     */
    @org.junit.Test
    public void testSettingsButtonPresent() {
        Main.main(null);
        JButton button = getButton(3);
        assert (button.getText().equals("Settings"));
    }
}
