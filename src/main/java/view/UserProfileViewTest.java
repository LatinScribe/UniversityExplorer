package view;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.user_profiles.UserProfileController;
import interface_adapter.user_profiles.UserProfilePresenter;
import interface_adapter.user_profiles.UserProfileViewModel;
import use_case.user_profile.UserProfileInputBoundary;
import use_case.user_profile.UserProfileInteractor;
import view.UserProfileView;

public class UserProfileViewTest {

    public static void main(String[] args) {
        JFrame frame = new JFrame("User Profile Test");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Create instances of ViewModel and Controller
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        UserProfileViewModel viewModel = new UserProfileViewModel("User Profile");
        UserProfilePresenter userProfilePresenter = new UserProfilePresenter(viewManagerModel, viewModel);
        UserProfileInputBoundary userProfileInteractor = new UserProfileInteractor(userProfilePresenter);
        UserProfileController controller = new UserProfileController(userProfileInteractor);

        // Create the UserProfileView and add it to the frame
        UserProfileView userProfileView = new UserProfileView(viewModel, controller);
        frame.add(userProfileView);

        frame.pack();
        frame.setVisible(true);
    }
}
