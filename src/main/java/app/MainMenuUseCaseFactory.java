//package app;
//
//import entity.CreationCommonUserFactory;
//import entity.CreationUserFactory;
//import interface_adapter.ViewManagerModel;
//import interface_adapter.login.LoginViewModel;
//import interface_adapter.main_menu.MainMenuController;
//import interface_adapter.main_menu.MainMenuViewModel;
//import interface_adapter.signup.SignupController;
//import interface_adapter.signup.SignupPresenter;
//import interface_adapter.signup.SignupViewModel;
//import use_case.signup.SignupInputBoundary;
//import use_case.signup.SignupInteractor;
//import use_case.signup.SignupOutputBoundary;
//import use_case.signup.SignupUserDataAccessInterface;
//import view.SignupView;
//
//import javax.swing.*;
//import java.io.IOException;
//
//public class MainMenuUseCaseFactory {
//    /** Prevent instantiation. */
//    private MainMenuUseCaseFactory() {}
//
//    public static SignupView create(
//            MainMenuViewModel mainMenuViewModel, MainMenuController controller) {
//
//        try {
//            SignupController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, loginViewModel, userDataAccessObject);
////            ClearController clearController = createClearUseCase(viewManagerModel, signupViewModel, loginViewModel, userDataAccessObject);
//            return new SignupView(signupController, signupViewModel);
//        } catch (IOException e) {
//            JOptionPane.showMessageDialog(null, "Could not open user data file.");
//        }
//
//        return null;
//    }
//
//    private static SignupController createUserSignupUseCase(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel, SignupUserDataAccessInterface userDataAccessObject) throws IOException {
//
//        // Notice how we pass this method's parameters to the Presenter.
//        SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);
//
//        CreationUserFactory userFactory = new CreationCommonUserFactory();
//
//        SignupInputBoundary userSignupInteractor = new SignupInteractor(
//                userDataAccessObject, signupOutputBoundary, userFactory);
//
//        return new SignupController(userSignupInteractor);
//    }
//}
