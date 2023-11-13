// Author: Diego
package view;

import app.SearchUseCaseFactory;
import data_access.ServerUserDataAccessObject;
import entity.ExistingCommonUserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.apply.ApplyController;
import interface_adapter.apply.ApplyViewModel;
import interface_adapter.search.SearchViewModel;
import interface_adapter.sub_menu.SubViewController;
import interface_adapter.sub_menu.SubViewModel;
import interface_adapter.sub_menu.SubViewPresenter;
import interface_adapter.sub_menu.SubViewState;
import org.json.JSONObject;
import use_case.apply.ApplyInputBoundary;
import use_case.search.SearchUserDataAccessInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SubView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "sub menu";
    private final JButton recommendation;
    private final JButton search;
    private final SubViewModel subViewModel;
    private final SubViewController subViewController;

    private final SubViewPresenter subViewPresenter;

    public SubView(SubViewModel subViewModel, SubViewController controller, SubViewPresenter subViewPresenter) {
        this.subViewController = controller;
        this.subViewModel = subViewModel;
        this.subViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(SubViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        recommendation = new JButton(SubViewModel.RECOMMENDATION_BUTTON_LABEL);
        buttons.add(recommendation);
        search = new JButton(SubViewModel.SEARCH_BUTTON_LABEL);
        buttons.add(search);

        // for testing: parameter controller
        this.subViewPresenter = subViewPresenter;

        recommendation.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(recommendation)) {
                            // System.out.println("Recommendation Button pressed");
                            subViewPresenter.prepareApplyView();
                        }
                    }
                }
        );

        search.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(search)) {
                            // System.out.println("Search Button pressed");
                            subViewPresenter.prepareSearchView();
                        }
                    }
                }
        );

        this.add(title);
        this.add(buttons);
    }

    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SubViewState state = (SubViewState) evt.getNewValue();
        // setFields(state);
    }

    public static void main(String[] args) {
        JFrame application = new JFrame("Sub menu Test");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        SearchViewModel searchViewModel = new SearchViewModel();
        ApplyViewModel applyViewModel = new ApplyViewModel();

        SubViewModel subViewModel = new SubViewModel();
        SubViewPresenter subViewPresenter = new SubViewPresenter(searchViewModel, applyViewModel, viewManagerModel);

        SubViewController subViewController = new SubViewController();
        SubView subView = new SubView(subViewModel, subViewController, subViewPresenter);

        views.add(subView, subView.viewName);

        // add apply and search Views
        ServerUserDataAccessObject userDataAccessObject = new ServerUserDataAccessObject(new ExistingCommonUserFactory());
        SearchUserDataAccessInterface userSearchData = new SearchUserDataAccessInterface() {
            @Override
            public JSONObject basicQuery(String queryParameters, String optionalParameters) {
                return null;
            }
        };

        SearchView searchView = SearchUseCaseFactory.create(viewManagerModel, searchViewModel, userSearchData);
        assert searchView != null;
        views.add(searchView, searchView.viewName);
        ApplyInputBoundary applyUseCaseInteractor = null;
        ApplyController applyController = new ApplyController(applyViewModel, applyUseCaseInteractor);
        Applyview applyView = new Applyview(applyController, applyViewModel);
        views.add(applyView, applyView.viewName);

        viewManagerModel.setActiveView(subView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
