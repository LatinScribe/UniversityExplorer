// Author: Diego
package view;

import app.ResultsUseCaseFactory;
import app.SearchUseCaseFactory;
import data_access.ResultsDataAccessObject;
import data_access.SearchDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.apply.ApplyViewModel;
import interface_adapter.results.ResultsViewModel;
import interface_adapter.search.SearchController;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;
import interface_adapter.sub_menu.SubViewController;
import interface_adapter.sub_menu.SubViewModel;
import interface_adapter.sub_menu.SubViewPresenter;
import interface_adapter.zip_search.ZipSearchController;
import interface_adapter.zip_search.ZipSearchState;
import interface_adapter.zip_search.ZipSearchViewModel;
import use_case.results.ResultsUserDataAccessInterface;
import use_case.search.SearchUserDataAccessInterface;
import use_case.sub_menu.SubViewInputBoundary;
import use_case.sub_menu.SubViewInteractor;
import use_case.zip_search.ZipSearchInputBoundary;
import use_case.zip_search.ZipSearchInteractor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ZipSearchView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "zip_search";
    private final ZipSearchViewModel zipSearchViewModel;
    private final JTextField searchInputField = new JTextField(15);
    private final ZipSearchController zipSearchController;

    private final JButton search;
    private final JButton back;

    public ZipSearchView(ZipSearchController zipSearchController, ZipSearchViewModel zipSearchViewModel) {

        this.zipSearchController = zipSearchController;
        this.zipSearchViewModel = zipSearchViewModel;
        zipSearchViewModel.addPropertyChangeListener(this);

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
                            SearchState currentState = zipSearchViewModel.getState();

                            zipSearchController.executeSearch(
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
                            zipSearchController.executeBack();
                        }
                    }
                }
        );

        searchInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SearchState currentState = zipSearchViewModel.getState();
                        String text = searchInputField.getText() + e.getKeyChar();
                        currentState.setSearchCriteria(text);
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
            ZipSearchState state = (ZipSearchState) evt.getNewValue();
           // JOptionPane.showMessageDialog(this, state.getSearchError());
           // state.setSearchError(null);
        } else {
            SearchState state = (SearchState) evt.getNewValue();
        }
    }

    public static void main(String[] args) {
        JFrame application = new JFrame("Zip Search Test");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);
        ZipSearchViewModel zipSearchViewModel = new ZipSearchViewModel();
        //SearchUserDataAccessInterface searchDataAccessObject = new SearchDataAccessObject();
        SubViewModel subViewModel = new SubViewModel();
        ResultsViewModel resultsViewModel = new ResultsViewModel();
        ZipSearchInputBoundary zipSearchInteractor = new ZipSearchInteractor();
        ZipSearchController zipSearchController = new ZipSearchController(zipSearchInteractor);
        ZipSearchView zipSearchView = new ZipSearchView(zipSearchController, zipSearchViewModel);
        //SubViewPresenter subViewPresenter = new SubViewPresenter(searchViewModel, applyViewModel, viewManagerModel);
       // SubViewInputBoundary subViewInteractor = new SubViewInteractor(subViewPresenter);
        //SubViewController subViewController = new SubViewController(subViewInteractor);
        //SubView subView = new SubView(subViewModel, subViewController);

        ResultsUserDataAccessInterface resultsUserDataAccessInterface = new ResultsDataAccessObject();
        //ResultsView resultsView = ResultsUseCaseFactory.create(viewManagerModel, resultsViewModel, searchViewModel, resultsUserDataAccessInterface);

        views.add(zipSearchView, zipSearchView.viewName);
        //views.add(subView, subView.viewName);
        //views.add(resultsView, resultsView.viewName);

        viewManagerModel.setActiveView(zipSearchView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
