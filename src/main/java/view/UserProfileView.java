// Author: Kanish
package view;

import interface_adapter.user_profiles.UserProfileController;
import interface_adapter.user_profiles.UserProfileState;
import interface_adapter.user_profiles.UserProfileViewModel;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class UserProfileView extends JPanel implements ActionListener, PropertyChangeListener{
    public final String viewName = "User Profile";

    private final UserProfileViewModel userProfileViewModel;

    private JTextField finAidRequirementField;
    private JTextField preferredProgramField;
    private JTextField avgSalaryField;
    private JTextField universityRankingRangeField;
    private JTextField locationPreferenceField;

    final JButton profile;

    final JButton editProfile;

    final JButton continueAsGuest;

    final JButton signIn;

    final JButton userProfile;

    private final UserProfileController userProfileController;

    public UserProfileView(UserProfileViewModel userProfileViewModel, UserProfileController controller) {

        this.userProfileController = controller;
        this.userProfileViewModel = userProfileViewModel;
        this.userProfileViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("User Profile");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        // access static member using class - would this be a button for the profile?
        profile = new JButton(UserProfileViewModel.PROFILE_BUTTON_LABEL);
        buttons.add(profile);
        continueAsGuest = new JButton(UserProfileViewModel.GUEST_BUTTON_LABEL);
        buttons.add(continueAsGuest);
        signIn = new JButton(UserProfileViewModel.SIGNIN_BUTTON_LABEL);
        buttons.add(signIn);
        editProfile = new JButton(UserProfileViewModel.EDIT_BUTTON_LABEL);
        buttons.add(editProfile);
        userProfile = new JButton(UserProfileViewModel.OTHER_PROFILE_BUTTON_LABEL);

        finAidRequirementField = new JTextField(20);
        preferredProgramField = new JTextField(20);
        avgSalaryField = new JTextField(20);
        universityRankingRangeField = new JTextField(20);
        locationPreferenceField = new JTextField(20);

        // Add input fields to the panel
        this.add(new JLabel("Financial Aid Requirement:"));
        this.add(finAidRequirementField);
        this.add(new JLabel("Preferred Program:"));
        this.add(preferredProgramField);
        this.add(new JLabel("Average Salary:"));
        this.add(avgSalaryField);
        this.add(new JLabel("University Ranking Range:"));
        this.add(universityRankingRangeField);
        this.add(new JLabel("Location Preference:"));
        this.add(locationPreferenceField);

        // Setup action listeners for buttons
        editProfile.addActionListener(this);

//        UserProfileState.addActionListener(                // This creates an anonymous subclass of ActionListener and instantiates it.
//                new ActionListener() {
//                    public void actionPerformed(ActionEvent evt) {
//                        if (evt.getSource().equals("User Profile")) {
//                            UserProfileState currentState = UserProfileViewModel.getState();
//
//                            userProfileController.execute(
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


    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Button Clicked" + e.getActionCommand());


        // need to figure how to properly initialize this event in the listener above - need the use case implemented
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        UserProfileState state = (UserProfileState) evt.getNewValue();
    }

    private JButton saveButton; // A button for saving changes

    private void setupSaveButton() {
        saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveProfile();
            }
        });
        this.add(saveButton);
    }

    private Integer[] parseRankingRange(String input) {
        if (input == null || input.isEmpty()) {
            return new Integer[0];
        }
        String[] parts = input.split(",");
        Integer[] result = new Integer[parts.length];
        for (int i = 0; i < parts.length; i++) {
            try {
                result[i] = Integer.parseInt(parts[i].trim());
            } catch (NumberFormatException e) {
                // Handle the case where a part of the input is not a valid integer
                // You might want to notify the user or log this
                // For simplicity, we can skip invalid inputs
            }
        }
        return result;
    }
    private void saveProfile() {
        // Collect user input from fields
        Integer finAidRequirement = Integer.parseInt(finAidRequirementField.getText());
        String preferredProgram = preferredProgramField.getText();
        Integer avgSalary = Integer.parseInt(avgSalaryField.getText());
        String universityRankingRangeText = universityRankingRangeField.getText();
        Integer[] universityRankingRange = parseRankingRange(universityRankingRangeText);
        String locationPreference = locationPreferenceField.getText();



        // Send data to controller
        userProfileController.updateUserProfile(finAidRequirement, preferredProgram,
                avgSalary, universityRankingRange,
                locationPreference);
    }

    public static void main(String[] args) {
        JFrame application = new JFrame("User Profile Test");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        UserProfileViewModel userProfileViewModel1 = new UserProfileViewModel("User Profile");
        UserProfileController userProfileController1 = new UserProfileController(userProfileViewModel1);
        UserProfileView userProfileView = new UserProfileView(userProfileViewModel1, userProfileController1);

        views.add(userProfileView, userProfileView.viewName);

        application.pack();
        application.setVisible(true);

    }
}
