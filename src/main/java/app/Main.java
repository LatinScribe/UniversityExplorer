package app;


import data_access.*;
import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.apply.ApplyViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.main_menu.MainMenuViewModel;
import interface_adapter.prefapply.PrefApplyViewModel;
import interface_adapter.results.ResultsViewModel;
import interface_adapter.search.SearchViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.sub_menu.SubViewController;
import interface_adapter.sub_menu.SubViewModel;
import interface_adapter.sub_menu.SubViewPresenter;
import interface_adapter.user_profiles.UserProfileController;
import interface_adapter.user_profiles.UserProfilePresenter;
import interface_adapter.user_profiles.UserProfileViewModel;
import interface_adapter.zip_search.ZipSearchViewModel;
import use_case.apply.ApplyDataAccessInterface;
import use_case.prefapply.PrefApplyDataAccessInterface;
import use_case.results.ResultsUserDataAccessInterface;
import use_case.search.SearchUserDataAccessInterface;
import use_case.sub_menu.SubViewInputBoundary;
import use_case.sub_menu.SubViewInteractor;
import use_case.user_profile.UserProfileInteractor;
import use_case.zip_search.ZipSearchUserDataAccessInterface;
import view.*;

import javax.swing.*;
import java.awt.*;

public class Main {
    /**
     * Run this file to run our program!
     * No parameters needed.
     * <p>
     * Order views are added in:
     * 1) MainMenuView
     * 2) LoginView
     * 3) SignupView
     * 4) LoggedInView
     * 5) UserProfileView
     * 6) SubView
     * 7) ApplyView
     * 8) SearchView
     * 9) ZipSearchView
     * 10) ResultsView
     * 11) PrefApplyView
     *
     * @author Common
     */
    public static void main(String[] args) {
        JFrame application = new JFrame("UniversityExplorer");
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
        SubViewModel subViewModel = new SubViewModel();
        ApplyViewModel applyViewModel = new ApplyViewModel();
        SearchViewModel searchViewModel = new SearchViewModel();
        ZipSearchViewModel zipSearchViewModel = new ZipSearchViewModel();
        ResultsViewModel resultsViewModel = new ResultsViewModel();
        PrefApplyViewModel prefapplyViewModel = new PrefApplyViewModel();

        // create the main menu view
        MainMenuView mainMenuView = MainMenuUseCaseFactory.create(mainMenuViewModel1, signupViewModel, loginViewModel, subViewModel, viewManagerModel);
        views.add(mainMenuView, mainMenuView.viewName);

        // create data access objects for this particular implementation
        ServerUserDataAccessObject userDataAccessObject = new ServerUserDataAccessObject(new ExistingCommonUserFactory());
        FileTokenDataAccessObject tokenDataAccessObject = new FileTokenDataAccessObject();
        UserPreferencesFactory userPreferencesFactory = new UserPreferencesFactory();
        ServerProfileDataAccessObject profileDataAccessObject = new ServerProfileDataAccessObject(tokenDataAccessObject, userPreferencesFactory);
        PasswordValidatorService passwordValidatorService = new PasswordValidatorService();
        UsernameValidatorService usernameValidatorService = new UsernameValidatorService();

        // add login, logged in and signup Views
        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject, mainMenuViewModel1, tokenDataAccessObject, passwordValidatorService, usernameValidatorService);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, mainMenuViewModel1, userDataAccessObject, tokenDataAccessObject);
        views.add(loginView, loginView.viewName);
        views.add(signupView, signupView.viewName);

        // create a UserProfileViewModel and view
        UserProfileViewModel userProfileViewModel = new UserProfileViewModel("userProfileView");
        UserProfilePresenter userProfilePresenter = new UserProfilePresenter(viewManagerModel, userProfileViewModel, loggedInViewModel);
        UserProfileInteractor userProfileInteractor = new UserProfileInteractor(userProfilePresenter, profileDataAccessObject); // TODO: Determine if this casting is appropriate
        UserProfileController userProfileController = new UserProfileController(userProfileInteractor);
        UserProfileView userProfileView = UserProfileUseCaseFactory.create(viewManagerModel, userProfileViewModel, profileDataAccessObject, loggedInViewModel);

        // add loggedin view
        LoggedInView loggedInView = LoggedInUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, userProfileViewModel, prefapplyViewModel, searchViewModel, tokenDataAccessObject);
        views.add(loggedInView, loggedInView.viewName);
        assert userProfileView != null;
        views.add(userProfileView, userProfileView.viewName);

        // add subview
        SubViewPresenter subViewPresenter = new SubViewPresenter(searchViewModel, applyViewModel, zipSearchViewModel, mainMenuViewModel1, viewManagerModel);

        SubViewInputBoundary subViewInteractor = new SubViewInteractor(subViewPresenter);
        SubViewController subViewController = new SubViewController(subViewInteractor);
        SubView subView = new SubView(subViewModel, subViewController);

        views.add(subView, subView.viewName);

        // add apply view
        ApplyDataAccessInterface applyUserDataAccessObject = new ApplyDataAccessObject();
        UniversityFactory shortUniversityFactory = new CommonUniversityFactory();
        Applyview applyView = ApplyUseCaseFactory.create(viewManagerModel, applyViewModel, applyUserDataAccessObject, shortUniversityFactory, subViewModel);
        //ApplyController applyController = new ApplyController( applyUseCaseInteractor);
        //Applyview applyView = new Applyview(applyController, applyViewModel);

        views.add(applyView, applyView.viewName);

        // add search view
        SearchUserDataAccessInterface searchUserDataAccessInterface = new SearchDataAccessObject();
        SearchView searchView = SearchUseCaseFactory.create(viewManagerModel, searchViewModel, subViewModel, resultsViewModel, loggedInViewModel, searchUserDataAccessInterface);

        views.add(searchView, searchView.viewName);

        // add zip search view
        ZipSearchUserDataAccessInterface zipSearchDataAccessObject = new ZipSearchDataAccessObject();
        ZipSearchView zipSearchView = ZipSearchUseCaseFactory.create(viewManagerModel, zipSearchViewModel, subViewModel, resultsViewModel, zipSearchDataAccessObject);

        views.add(zipSearchView, zipSearchView.viewName);

        // add results view
        ResultsUserDataAccessInterface resultsUserDataAccessInterface = new ResultsDataAccessObject();
        ResultsView resultsView = ResultsUseCaseFactory.create(viewManagerModel, resultsViewModel, searchViewModel, zipSearchViewModel, resultsUserDataAccessInterface);

        views.add(resultsView, resultsView.viewName);

        // add prefapply view
        PrefApplyDataAccessInterface prefapplyUserDataAccessObject = new PrefApplyDataAccessObject();
        PrefApplyview prefapplyView = PrefApplyUseCaseFactory.create(viewManagerModel, prefapplyViewModel, prefapplyUserDataAccessObject, shortUniversityFactory, loggedInViewModel,profileDataAccessObject);
        //ApplyController applyController = new ApplyController( applyUseCaseInteractor);
        //Applyview applyView = new Applyview(applyController, applyViewModel);

        views.add(prefapplyView, prefapplyView.viewName);

        viewManagerModel.setActiveView(mainMenuView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
