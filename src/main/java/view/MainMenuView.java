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

        JPanel buttons = new JPanel();
        // access static member using class
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

        this.add(title);
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
    }

}