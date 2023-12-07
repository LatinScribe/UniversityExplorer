package view;

import interface_adapter.sub_menu.SubViewController;
import interface_adapter.sub_menu.SubViewModel;
import interface_adapter.sub_menu.SubViewState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The subview class that contains the recommendation, search, zip search,
 * and the back button, allowing each use case to be performed.
 * @author Diego
 */
public class SubView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "sub menu";
    private final JButton recommendation;
    private final JButton search;
    private final JButton zip_search;

    private final JButton back;
    private final SubViewModel subViewModel;
    private final SubViewController subViewController;

    /**
     * Instantiates the SubView.
     * @param subViewModel The View Model used for displaying the text in the view
     * @param controller The Controller used for making the buttons work
     */
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
                    /**
                     * When the recommendation button is pressed, call the execute method
                     * of the subviewController.
                     * @param evt the event to be processed
                     */
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
                    /**
                     * When the search button is pressed, call the execute method
                     * of the subviewController.
                     * @param evt the event to be processed
                     */
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
                    /**
                     * When the zip search button is pressed, call the execute method
                     * of the subviewController.
                     * @param evt the event to be processed
                     */
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(zip_search)) {
                            // System.out.println("ZipSearch Button pressed");
                            subViewController.execute("zip_search");
                        }
                    }
                }
        );

        back.addActionListener(new ActionListener() {
            /**
             * When the back button is pressed, call the execute method
             * of the subviewController.
             * @param evt the event to be processed
             */
            public void actionPerformed(ActionEvent evt) {
                subViewController.execute("loggedInViewName"); // Replace with your logged-in view name
            }
        });

        this.add(title);
        this.add(buttons);
    }

    /**
     * The action performed meant to be overridden.
     * @param evt the event to be processed
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    /**
     * If there is an error in the subview, show a popup to
     * the user regarding what has happened.
     * @param evt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SubViewState state = (SubViewState) evt.getNewValue();
        // setFields(state);
    }
}
