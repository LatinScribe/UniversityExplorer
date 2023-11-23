// Author: Diego
package view;

import app.ApplyUseCaseFactory;
import app.SearchUseCaseFactory;
import data_access.ApplyDataAccessObject;
import data_access.SearchDataAccessObject;
import entity.CommonUniversityFactory;
import entity.UniversityFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.apply.ApplyController;
import interface_adapter.apply.ApplyPresenter;
import interface_adapter.apply.ApplyViewModel;
import interface_adapter.search.SearchViewModel;
import interface_adapter.sub_menu.SubViewController;
import interface_adapter.sub_menu.SubViewModel;
import interface_adapter.sub_menu.SubViewPresenter;
import interface_adapter.sub_menu.SubViewState;
import use_case.apply.ApplyDataAccessInterface;
import use_case.apply.ApplyInputBoundary;
import use_case.apply.ApplyInteractor;
import use_case.apply.ApplyOutputBoundary;
import use_case.search.SearchUserDataAccessInterface;
import use_case.sub_menu.SubViewInputBoundary;
import use_case.sub_menu.SubViewInteractor;

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


    public SubView(SubViewModel subViewModel, SubViewController controller) {
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


        recommendation.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(recommendation)) {
                            // System.out.println("Recommendation Button pressed");
                            subViewController.execute("recommendation");
                        }
                    }
                }
        );

        search.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(search)) {
                            // System.out.println("Search Button pressed");
                            subViewController.execute("search");
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

        SubViewInputBoundary subViewInteractor = new SubViewInteractor(subViewPresenter);
        SubViewController subViewController = new SubViewController(subViewInteractor);
        SubView subView = new SubView(subViewModel, subViewController);

        views.add(subView, subView.viewName);

        // add apply and search Views
        SearchUserDataAccessInterface searchUserDataAccessInterface = new SearchDataAccessObject();
        // ServerUserDataAccessObject userDataAccessObject = new ServerUserDataAccessObject(new ExistingCommonUserFactory());
        SearchView searchView = SearchUseCaseFactory.create(viewManagerModel, searchViewModel, subViewModel, searchUserDataAccessInterface);
        views.add(searchView, searchView.viewName);
        ApplyOutputBoundary applyPresenter = new ApplyPresenter(applyViewModel,viewManagerModel);
        ApplyDataAccessInterface applyDataAccessInterface = new ApplyDataAccessObject();
        UniversityFactory universityFactory = new CommonUniversityFactory();
        ApplyInputBoundary applyUseCaseInteractor = new ApplyInteractor(applyDataAccessInterface,applyPresenter,universityFactory);
        ApplyController applyController = new ApplyController(applyUseCaseInteractor);
        // Applyview applyView = new Applyview(applyController, applyViewModel);
        ApplyDataAccessInterface applyDataAccessInterface1 = new ApplyDataAccessObject();
        Applyview applyView1 = ApplyUseCaseFactory.create(viewManagerModel, applyViewModel, applyDataAccessInterface1, universityFactory);

        views.add(applyView1, applyView1.viewName);

        viewManagerModel.setActiveView(subView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
