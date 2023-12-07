package view;


import interface_adapter.zip_search.ZipSearchController;
import interface_adapter.zip_search.ZipSearchState;
import interface_adapter.zip_search.ZipSearchViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The zip search class that contains the input fields and the zip search buttons
 * for the Zip Search use case
 * @author Diego
 */
public class ZipSearchView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "zip_search";
    private final ZipSearchViewModel zipSearchViewModel;
    private final JTextField zipcodeInputField = new JTextField(15);
    private final JTextField radiusInputField = new JTextField(15);
    private final ZipSearchController zipSearchController;

    private final JButton search;
    private final JButton back;

    /**
     * Instantiates the ZipSearchView.
     * @param zipSearchController The View Model used for displaying the text in the view
     * @param zipSearchViewModel The Controller used for making the search work
     */
    public ZipSearchView(ZipSearchController zipSearchController, ZipSearchViewModel zipSearchViewModel) {

        this.zipSearchController = zipSearchController;
        this.zipSearchViewModel = zipSearchViewModel;
        this.zipSearchViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(ZipSearchViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel zipSearchZipInfo = new LabelTextPanel(
                new JLabel(ZipSearchViewModel.ZIPCODE_LABEL), zipcodeInputField);

        LabelTextPanel zipSearchRadInfo = new LabelTextPanel(
                new JLabel(ZipSearchViewModel.RADIUS_LABEL), radiusInputField);

        JPanel buttons = new JPanel();
        search = new JButton(ZipSearchViewModel.SEARCH_BUTTON_LABEL);
        buttons.add(search);
        back = new JButton(ZipSearchViewModel.BACK_BUTTON_LABEL);
        buttons.add(back);

        search.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    /**
                     * When the search button is pressed, call the executeZipSearch method
                     * of the searchController, providing the content of the searchInputField.
                     * @param evt the event to be processed
                     */
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(search)) {
                            System.out.println("Search pressed!");
                            ZipSearchState currentState = zipSearchViewModel.getState();
                            System.out.println("the input: " + currentState.getRadSearchCriteria());

                            zipSearchController.executeSearch(
                                    currentState.getZipSearchCriteria(),
                                    currentState.getRadSearchCriteria()
                            );
                        }
                    }
                }
        );

        back.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    /**
                     * When the back button is pressed, call the executeBack method
                     * of the zipSearchController.
                     * @param evt the event to be processed
                     */
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(back)) {
                            System.out.println("Back pressed");
                            zipSearchController.executeBack();
                        }
                    }
                }
        );

        zipcodeInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        ZipSearchState currentState = zipSearchViewModel.getState();
                        String text = zipcodeInputField.getText() + e.getKeyChar();
                        currentState.setZipSearchCriteria(text);
                        zipSearchViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                }
        );

        radiusInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        ZipSearchState currentState = zipSearchViewModel.getState();
                        String text = radiusInputField.getText() + e.getKeyChar();
                        currentState.setRadSearchCriteria(text);
                        zipSearchViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(zipSearchZipInfo);
        this.add(zipSearchRadInfo);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "ZipSearch not implemented yet.");
    }

    /**
     * If there is an error in the search query or a lack of results, show a popup to
     * the user regarding what has happened.
     * @param evt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String y = evt.getPropertyName();
        if (y.equals("failure")) {
            ZipSearchState state = (ZipSearchState) evt.getNewValue();
            JOptionPane.showMessageDialog(this, state.getZipSearchError());
            state.setZipSearchError(null);
        }
    }
}
