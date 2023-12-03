package view;

import app.Main;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SubViewTest {

    public JButton getButton(int i) {
        JFrame app = getApp();

        JPanel jp2 = getPrimaryJpanel(app);

        SubView sv = (SubView) jp2.getComponent(5);

        JPanel buttons = (JPanel) sv.getComponent(1);
        return (JButton) buttons.getComponent(i); // this should be the clear button
    }

    public boolean getShowing(int i) {
        JFrame app = getApp();

        JPanel jp2 = getPrimaryJpanel(app);

        return jp2.getComponent(i).isShowing();
    }

    /**
     * Test that the Recommendation button is present and where it is expected to be
     * And that pressing the button switches to ApplyView
     */
    @org.junit.Test
    public void testRecommendationButtonPresent() {
        Main.main(null);
        JButton button = getButton(0);
        assert (button.getText().equals("Get University Recommendations"));
    }

    @org.junit.Test
    public void testRecommendationButtonPressed() {

        Main.main(null);
        JButton button = getButton(0);
        button.doClick();

        assert (getShowing(6));
    }

    /**
     * Test that the Search button is present and where it is expected to be
     * And that pressing the button switches to SearchView
     */
    @org.junit.Test
    public void testSearchButtonPresent() {
        Main.main(null);
        JButton button = getButton(1);
        assert (button.getText().equals("Search Universities by Name"));
    }

    @org.junit.Test
    public void testSearchButtonPressed() {

        Main.main(null);
        JButton button = getButton(1);
        button.doClick();

        assert (getShowing(7));
    }

    /**
     * Test that the ZipSearch button is present and where it is expected to be
     * And that pressing the button switches to ZipSearchView
     */
    @org.junit.Test
    public void testZipSearchButtonPresent() {
        Main.main(null);
        JButton button = getButton(2);
        assert (button.getText().equals("Search Universities by ZIP Code"));
    }

    @org.junit.Test
    public void testZipSearchButtonPressed() {

        Main.main(null);
        JButton button = getButton(2);
        button.doClick();

        assert (getShowing(8));
    }

    /**
     * Test that the Back button is present and where it is expected to be
     * And that pressing the button switches to MainView
     */
    @org.junit.Test
    public void testBackButtonPresent() {
        Main.main(null);
        JButton button = getButton(3);
        assert (button.getText().equals("Back to Main Menu"));
    }

    @org.junit.Test
    public void testBackButtonPressed() {

        Main.main(null);
        JButton button = getButton(3);
        button.doClick();

        assert (getShowing(0));
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
