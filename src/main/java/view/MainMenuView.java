package view;

import interface_adapter.main_menu.MainMenuController;
import interface_adapter.main_menu.MainMenuViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * View shown to the user as the main menu of the program
 * This should be the first view the user sees when the program is run
 *
 * @author Henry
 */
public class MainMenuView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "Main Menu";
    private final MainMenuViewModel mainMenuViewModel;
    final JButton signUp;
    final JButton continueAsGuest;
    final JButton signIn;
    final JButton quit;
    private final MainMenuController mainMenuController;

    public MainMenuView(MainMenuViewModel mainMenuViewModel, MainMenuController mainMenuController) {
        this.mainMenuController = mainMenuController;
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
        quit = new JButton(MainMenuViewModel.QUIT_BUTTON_LABEL);
        buttons.add(quit);

        // add action listeners
        signUp.addActionListener(       // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(signUp)) {
                            mainMenuController.switchToSignupView();
                        }
                    }
                }
        );
        continueAsGuest.addActionListener(  // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(continueAsGuest)) {
                            System.out.println("Continue as Guest Button pressed");
                            mainMenuController.switchToSubView();
                        }
                    }
                }
        );

        signIn.addActionListener(       // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(signIn)) {
                            mainMenuController.switchToLoginView();
                        }
                    }
                }
        );
//        settings.addActionListener(        // This creates an anonymous subclass of ActionListener and instantiates it.
//                new ActionListener() {
//                    public void actionPerformed(ActionEvent evt) {
//                        if (evt.getSource().equals(settings)) {
//                            System.out.println("SignIn Button pressed");
//                        }
//                    }
//                }
//        );
        // Other way of doing it, which is using the default action performed in the class
        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(quit)) {
                    System.out.println("Closing program");
                    System.exit(0);
                }
            }
        });


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
//        MainMenuState state = (MainMenuState) evt.getNewValue();
    }

}