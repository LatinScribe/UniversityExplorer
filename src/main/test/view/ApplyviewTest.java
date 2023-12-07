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

        MainMenuView sv = (MainMenuView) jp2.getComponent(0);

        JPanel buttons = (JPanel) sv.getComponent(1);
        return (JButton) buttons.getComponent(i);
    }

    public JButton getSubButton(int i) {
        JFrame app = getApp();

        JPanel jp2 = getPrimaryJpanel(app);

        SubView sv = (SubView) jp2.getComponent(5);

        JPanel buttons = (JPanel) sv.getComponent(1);
        return (JButton) buttons.getComponent(i);
    }

    public JButton getApplyButton(int i) {
        JFrame app = getApp();

        JPanel jp2 = getPrimaryJpanel(app);

        Applyview sv = (Applyview) jp2.getComponent(6);

        JPanel buttons = (JPanel) sv.getComponent(3);
        return (JButton) buttons.getComponent(i);
    }

    public LabelTextPanel getTextPanel() {
        JFrame app = getApp();

        JPanel jp2 = getPrimaryJpanel(app);

        Applyview sv = (Applyview) jp2.getComponent(6);

        // i can be 1 or 2
        return (LabelTextPanel) sv.getComponent(1);
    }

    public LabelTextPanel getTextPanel2() {
        JFrame app = getApp();

        JPanel jp2 = getPrimaryJpanel(app);

        Applyview sv = (Applyview) jp2.getComponent(6);

        return (LabelTextPanel) sv.getComponent(2);
    }

    public boolean getShowing(int i) {
        JFrame app = getApp();

        JPanel jp2 = getPrimaryJpanel(app);

        return jp2.getComponent(i).isShowing();
    }

    @org.junit.Test
    public void testApplySuccess() {

        Main.main(null);

        JButton button1 = getMainButton(1);
        button1.doClick();

        assert (getShowing(5));


        JButton button2 = getSubButton(0);
        System.out.println(button2.getText());
        assert (button2.getText().equals("Get University Recommendations"));
        button2.doClick();

        assert (getShowing(6));

        // get a reference to the first username field
        LabelTextPanel panel = getTextPanel();

        JTextField jTextField = (JTextField) panel.getComponent(1);

        String act = "28";

        writeTextField(act, jTextField, panel);

        // get a reference to the first password field
        LabelTextPanel panel2 = getTextPanel2();

        JTextField pwdField = (JTextField) panel2.getComponent(1);

        String sat = "1200";

        writeTextField(sat, pwdField, panel);

        popUpDiscovered = false;

        JButton button = getApplyButton(0);
        System.out.println(button.getText());

        createCloseTimer().start();

        button.doClick();
        System.out.println("Popup was detected successfully");
        assert (message.equals("John Brown University SAT:1257 ACT:27"));
    }

    @org.junit.Test
    public void testApplyFail() {

        Main.main(null);

        JButton button1 = getMainButton(1);
        button1.doClick();

        assert (getShowing(5));


        JButton button2 = getSubButton(0);
        System.out.println(button2.getText());
        assert (button2.getText().equals("Get University Recommendations"));
        button2.doClick();

        assert (getShowing(6));

        // get a reference to the first username field
        LabelTextPanel panel = getTextPanel();

        JTextField jTextField = (JTextField) panel.getComponent(1);

        String act = "0";

        writeTextField(act, jTextField, panel);

        // get a reference to the first password field
        LabelTextPanel panel2 = getTextPanel2();

        JTextField pwdField = (JTextField) panel2.getComponent(1);

        String sat = "0";

        writeTextField(sat, pwdField, panel);

        popUpDiscovered = false;

        JButton button = getApplyButton(0);
        System.out.println(button.getText());

        createCloseTimer().start();

        button.doClick();
        System.out.println("Popup was detected successfully");
        assert (message.equals("Error: No universities found"));
    }

    @org.junit.Test
    public void testApplyBack() {

        Main.main(null);

        JButton button1 = getMainButton(1);
        button1.doClick();

        assert (getShowing(5));


        JButton button2 = getSubButton(0);
        System.out.println(button2.getText());
        assert (button2.getText().equals("Get University Recommendations"));
        button2.doClick();

        assert (getShowing(6));

        JButton button = getApplyButton(1);
        System.out.println(button.getText());

        button.doClick();
        assert (getShowing(5));
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
                            ApplyviewTest.message = s;
                            ApplyviewTest.popUpDiscovered = true;

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
