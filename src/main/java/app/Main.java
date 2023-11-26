// Author: Common

package app;

import data_access.FileTokenDataAccessObject;
import data_access.ProfileDataAccessInterface;
import data_access.ServerProfileDataAccessObject;
import data_access.ServerUserDataAccessObject;
import entity.ExistingCommonUserFactory;
import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInController;
import interface_adapter.logged_in.LoggedInPresenter;
import interface_adapter.apply.ApplyController;
import interface_adapter.apply.ApplyViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.main_menu.MainMenuViewModel;
import interface_adapter.search.SearchViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.user_profiles.UserProfileController;
import interface_adapter.user_profiles.UserProfilePresenter;
import interface_adapter.user_profiles.UserProfileViewModel;
import interface_adapter.sub_menu.SubViewController;
import interface_adapter.sub_menu.SubViewModel;
import interface_adapter.sub_menu.SubViewPresenter;
import use_case.apply.ApplyInputBoundary;
import use_case.sub_menu.SubViewInputBoundary;
import use_case.sub_menu.SubViewInteractor;

import use_case.user_profile.UserProfileInteractor;
import view.*;

import javax.swing.*;
import java.awt.*;

public class Main {
    /**
     *
     * Order views are added in:
     * 1) MainMenuView
     * 2) LoginView
     * 3) SignupView
     * 4) LoggedInView
     * 5) UserProfileView
     * 6) SubView
     * 7) ApplyView
     */
    public static void main(String[] args) {
        JFrame application = new JFrame("Main Menu Test");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // The data for the views, such as username and password, are in the ViewModels.
        // This information will be changed by a presenter object that is reporting the
        // results from the use case. The ViewModels are observable, and will
        // be observed by the Views.

        // create the view models
        LoginViewModel loginViewModel = new LoginViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        MainMenuViewModel mainMenuViewModel1 = new MainMenuViewModel();
        SearchViewModel searchViewModel = new SearchViewModel();
        SubViewModel subViewModel = new SubViewModel();
        ApplyViewModel applyViewModel = new ApplyViewModel();

        // create the main menu view
        MainMenuView mainMenuView = MainMenuUseCaseFactory.create(mainMenuViewModel1, signupViewModel, loginViewModel, subViewModel, viewManagerModel);
        views.add(mainMenuView, mainMenuView.viewName);

        // create data access objects for this particular implementation
        ServerUserDataAccessObject userDataAccessObject = new ServerUserDataAccessObject(new ExistingCommonUserFactory());
        FileTokenDataAccessObject tokenDataAccessObject = new FileTokenDataAccessObject();
        ServerProfileDataAccessObject profileDataAccessObject = new ServerProfileDataAccessObject(tokenDataAccessObject);

        // add login, logged in and signup Views
        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject, mainMenuViewModel1, tokenDataAccessObject);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, mainMenuViewModel1,userDataAccessObject, tokenDataAccessObject);
        views.add(loginView, loginView.viewName);
        views.add(signupView, signupView.viewName);

        // create a UserProfileViewModel and view
        UserProfileViewModel userProfileViewModel = new UserProfileViewModel("userProfileView");
        UserProfilePresenter userProfilePresenter = new UserProfilePresenter(viewManagerModel, userProfileViewModel);
        UserProfileInteractor userProfileInteractor = new UserProfileInteractor(userProfilePresenter, profileDataAccessObject); // TODO: Determine if this casting is appropriate
        UserProfileController userProfileController = new UserProfileController(userProfileInteractor);
//        UserProfileView userProfileView = new UserProfileView(userProfileViewModel, userProfileController);
        UserProfileView userProfileView = UserProfileUseCaseFactory.create(viewManagerModel, userProfileViewModel, profileDataAccessObject);

        // add loggedin view
        LoggedInView loggedInView = LoggedInUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, userProfileViewModel, tokenDataAccessObject);
        views.add(loggedInView, loggedInView.viewName);
        assert userProfileView != null;
        views.add(userProfileView, userProfileView.viewName);

        // add subview
        SubViewPresenter subViewPresenter = new SubViewPresenter(searchViewModel, applyViewModel, viewManagerModel);

        SubViewInputBoundary subViewInteractor = new SubViewInteractor(subViewPresenter);
        SubViewController subViewController = new SubViewController(subViewInteractor);
        SubView subView = new SubView(subViewModel, subViewController);

        views.add(subView, subView.viewName);

        // add apply view
        ApplyInputBoundary applyUseCaseInteractor = null;
        ApplyController applyController = new ApplyController(applyUseCaseInteractor);
        Applyview applyView = new Applyview(applyController, applyViewModel);
        views.add(applyView, applyView.viewName);

        viewManagerModel.setActiveView(mainMenuView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
