package view;

import interface_adapter.apply.ApplyController;
import interface_adapter.apply.ApplyState;
import interface_adapter.apply.ApplyViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Applyview extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "apply";

    private final ApplyViewModel applyViewModel;
    private final JTextField usernameInputField = new JTextField(15);
    private final JTextField gpaInputField = new JTextField(15);

    private final ApplyController applyController;

    private final JButton submit;


    public Applyview(ApplyController controller, ApplyViewModel applyViewModel) {

        this.applyController = controller;
        this.applyViewModel = applyViewModel;
        applyViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(ApplyViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel(ApplyViewModel.USERNAME_LABEL), usernameInputField);
        LabelTextPanel gpaInfo = new LabelTextPanel(
                new JLabel(ApplyViewModel.GPA_LABEL), gpaInputField);


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
        this.add(usernameInfo);
        this.add(gpaInfo);
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
        ApplyController applyController = new ApplyController(applyViewModel);
        Applyview applyView = new Applyview(applyController, applyViewModel);

        JPanel testPanel = new JPanel();
        testPanel.add(applyView);

        application.add(testPanel);
        application.pack();
        application.setVisible(true);
    }

}
