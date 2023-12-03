// Author: Diego
package view;

import app.*;
import data_access.ApplyDataAccessObject;
import data_access.ResultsDataAccessObject;
import data_access.SearchDataAccessObject;
import data_access.ZipSearchDataAccessObject;
import entity.CommonUniversityFactory;
import entity.UniversityFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.apply.ApplyController;
import interface_adapter.apply.ApplyPresenter;
import interface_adapter.apply.ApplyViewModel;
import interface_adapter.main_menu.MainMenuViewModel;
import interface_adapter.results.ResultsViewModel;
import interface_adapter.search.SearchViewModel;
import interface_adapter.sub_menu.SubViewController;
import interface_adapter.sub_menu.SubViewModel;
import interface_adapter.sub_menu.SubViewState;
import interface_adapter.zip_search.ZipSearchViewModel;
import use_case.apply.ApplyDataAccessInterface;
import use_case.apply.ApplyInputBoundary;
import use_case.apply.ApplyInteractor;
import use_case.apply.ApplyOutputBoundary;
import use_case.results.ResultsUserDataAccessInterface;
import use_case.search.SearchUserDataAccessInterface;
import use_case.zip_search.ZipSearchUserDataAccessInterface;

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
    private final JButton zip_search;

    private final JButton back;
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
        zip_search = new JButton(SubViewModel.ZIP_SEARCH_BUTTON_LABEL);
        buttons.add(zip_search);
        back = new JButton("Back to Main Menu");
        buttons.add(back);


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

        zip_search.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(zip_search)) {
                            // System.out.println("ZipSearch Button pressed");
                            subViewController.execute("zip_search");
                        }
                    }
                }
        );

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                subViewController.execute("loggedInViewName"); // Replace with your logged-in view name
            }
        });

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
}
