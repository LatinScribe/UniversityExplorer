// Author: Henry
package view;

import interface_adapter.main_menu.MainMenuViewModel;
import interface_adapter.main_menu.MainMenuState;
import interface_adapter.main_menu.MainMenuController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainMenuView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "Main Menu";
    private final MainMenuViewModel mainMenuViewModel;

//    final JTextField usernameInputField = new JTextField(15);
//    private final JLabel usernameErrorField = new JLabel();
//
//    final JPasswordField passwordInputField = new JPasswordField(15);
//    private final JLabel passwordErrorField = new JLabel();

    final JButton signUp;
    final JButton continueAsGuest;

    final JButton signIn;

    final JButton settings;
    private final MainMenuController mainMenuController;

    public MainMenuView(MainMenuViewModel mainMenuViewModel, MainMenuController controller) {

        this.mainMenuController = controller;
        this.mainMenuViewModel = mainMenuViewModel;
        this.mainMenuViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Main Menu Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

//        LabelTextPanel usernameInfo = new LabelTextPanel(
//                new JLabel("Username"), usernameInputField);
//        LabelTextPanel passwordInfo = new LabelTextPanel(
//                new JLabel("Password"), passwordInputField);

        JPanel buttons = new JPanel();
        // TODO: check mainMenuViewModel vs MainMenuViewModel
        signUp = new JButton(MainMenuViewModel.SIGNUP_BUTTON_LABEL);
        buttons.add(signUp);
        continueAsGuest = new JButton(MainMenuViewModel.GUEST_BUTTON_LABEL);
        buttons.add(continueAsGuest);
        signIn = new JButton(MainMenuViewModel.SIGNIN_BUTTON_LABEL);
        buttons.add(signIn);
        settings = new JButton(MainMenuViewModel.SETTINGS_BUTTON_LABEL);
        buttons.add(settings);

//        logIn.addActionListener(                // This creates an anonymous subclass of ActionListener and instantiates it.
//                new ActionListener() {
//                    public void actionPerformed(ActionEvent evt) {
//                        if (evt.getSource().equals(logIn)) {
//                            MainMenuState currentState = mainMenuViewModel.getState();
//
//                            mainMenuController.execute(
//                                    currentState.getUsername(),
//                                    currentState.getPassword()
//                            );
//                        }
//                    }
//                }
//        );
//
//        cancel.addActionListener(this);

//        usernameInputField.addKeyListener(new KeyListener() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//                MainMenuState currentState = mainMenuViewModel.getState();
//                currentState.setUsername(usernameInputField.getText() + e.getKeyChar());
//                mainMenuViewModel.setState(currentState);
//            }
//
//            @Override
//            public void keyPressed(KeyEvent e) {
//            }
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//            }
//        });
//        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//
//        passwordInputField.addKeyListener(
//                new KeyListener() {
//                    @Override
//                    public void keyTyped(KeyEvent e) {
//                        MainMenuState currentState = mainMenuViewModel.getState();
//                        currentState.setPassword(passwordInputField.getText() + e.getKeyChar());
//                        mainMenuViewModel.setState(currentState);
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

        this.add(title);
//        this.add(usernameInfo);
//        this.add(usernameErrorField);
//        this.add(passwordInfo);
//        this.add(passwordErrorField);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        MainMenuState state = (MainMenuState) evt.getNewValue();
        // setFields(state);
    }

//    private void setFields(MainMenuState state) {
//        usernameInputField.setText(state.getUsername());
//    }

}