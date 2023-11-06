// Author: Diego
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

public class SubView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "sub menu";
    private final JButton recommendation;
    private final JButton search;
    private final SubViewModel subViewModel;
    private final SubViewController subcontroller;

    public SubView(SubViewModel subViewModel, SubViewController controller) {
        this.subcontroller = controller;
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
                            System.out.println("Recommendation Button pressed");
                        }
                    }
                }
        );

        search.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(search)) {
                            System.out.println("Search Button pressed");
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

        SubViewModel subViewModel = new SubViewModel();
        SubViewController subViewController = new SubViewController();
        SubView subView = new SubView(subViewModel, subViewController);

        views.add(subView, subView.viewName);

        application.pack();;
        application.setVisible(true);
    }
}
