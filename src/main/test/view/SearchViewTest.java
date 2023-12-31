package view;

import app.Main;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SearchViewTest {
    static String message = "";
    static boolean popUpDiscovered = false;

    public JButton getMainButton(int i) {
        JFrame app = getApp();

        JPanel jp2 = getPrimaryJpanel(app);

        MainMenuView sv = (MainMenuView) jp2.getComponent(0);

        JPanel buttons = (JPanel) sv.getComponent(1);
        return (JButton) buttons.getComponent(i);
    }

    public JButton getSubViewButton(int i) {
        JFrame app = getApp();

        JPanel jp2 = getPrimaryJpanel(app);

        SearchView sv = (SearchView) jp2.getComponent(5);

        JPanel buttons = (JPanel) sv.getComponent(1);
        return (JButton) buttons.getComponent(i);
    }

    public void getToSearchView() {
        JButton button1 = getMainButton(1);
        button1.doClick();

        SubViewTest subViewTest = new SubViewTest();

        JButton button2 = subViewTest.getButton(1);
        button2.doClick();
    }

    public JButton getButton(int i) {
        JFrame app = getApp();

        JPanel jp2 = getPrimaryJpanel(app);

        SearchView sv = (SearchView) jp2.getComponent(7);

        JPanel buttons = (JPanel) sv.getComponent(2);
        return (JButton) buttons.getComponent(i);
    }

    public LabelTextPanel getSearchInfoTextPanel() {
        JFrame app = getApp();

        JPanel jp2 = getPrimaryJpanel(app);

        SearchView sv = (SearchView) jp2.getComponent(7);

        return (LabelTextPanel) sv.getComponent(1);
    }



    public boolean getShowing(int i) {
        JFrame app = getApp();

        JPanel jp2 = getPrimaryJpanel(app);

        return jp2.getComponent(i).isShowing();
    }

    /**
     * Test that the Search button is present and where it is expected to be
     * And that pressing the button switches to ResultsView
     */
    @org.junit.Test
    public void testSearchButtonSuccess() {
        Main.main(null);

        getToSearchView();

        JButton button = getButton(0);
        assert (button.getText().equals("Search"));

        // get a reference to the Search field
        String searchC = "New York";
        LabelTextPanel panel = getSearchInfoTextPanel();

        JTextField searchTextField = (JTextField) panel.getComponent(1);

        writeTextField(searchC, searchTextField, panel);


        button.doClick();

        assert getShowing(9);
    }

    @org.junit.Test
    public void testSearchButtonInputError() {
        Main.main(null);

        getToSearchView();

        JButton button = getButton(0);
        assert (button.getText().equals("Search"));

        // get a wrong reference to the Search field
        String searchC = "error";
        LabelTextPanel panel = getSearchInfoTextPanel();

        JTextField SearchTextField = (JTextField) panel.getComponent(1);

        writeTextField(searchC, SearchTextField, panel);


        //popUpDiscovered = false;
        //message = "";

        // since clicking the button should end up displaying a JDialog to the user to report the
        // result, we set a timer, which will execute code necessary to complete the testing.
        createCloseTimer().start();

        button.doClick();

        // confirm a popUp was discovered
        assert (popUpDiscovered);
        System.out.println("popup was detected successfully.");
        assert (message.equals("Results not found!"));

    }

    /**
     * Test that the Back button is present and where it is expected to be
     * And that pressing the button switches to SubView
     */
    @org.junit.Test
    public void testCancelButton() {
        Main.main(null);
        JButton button = getButton(1);
        assert (button.getText().equals("Back"));

        button.doClick();

        assert getShowing(5);
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
                            message = s;
                            popUpDiscovered = true;
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


