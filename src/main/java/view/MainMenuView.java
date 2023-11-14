// Author: Henry
package view;

import app.LoginUseCaseFactory;
import app.MainMenuUseCaseFactory;
import app.SignupUseCaseFactory;
import data_access.ServerUserDataAccessObject;
import entity.ExistingCommonUserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.main_menu.MainMenuViewModel;
import interface_adapter.main_menu.MainMenuState;
import interface_adapter.main_menu.MainMenuController;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.sub_menu.SubViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public MainMenuView(MainMenuViewModel mainMenuViewModel, MainMenuController mainMenuController) {
        this.mainMenuController = mainMenuController;;
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
        settings.addActionListener(this);



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

    public static void main(String[] args) {
        JFrame application = new JFrame("Main Menu Test");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing
        // The data for the views, such as username and password, are in the ViewModels.
        // This information will be changed by a presenter object that is reporting the
        // results from the use case. The ViewModels are observable, and will
        // be observed by the Views.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // create the view models
        LoginViewModel loginViewModel = new LoginViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        MainMenuViewModel mainMenuViewModel1 = new MainMenuViewModel();
        SubViewModel subViewModel = new SubViewModel();

        // create the main menu view
        MainMenuView mainMenuView = MainMenuUseCaseFactory.create(mainMenuViewModel1, signupViewModel, loginViewModel, subViewModel,viewManagerModel);
        views.add(mainMenuView, mainMenuView.viewName);

        // add login, logged in and signup Views
        ServerUserDataAccessObject userDataAccessObject = new ServerUserDataAccessObject(new ExistingCommonUserFactory());

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject, mainMenuViewModel1);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, mainMenuViewModel1,userDataAccessObject);
        views.add(loginView, loginView.viewName);

        LoggedInView loggedInView = new LoggedInView(loggedInViewModel);
        views.add(loggedInView, loggedInView.viewName);

        viewManagerModel.setActiveView(mainMenuView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);

    }

}