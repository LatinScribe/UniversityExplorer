package view;

import interface_adapter.apply.ApplyController;
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


//        submit.addActionListener(
//                // This creates an anonymous subclass of ActionListener and instantiates it.
//                new ActionListener() {
//                    public void actionPerformed(ActionEvent evt) {
//                        if (evt.getSource().equals(signUp)) {
//                            SignupState currentState = signupViewModel.getState();
//
//                            signupController.execute(
//                                    currentState.getUsername(),
//                                    currentState.getPassword(),
//                                    currentState.getRepeatPassword()
//                            );
//                        }
//                    }
//                }
//        );
//
//        clear.addActionListener(
//                new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        if (e.getSource().equals(clear)) {
//                            clearController.execute();
//                        }
//                    }
//                }
//        );

//
//
//        // This makes a new KeyListener implementing class, instantiates it, and
//        // makes it listen to keystrokes in the usernameInputField.
//        //
//        // Notice how it has access to instance variables in the enclosing class!
//        usernameInputField.addKeyListener(
//                new KeyListener() {
//                    @Override
//                    public void keyTyped(KeyEvent e) {
//                        SignupState currentState = signupViewModel.getState();
//                        String text = usernameInputField.getText() + e.getKeyChar();
//                        currentState.setUsername(text);
//                        signupViewModel.setState(currentState);
//                    }
//
//                    @Override
//                    public void keyPressed(KeyEvent e) {
//                    }
//
//                    @Override
//                    public void keyReleased(KeyEvent e) {
//                    }
//                });
//
//        passwordInputField.addKeyListener(
//                new KeyListener() {
//                    @Override
//                    public void keyTyped(KeyEvent e) {
//                        SignupState currentState = signupViewModel.getState();
//                        currentState.setPassword(passwordInputField.getText() + e.getKeyChar());
//                        signupViewModel.setState(currentState);
//                    }
//
//                    @Override
//                    public void keyPressed(KeyEvent e) {
//
//                    }
//
//                    @Override
//                    public void keyReleased(KeyEvent e) {
//
//                    }
//                }
//        );
//
//        repeatPasswordInputField.addKeyListener(
//                new KeyListener() {
//                    @Override
//                    public void keyTyped(KeyEvent e) {
//                        SignupState currentState = signupViewModel.getState();
//                        currentState.setRepeatPassword(repeatPasswordInputField.getText() + e.getKeyChar());
//                        signupViewModel.setState(currentState); // Hmm, is this necessary?
//                    }
//
//                    @Override
//                    public void keyPressed(KeyEvent e) {
//
//                    }
//
//                    @Override
//                    public void keyReleased(KeyEvent e) {
//
//                    }
//                }
//        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(gpaInfo);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Cancel not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }


//    @Override
//    public void propertyChange(PropertyChangeEvent evt) {
//        if (evt.getNewValue() instanceof ClearState) {
//            ClearState state = (ClearState) evt.getNewValue();
//            JOptionPane.showMessageDialog(this, state.getDeletedUserString());
//        }
//        else if (evt.getNewValue() instanceof SignupState) {
//            SignupState state = (SignupState) evt.getNewValue();
//            if (state.getUsernameError() != null) {
//                JOptionPane.showMessageDialog(this, state.getUsernameError());
//            }
//        }
//    }
}
