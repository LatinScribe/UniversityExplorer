// Author: Andre, a test

package view;

import app.ResultsUseCaseFactory;
import app.SearchUseCaseFactory;
import data_access.ResultsDataAccessObject;
import data_access.SearchDataAccessObject;
import entity.CommonUniversityFactory;
import entity.UniversityFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.apply.ApplyViewModel;
import interface_adapter.results.ResultsViewModel;
import interface_adapter.search.SearchController;
import interface_adapter.search.SearchViewModel;
import interface_adapter.search.SearchState;
import interface_adapter.sub_menu.SubViewController;
import interface_adapter.sub_menu.SubViewModel;
import interface_adapter.sub_menu.SubViewPresenter;
import org.json.JSONObject;
import use_case.results.ResultsUserDataAccessInterface;
import use_case.search.SearchUserDataAccessInterface;
import use_case.sub_menu.SubViewInputBoundary;
import use_case.sub_menu.SubViewInteractor;

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
                new JLabel("Type in the name of a university here:"), searchInputField);

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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String y = evt.getPropertyName();
        if (y.equals("failure")){
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
        new ViewManager(views, cardLayout, viewManagerModel);
        SearchViewModel searchViewModel = new SearchViewModel();
        SearchUserDataAccessInterface searchDataAccessObject = new SearchDataAccessObject();
        SubViewModel subViewModel = new SubViewModel();
        ResultsViewModel resultsViewModel = new ResultsViewModel();
        SearchView searchView = SearchUseCaseFactory.create(viewManagerModel, searchViewModel, subViewModel, resultsViewModel, searchDataAccessObject);

        ApplyViewModel applyViewModel = new ApplyViewModel();
        SubViewPresenter subViewPresenter = new SubViewPresenter(searchViewModel, applyViewModel, viewManagerModel);
        SubViewInputBoundary subViewInteractor = new SubViewInteractor(subViewPresenter);
        SubViewController subViewController = new SubViewController(subViewInteractor);
        SubView subView = new SubView(subViewModel, subViewController);

        ResultsUserDataAccessInterface resultsUserDataAccessInterface = new ResultsDataAccessObject();
        ResultsView resultsView = ResultsUseCaseFactory.create(viewManagerModel, resultsViewModel, searchViewModel, resultsUserDataAccessInterface);

        views.add(searchView, searchView.viewName);
        views.add(subView, subView.viewName);
        views.add(resultsView, resultsView.viewName);

        viewManagerModel.setActiveView(searchView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}