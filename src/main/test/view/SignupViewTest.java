package view;

import app.Main;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupViewModel;
import org.jetbrains.annotations.Nullable;
import use_case.signup.SignupInputBoundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SignupViewTest {
    static String message = "";
    static boolean popUpDiscovered = false;

    public JButton getMainButton(int i) {
        JFrame app = getApp();

        JPanel jp2 = getPrimaryJpanel(app);

        MainMenuView sv = (MainMenuView) jp2.getComponent(0);

        JPanel buttons = (JPanel) sv.getComponent(1);
        return (JButton) buttons.getComponent(i); // this should be the clear button
    }

    public JButton getButton(int i) {
        JFrame app = getApp();

        JPanel jp2 = getPrimaryJpanel(app);

        SignupView sv = (SignupView) jp2.getComponent(2);

        JPanel buttons = (JPanel) sv.getComponent(4);
        return (JButton) buttons.getComponent(i); // this should be the clear button
    }

    public LabelTextPanel getPasswordPanel1() {
        JFrame app = getApp();

        JPanel jp2 = getPrimaryJpanel(app);

        SignupView sv = (SignupView) jp2.getComponent(2);

        return (LabelTextPanel) sv.getComponent(2);
    }

    public LabelTextPanel getPasswordPanel2() {
        JFrame app = getApp();

        JPanel jp2 = getPrimaryJpanel(app);

        SignupView sv = (SignupView) jp2.getComponent(2);

        return (LabelTextPanel) sv.getComponent(3);
    }

    public LabelTextPanel getTextPanel() {
        JFrame app = getApp();

        JPanel jp2 = getPrimaryJpanel(app);

        SignupView sv = (SignupView) jp2.getComponent(2);

        // i can be 1 or 2
        return (LabelTextPanel) sv.getComponent(1);
    }

    public boolean getShowing(int i) {
        JFrame app = getApp();

        JPanel jp2 = getPrimaryJpanel(app);

        return jp2.getComponent(i).isShowing();
    }

    /**
     * Test that the Signup button is present and where it is expected to be
     * And that pressing the button switches to SignInView
     */
    @org.junit.Test
    public void testSignUpButtonSuccess() {

        int randomNum = ThreadLocalRandom.current().nextInt(100, 1000000);
        String userPass = "Test" + randomNum;

        Main.main(null);

        JButton button1 = getMainButton(0);
        button1.doClick();

        assert (getShowing(2));


        JButton button = getButton(0);
        assert (button.getText().equals("Sign up"));

        // get a reference to the first username field
        LabelTextPanel panel = getTextPanel();

        JTextField jTextField = (JTextField) panel.getComponent(1);

        writeTextField(userPass, jTextField, panel);

        // get a reference to the first username field
        LabelTextPanel panel2 = getPasswordPanel1();

        JPasswordField pwdField = (JPasswordField) panel2.getComponent(1);

        writeTextField(userPass, pwdField, panel);

        // get a reference to the second password field
        LabelTextPanel panel3 = getPasswordPanel2();
        JPasswordField pwdField2 = (JPasswordField) panel3.getComponent(1);

        writeTextField(userPass, pwdField2, panel);

        button.doClick();

        assert (getShowing(1));
    }

    @org.junit.Test
    public void testSignUpButtonPasswordNotMatch() {

        int randomNum = ThreadLocalRandom.current().nextInt(10, 1000000);
        String userName = "Test" + randomNum;
        String pass1 = "1234";
        String pass2 = "123";


        Main.main(null);

        JButton button1 = getMainButton(0);
        button1.doClick();

        assert (getShowing(2));


        JButton button = getButton(0);
        assert (button.getText().equals("Sign up"));

        // get a reference to the first username field
        LabelTextPanel panel = getTextPanel();

        JTextField jTextField = (JTextField) panel.getComponent(1);

        writeTextField(userName, jTextField, panel);

        // get a reference to the first username field
        LabelTextPanel panel2 = getPasswordPanel1();

        JPasswordField pwdField = (JPasswordField) panel2.getComponent(1);

        writeTextField(pass1, pwdField, panel);

        // get a reference to the second password field
        LabelTextPanel panel3 = getPasswordPanel2();
        JPasswordField pwdField2 = (JPasswordField) panel3.getComponent(1);

        writeTextField(pass2, pwdField2, panel);

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
        assert (message.equals("Passwords don't match."));

        assert (getShowing(2));
    }

    @org.junit.Test
    public void testSignUpButtonUserAlreadyExists() {

        String userPass = "abcd";

        Main.main(null);

        JButton button1 = getMainButton(0);
        button1.doClick();

        assert (getShowing(2));


        JButton button = getButton(0);
        assert (button.getText().equals("Sign up"));

        // get a reference to the first username field
        LabelTextPanel panel = getTextPanel();

        JTextField jTextField = (JTextField) panel.getComponent(1);

        writeTextField(userPass, jTextField, panel);
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
        assert (message.equals("User already exists."));

        assert (getShowing(2));
    }

    @org.junit.Test
    public void testSignUpButtonPasswordNotValid() {

        int randomNum = ThreadLocalRandom.current().nextInt(10, 1000000);
        String userName = "Test" + randomNum;
        String pass1 = "12345";
        String pass2 = "12345";

        Main.main(null);

        JButton button1 = getMainButton(0);
        button1.doClick();

        assert (getShowing(2));


        JButton button = getButton(0);
        assert (button.getText().equals("Sign up"));

        // get a reference to the first username field
        LabelTextPanel panel = getTextPanel();

        JTextField jTextField = (JTextField) panel.getComponent(1);

        writeTextField(userName, jTextField, panel);

        // get a reference to the first username field
        LabelTextPanel panel2 = getPasswordPanel1();

        JPasswordField pwdField = (JPasswordField) panel2.getComponent(1);

        writeTextField(pass1, pwdField, panel);

        // get a reference to the second password field
        LabelTextPanel panel3 = getPasswordPanel2();
        JPasswordField pwdField2 = (JPasswordField) panel3.getComponent(1);

        writeTextField(pass2, pwdField2, panel);

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
        assert (message.equals("Password must be longer than 5 characters (no spaces)"));

        assert (getShowing(2));
    }

    @org.junit.Test
    public void testSignUpButtonUsernameNotValid() {
        String userName = "a ";
        String pass1 = "123456";
        String pass2 = "123456";

        Main.main(null);

        JButton button1 = getMainButton(0);
        button1.doClick();

        assert (getShowing(2));


        JButton button = getButton(0);
        assert (button.getText().equals("Sign up"));

        // get a reference to the first username field
        LabelTextPanel panel = getTextPanel();

        JTextField jTextField = (JTextField) panel.getComponent(1);

        writeTextField(userName, jTextField, panel);

        // get a reference to the first username field
        LabelTextPanel panel2 = getPasswordPanel1();

        JPasswordField pwdField = (JPasswordField) panel2.getComponent(1);

        writeTextField(pass1, pwdField, panel);

        // get a reference to the second password field
        LabelTextPanel panel3 = getPasswordPanel2();
        JPasswordField pwdField2 = (JPasswordField) panel3.getComponent(1);

        writeTextField(pass2, pwdField2, panel);

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
        assert (message.equals("Username must be longer than 1 characters (no spaces)"));

        assert (getShowing(2));
    }

    @org.junit.Test
    public void testSignupView() {

        // create the UI; note, we don't make a real SignupInputBoundary,
        // since we don't need it for this test.
        SignupInputBoundary sib = null;
        SignupController controller = new SignupController(sib);
        SignupViewModel viewModel = new SignupViewModel();
        JPanel signupView = new SignupView(controller, viewModel);
        JFrame jf = new JFrame();
        jf.setContentPane(signupView);
        jf.pack();
        jf.setVisible(true);

        // get a reference to the first password field
        LabelTextPanel panel = (LabelTextPanel) signupView.getComponent(2);
        JPasswordField pwdField = (JPasswordField) panel.getComponent(1);

        // create and dispatch KeyEvents to the UI
        KeyEvent event = new KeyEvent(
                pwdField, // we are interacting with the pwdField
                KeyEvent.KEY_TYPED, //
                System.currentTimeMillis(), // say the event happened right now
                0, // no modifiers
                KeyEvent.VK_UNDEFINED, // for KEY_TYPED, the KeyCode is undefined per documentation
                'y'); // the character that is being typed

        panel.dispatchEvent(event);


        // pause execution for a second
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // print the current values the password field and view-model hold
        System.out.println("field 1: " + new String(pwdField.getPassword()));
        System.out.println("view-model: " + viewModel.getState().getPassword());

        // move to the right in the password field, otherwise the event
        // will type the character as the first character instead of the last!
        KeyEvent eventRight = new KeyEvent(
                pwdField,
                KeyEvent.KEY_PRESSED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_RIGHT,
                KeyEvent.CHAR_UNDEFINED
        );
        panel.dispatchEvent(eventRight);

        // pause execution for a second
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // type a second character
        KeyEvent event2 = new KeyEvent(
                pwdField,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                'z');
        panel.dispatchEvent(event2);


        // pause execution for 3 seconds
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // print the current values the password field and view-model hold
        System.out.println("field 1: " + new String(pwdField.getPassword()));
        System.out.println("view-model: " + viewModel.getState().getPassword());

        // assert that the values are as expected.
        assertEquals("yz", new String(pwdField.getPassword()));
        assertEquals("yz", viewModel.getState().getPassword());
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

                Window[] windows = Window.getWindows();
                for (Window window : windows) {

                    if (window instanceof JDialog) {

                        JDialog dialog = (JDialog) window;

                        // this ignores old dialogs
                        if (dialog.isVisible()) {
                            System.out.println("found jdialogue");
                            String s = ((JOptionPane) ((BorderLayout) dialog.getRootPane()
                                    .getContentPane().getLayout()).getLayoutComponent(BorderLayout.CENTER)).getMessage().toString();
                            System.out.println("message = " + s);

                            // store the information we got from the JDialog
                            SignupViewTest.message = s;
                            SignupViewTest.popUpDiscovered = true;

                            System.out.println("disposing of..." + window.getClass());
                            window.dispose();
                        }
                    }
                }
            }

        };

        Timer t = new Timer(1000, close);
        t.setRepeats(false);
        return t;
    }

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

        JPanel jp2 = (JPanel) jp.getComponent(0);
        return jp2;
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