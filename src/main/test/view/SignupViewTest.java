package view;

import app.Main;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

public class SignupViewTest {

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

        SignupView sv = ( SignupView) jp2.getComponent(2);

        JPanel buttons = (JPanel) sv.getComponent(4);
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

    public JTextField getTextField() {
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

        SignupView sv = ( SignupView) jp2.getComponent(2);

        JPanel textfields = (JPanel) sv.getComponent(2);

        return (JTextField) textfields.getComponent(1); // this should be the clear button
    }

    public JPasswordField getPasswordField(int i) {
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

        SignupView sv = ( SignupView) jp2.getComponent(2);

        JPanel passwordfields = (JPanel) sv.getComponent(3);

        // i can be 1 or 2
        return (JPasswordField) passwordfields.getComponent(1); // this should be the clear button
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
     *
     * Test that the Signup button is present and where it is expected to be
     * And that pressing the button switches to SignInView
     */
    @org.junit.Test
    public void testSignUpButton() {
        Main.main(null);
        JButton button = getButton(0);
        assert(button.getText().equals("Sign up"));

        JTextField jTextField = getTextField();
        int randomNum = ThreadLocalRandom.current().nextInt(100, 123344 + 1);
        jTextField.setText("test" + randomNum);

        JPasswordField jPasswordField1 = getPasswordField(1);
        JPasswordField jPasswordField2 = getPasswordField(2);

        jPasswordField1.setText("password"+randomNum);
        jPasswordField2.setText("password"+randomNum);

        button.doClick();
    }

    /**
     *
     * Test that the Cancel button is present and where it is expected to be
     * And that pressing the button switches to mainMenu
     */
    @org.junit.Test
    public void testCancelButton() {
        Main.main(null);
        JButton button = getButton(1);
        assert(button.getText().equals("Cancel"));

        button.doClick();

        assert getShowing(0);
    }

}