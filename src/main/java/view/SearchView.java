package view;

import interface_adapter.search.SearchController;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * A view with an input field and 2 buttons which allows for the Search use case to be performed.
 * @author Andre
 */
public class SearchView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "search";

    private final SearchViewModel searchViewModel;
    private final JTextField searchInputField = new JTextField(15);
    private final SearchController searchController;

    private final JButton search;
    private final JButton back;

    /**
     * Instantiates the SearchView.
     * @param searchController
     * @param searchViewModel
     */
    public SearchView(SearchController searchController, SearchViewModel searchViewModel) {

        this.searchController = searchController;
        this.searchViewModel = searchViewModel;
        searchViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(SearchViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel searchInfo = new LabelTextPanel(
                new JLabel("Type in the name of a university here:"), searchInputField);

        JPanel buttons = new JPanel();
        search = new JButton(SearchViewModel.SEARCH_BUTTON_LABEL);
        buttons.add(search);
        back = new JButton(SearchViewModel.BACK_BUTTON_LABEL);
        buttons.add(back);

        search.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    /**
                     * When the search button is pressed, call the executeSearch method of the searchController,
                     * providing the content of the searchInputField.
                     * @param evt the event to be processed
                     */
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(search)) {
                            System.out.println("Search pressed!");
                            SearchState currentState = searchViewModel.getState();

                            searchController.executeSearch(
                                    currentState.getSearchCriteria()
                            );
                        }
                    }
                }
        );

        back.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    /**
                     * When the back button is pressed, call the executeBack method of the searchController.
                     * @param evt the event to be processed
                     */
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(back)) {
                            System.out.println("Back pressed");
                            searchController.executeBack();
                        }
                    }
                }
        );

        searchInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SearchState currentState = searchViewModel.getState();
                        String text = searchInputField.getText() + e.getKeyChar();
                        String accumulator = "";
                        for (int counter = 0; counter < text.length(); counter++) {
                            String substring = text.substring(counter, counter + 1);
                            if (!(substring.equals("\b"))) {
                                accumulator += substring;
                            }
                        }
                        currentState.setSearchCriteria(accumulator);
                        searchViewModel.setState(currentState);
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
        this.add(searchInfo);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Search not implemented yet.");
    }

    /**
     * If there is an error in the search query or a lack of results, show a popup to the user regarding what has
     * happened.
     * @param evt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String y = evt.getPropertyName();
        if (y.equals("failure")) {
            SearchState state = (SearchState) evt.getNewValue();
            JOptionPane.showMessageDialog(this, state.getSearchError());
            state.setSearchError(null);
        }
    }

}