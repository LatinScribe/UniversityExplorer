package view;

import app.Main;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ApplyviewTest {
    static String message = "";
    static boolean popUpDiscovered = false;

    public JButton getMainButton(int i) {
        JFrame app = getApp();

        JPanel jp2 = getPrimaryJpanel(app);

        Applyview sv = (Applyview) jp2.getComponent(0);

        JPanel buttons = (JPanel) sv.getComponent(1);
        return (JButton) buttons.getComponent(i);
    }

    public JButton getButton(int i) {
        JFrame app = getApp();

        JPanel jp2 = getPrimaryJpanel(app);

        Applyview sv = (Applyview) jp2.getComponent(1);

        JPanel buttons = (JPanel) sv.getComponent(5);
        return (JButton) buttons.getComponent(i);
    }

    public LabelTextPanel getTextPanel() {
        JFrame app = getApp();

        JPanel jp2 = getPrimaryJpanel(app);

        Applyview sv = (Applyview) jp2.getComponent(1);

        // i can be 1 or 2
        return (LabelTextPanel) sv.getComponent(1);
    }

    public LabelTextPanel getPasswordPanel1() {
        JFrame app = getApp();

        JPanel jp2 = getPrimaryJpanel(app);

        LoginView sv = (LoginView) jp2.getComponent(1);

        return (LabelTextPanel) sv.getComponent(3);
    }

    public boolean getShowing(int i) {
        JFrame app = getApp();

        JPanel jp2 = getPrimaryJpanel(app);

        return jp2.getComponent(i).isShowing();
    }

    @org.junit.Test
    public void testLoginButtonSuccess() {

        String userName = "abcd";
        String userPass = "1234";

        Main.main(null);

        JButton button1 = getMainButton(2);
        button1.doClick();

        assert (getShowing(1));


        JButton button = getButton(0);
        System.out.println(button.getText());
        assert (button.getText().equals("Log in"));

        // get a reference to the first username field
        LabelTextPanel panel = getTextPanel();

        JTextField jTextField = (JTextField) panel.getComponent(1);

        writeTextField(userName, jTextField, panel);

        // get a reference to the first password field
        LabelTextPanel panel2 = getPasswordPanel1();

        JPasswordField pwdField = (JPasswordField) panel2.getComponent(1);

        writeTextField(userPass, pwdField, panel);

        button.doClick();

        assert (getShowing(3));
    }

    @org.junit.Test
    public void testLoginButtonIncorrect() {

        String userName = "abcd";
        String userPass = "12345";

        Main.main(null);

        JButton button1 = getMainButton(2);
        button1.doClick();

        assert (getShowing(1));

        JButton button = getButton(0);
        System.out.println(button.getText());
        assert (button.getText().equals("Log in"));

        // get a reference to the first username field
        LabelTextPanel panel = getTextPanel();

        JTextField jTextField = (JTextField) panel.getComponent(1);

        writeTextField(userName, jTextField, panel);

        // get a reference to the first password field
        LabelTextPanel panel2 = getPasswordPanel1();

        JPasswordField pwdField = (JPasswordField) panel2.getComponent(1);

        writeTextField(userPass, pwdField, panel);

        popUpDiscovered = false;
        message = "";

        // since clicking the button should end up displaying a JDialog to the user to report the
        // result, we set a timer, which will execute code necessary to complete the testing.
        createCloseTimer().start();

        //click the button
        button.doClick();

        // will continue execution here after the JDialog is closed

        // confirm a popUp was discovered
        assert (popUpDiscovered);
        System.out.println("popup was detected successfully.");
        System.out.println(message);
        assert (message.equals("PASSWORD OR USERNAME INCORRECT"));

        assert (getShowing(1));
    }

    @org.junit.Test
    public void testLoginButtonAccountNotExist() {
        int randomNum = ThreadLocalRandom.current().nextInt(10000000, 100000000);
        String userName = "abcd" + randomNum;
        String userPass = "12345";

        Main.main(null);

        JButton button1 = getMainButton(2);
        button1.doClick();

        assert (getShowing(1));

        JButton button = getButton(0);
        System.out.println(button.getText());
        assert (button.getText().equals("Log in"));

        // get a reference to the first username field
        LabelTextPanel panel = getTextPanel();

        JTextField jTextField = (JTextField) panel.getComponent(1);

        writeTextField(userName, jTextField, panel);

        // get a reference to the first password field
        LabelTextPanel panel2 = getPasswordPanel1();

        JPasswordField pwdField = (JPasswordField) panel2.getComponent(1);

        writeTextField(userPass, pwdField, panel);

        popUpDiscovered = false;
        message = "";

        // since clicking the button should end up displaying a JDialog to the user to report the
        // result, we set a timer, which will execute code necessary to complete the testing.
        createCloseTimer().start();

        //click the button
        button.doClick();

        // will continue execution here after the JDialog is closed

        // confirm a popUp was discovered
        assert (popUpDiscovered);
        System.out.println("popup was detected successfully.");
        System.out.println(message);
        assert (message.equals(userName + ": Account does not exist."));

        assert (getShowing(1));
    }

    /**
     * Test that the Cancel button is present and where it is expected to be
     * And that pressing the button switches to mainMenu
     */
    @org.junit.Test
    public void testCancelButton() {
        Main.main(null);
        JButton button = getButton(1);
        assert (button.getText().equals("Cancel"));

        button.doClick();

        assert getShowing(0);
    }

    private Timer createCloseTimer() {
        ActionListener close = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("checking for dialogue");

                Window[] windows = Window.getWindows();
                for (Window window : windows) {

                    if (window instanceof JDialog) {
                        System.out.println("found jdialogue");

                        JDialog dialog = (JDialog) window;

                        // this ignores old dialogs
                        if (dialog.isVisible()) {
                            String s = ((JOptionPane) ((BorderLayout) dialog.getRootPane()
                                    .getContentPane().getLayout()).getLayoutComponent(BorderLayout.CENTER)).getMessage().toString();
                            System.out.println("message = " + s);

                            // store the information we got from the JDialog
                            LoginViewTest.message = s;
                            LoginViewTest.popUpDiscovered = true;

                            System.out.println("disposing of..." + window.getClass());
                            window.dispose();
                        }
                    }
                }
            }

        };

        Timer t = new Timer(1000, close);
        t.setRepeats(true);
        return t;
    }

    /**
     * private helper functions
     */
    private static void writeTextField(String userPass, JTextField jTextField, LabelTextPanel panel) {
        for (int i = 0; i < userPass.length(); i++) {
            char c = userPass.charAt(i);
            // create and dispatch KeyEvents to the UI
            KeyEvent event = new KeyEvent(
                    jTextField, // we are interacting with the pwdField
                    KeyEvent.KEY_TYPED, //
                    System.currentTimeMillis(), // say the event happened right now
                    0, // no modifiers
                    KeyEvent.VK_UNDEFINED, // for KEY_TYPED, the KeyCode is undefined per documentation
                    c); // the character that is being typed

            panel.dispatchEvent(event);

            // pause execution for tenth of second
            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // move to the right in the password field, otherwise the event
            // will type the character as the first character instead of the last!
            KeyEvent eventRight = new KeyEvent(
                    jTextField,
                    KeyEvent.KEY_PRESSED,
                    System.currentTimeMillis(),
                    0,
                    KeyEvent.VK_RIGHT,
                    KeyEvent.CHAR_UNDEFINED
            );
            panel.dispatchEvent(eventRight);

            // pause execution for tenth of second
            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static JPanel getPrimaryJpanel(JFrame app) {
        assertNotNull(app); // found the window?

        Component root = app.getComponent(0);

        Component cp = ((JRootPane) root).getContentPane();

        JPanel jp = (JPanel) cp;

        return (JPanel) jp.getComponent(0);
    }

    @Nullable
    private static JFrame getApp() {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }
        return app;
    }

}
