// Author: Diego
package view;

import app.ResultsUseCaseFactory;
import app.ZipSearchUseCaseFactory;
import data_access.ResultsDataAccessObject;
import data_access.ZipSearchDataAccessObject;
import entity.CommonUniversityFactory;
import entity.UniversityFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.results.ResultsViewModel;
import interface_adapter.sub_menu.SubViewModel;
import interface_adapter.zip_search.ZipSearchController;
import interface_adapter.zip_search.ZipSearchPresenter;
import interface_adapter.zip_search.ZipSearchState;
import interface_adapter.zip_search.ZipSearchViewModel;
import use_case.results.ResultsUserDataAccessInterface;
import use_case.zip_search.ZipSearchInputBoundary;
import use_case.zip_search.ZipSearchInteractor;
import use_case.zip_search.ZipSearchOutputBoundary;
import use_case.zip_search.ZipSearchUserDataAccessInterface;

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
    private final JPasswordField zipcodeInputField = new JPasswordField(15);
    private final JPasswordField radiusInputField = new JPasswordField(15);
    private final ZipSearchController zipSearchController;

    private final JButton search;
    private final JButton back;

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
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(search)) {
                            System.out.println("Search pressed!");
                            ZipSearchState currentState = zipSearchViewModel.getState();

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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String y = evt.getPropertyName();
        if (y.equals("failure")){
            ZipSearchState state = (ZipSearchState) evt.getNewValue();
            JOptionPane.showMessageDialog(this, state.getZipSearchError());
            state.setZipSearchError(null);
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
        ZipSearchUserDataAccessInterface zipSearchDataAccessObject = new ZipSearchDataAccessObject();
        SubViewModel subViewModel = new SubViewModel();
        ResultsViewModel resultsViewModel = new ResultsViewModel();
        ZipSearchView zipSearchView = ZipSearchUseCaseFactory.create(viewManagerModel, zipSearchViewModel, subViewModel, resultsViewModel, zipSearchDataAccessObject);
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
