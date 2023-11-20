// Author: Andre, a test

package view;

import app.SearchUseCaseFactory;
import data_access.SearchDataAccessObject;
import entity.CommonUniversityFactory;
import entity.UniversityFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.search.SearchController;
import interface_adapter.search.SearchViewModel;
import interface_adapter.search.SearchState;
import interface_adapter.sub_menu.SubViewModel;
import use_case.search.SearchUserDataAccessInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SearchView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "search";

    private final SearchViewModel searchViewModel;
    private final JTextField searchInputField = new JTextField(15);
    private final SearchController searchController;

    private final JButton search;
    private final JButton back;

    public SearchView(SearchController searchController, SearchViewModel searchViewModel) {

        this.searchController = searchController;
        this.searchViewModel = searchViewModel;
        searchViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(SearchViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel searchInfo = new LabelTextPanel(
                new JLabel(SearchViewModel.SEARCH_BUTTON_LABEL), searchInputField);

        JPanel buttons = new JPanel();
        search = new JButton(SearchViewModel.SEARCH_BUTTON_LABEL);
        buttons.add(search);
        back = new JButton(SearchViewModel.BACK_BUTTON_LABEL);
        buttons.add(back);

        search.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
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
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(back)) {
                            System.out.println("Back pressed");
                            SearchState currentState = searchViewModel.getState();
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
                        currentState.setSearchCriteria(text);
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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String y = evt.getPropertyName();
        if (y.equals("state")) {
            // Results View Not implemented yet, will be implemented soon.
            SearchState state = (SearchState) evt.getNewValue();
            JOptionPane.showMessageDialog(this, state.getUniversities());
        } else {
            SearchState state = (SearchState) evt.getNewValue();
            JOptionPane.showMessageDialog(this, state.getSearchError());
            state.setSearchError(null);
        }
    }

    public static void main(String[] args) {
        JFrame application = new JFrame("Search Test");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SearchViewModel searchViewModel = new SearchViewModel();
        SearchUserDataAccessInterface searchDataAccessObject = new SearchDataAccessObject();
        SubViewModel subViewModel = new SubViewModel();
        SearchView searchView = SearchUseCaseFactory.create(viewManagerModel, searchViewModel, subViewModel, searchDataAccessObject);

        views.add(searchView, searchView.viewName);

        application.pack();
        application.setVisible(true);
    }
}