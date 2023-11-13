// Author: Bora
package view;

import interface_adapter.apply.ApplyController;
import interface_adapter.apply.ApplyState;
import interface_adapter.apply.ApplyViewModel;
import use_case.apply.ApplyInputBoundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Applyview extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "apply";

    private final ApplyViewModel applyViewModel;
    private final JTextField actInputField = new JTextField(15);
    private final JTextField satInputField = new JTextField(15);

    private final ApplyController applyController;

    private final JButton submit;


    public Applyview(ApplyController controller, ApplyViewModel applyViewModel) {

        this.applyController = controller;
        this.applyViewModel = applyViewModel;
        applyViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(ApplyViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel actInfo = new LabelTextPanel(
                new JLabel(ApplyViewModel.ACT_LABEL), actInputField);
        LabelTextPanel satInfo = new LabelTextPanel(
                new JLabel(ApplyViewModel.SAT_LABEL), satInputField);


        JPanel buttons = new JPanel();
        submit = new JButton(ApplyViewModel.SUBMIT_BUTTON_LABEL);
        buttons.add(submit);
        submit.addActionListener(new ActionListener() {
                                     @Override
                                     public void actionPerformed(ActionEvent evt) {
                                         if (evt.getSource().equals(submit)) {
                                             System.out.println("submit Button pressed");
                                             applyController.execute();
                                         }
                                     }
                                 });



        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(actInfo);
        this.add(satInfo);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        // Handle any other actions or button clicks as needed
        if (evt.getSource().equals(submit)) {
            // This code will be executed when the "Submit" button is clicked
            System.out.println("Submit Button pressed");
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Handle property changes if needed
        if (evt.getPropertyName().equals("state") && evt.getNewValue() instanceof ApplyState) {
            ApplyState state = (ApplyState) evt.getNewValue();
            // Handle state changes here
            // For example, you can update the view based on the new state
            // You can access the ApplyViewModel and update the UI components
            // with the latest state information
        }
    }
    public static void main(String[] args) {
        JFrame application = new JFrame("ApplyView Test");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        ApplyViewModel applyViewModel = new ApplyViewModel();
        ApplyInputBoundary applyUseCaseInteractor = null;
        ApplyController applyController = new ApplyController(applyViewModel, applyUseCaseInteractor);
        Applyview applyView = new Applyview(applyController, applyViewModel);

        JPanel testPanel = new JPanel();
        testPanel.add(applyView);

        application.add(testPanel);
        application.pack();
        application.setVisible(true);
    }

}
