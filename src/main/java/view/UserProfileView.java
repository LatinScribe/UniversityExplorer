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
import java.util.Arrays;
import java.util.stream.Collectors;


public class UserProfileView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "userProfileView";

    private final UserProfileViewModel userProfileViewModel;

    private final JPanel cards;

    final JButton profile;
    final JButton editProfile;
    final JButton save;

    private JTextField finAidRequirementField;
    private JTextField avgSalaryField;
    private JTextField locationPreferenceField;
    private JTextField preferredProgramField;
    // Assuming universityRankingRange is an array of integers
    private JTextField universityRankingRangeField;

    private JLabel finAidRequirementLabel;
    private JLabel avgSalaryLabel;
    private JLabel locationPreferenceLabel;
    private JLabel preferredProgramLabel;
    private JLabel universityRankingRangeLabel;


    private final UserProfileController userProfileController;

    public UserProfileView(UserProfileViewModel userProfileViewModel, UserProfileController controller) {

        this.userProfileController = controller;
        this.userProfileViewModel = userProfileViewModel;
        this.userProfileViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("User Profile");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        finAidRequirementLabel = new JLabel("Financial Aid Requirement:");
        avgSalaryLabel = new JLabel("Average Salary:");
        locationPreferenceLabel = new JLabel("Location Preference:");
        preferredProgramLabel = new JLabel("Preferred Program:");
        universityRankingRangeLabel = new JLabel("University Ranking Range:");

        JPanel buttons = new JPanel();
        // access static member using class - would this be a button for the profile?
        profile = new JButton(UserProfileViewModel.PROFILE_BUTTON_LABEL);
        buttons.add(profile);
        save = new JButton(UserProfileViewModel.SAVE_BUTTON_LABEL);
        buttons.add(save);
        editProfile = new JButton(UserProfileViewModel.EDIT_BUTTON_LABEL);
        buttons.add(editProfile);

        this.cards = new JPanel(new CardLayout());
        JPanel viewPanel = new JPanel(); // Panel for non-editable view
        JPanel editPanel = new JPanel(); // Panel for editable view


        finAidRequirementField = new JTextField(10);
        avgSalaryField = new JTextField(10);
        locationPreferenceField = new JTextField(10);
        preferredProgramField = new JTextField(10);
        universityRankingRangeField = new JTextField(10);

        viewPanel.add(profile);
        viewPanel.add(editProfile);

        // Add fields for editing
        editPanel.add(finAidRequirementField);
        editPanel.add(finAidRequirementLabel);
        editPanel.add(avgSalaryField);
        editPanel.add(avgSalaryLabel);
        editPanel.add(locationPreferenceField);
        editPanel.add(locationPreferenceLabel);
        editPanel.add(preferredProgramField);
        editPanel.add(preferredProgramLabel);
        editPanel.add(universityRankingRangeField);
        editPanel.add(universityRankingRangeLabel);
        editPanel.add(save);

        // Add panels to cards
        this.cards.add(viewPanel, "View");
        this.cards.add(editPanel, "Edit");

        // Add the cards panel to the UserProfileView
        this.add(cards);

        // Add new fields to the panel or a specific layout
        // Example: this.add(finAidRequirementField);

        setEditMode(false); // Initially set to view mode

        profile.addActionListener(                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(profile)) {
                            userProfileController.switchToPersonalProfile();
                        }
                    }
                }
        );

        editProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setEditMode(true);
            }
        });

        save.addActionListener(e -> {
            if (e.getSource().equals(save)) {
                try {
                    int finAidRequirement = Integer.parseInt(finAidRequirementField.getText());
                    int avgSalary = Integer.parseInt(avgSalaryField.getText());
                    String locationPreference = locationPreferenceField.getText();
                    String preferredProgram = preferredProgramField.getText();
                    // Parse the comma-separated string back into an array
                    Integer[] universityRankingRange = Arrays.stream(universityRankingRangeField.getText().split(","))
                            .map(String::trim)
                            .filter(str -> !str.isEmpty())
                            .map(Integer::parseInt)
                            .toArray(Integer[]::new);

                    userProfileController.updateUserProfile(
                            finAidRequirement, avgSalary, locationPreference, preferredProgram, universityRankingRange);

                    setEditMode(false); // Switch back to view mode after saving
                } catch (NumberFormatException ex) {
                    // Handle invalid input format
                    JOptionPane.showMessageDialog(this, "Invalid input format", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // TODO: Finish implementation of different edit structure and remove editProfile classes/functions

        /*editProfile.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(profile)) {
                            userProfileController.switchToEditProfile();
                        }
                    }
                }
        );*/


        this.add(title);
        this.add(buttons);
    }

    /*private void setEditMode(boolean editable) {
        finAidRequirementField.setEditable(editable);
        avgSalaryField.setEditable(editable);
        locationPreferenceField.setEditable(editable);
        preferredProgramField.setEditable(editable);
        universityRankingRangeField.setEditable(editable);
        save.setVisible(editable);

        UserProfileState state = userProfileViewModel.getState();

        if (!editable) {
            // In view mode, populate fields from state
            finAidRequirementField.setText(state.getFinAidRequirement() != null ? state.getFinAidRequirement().toString() : "0");
            avgSalaryField.setText(state.getAvgSalary() != null ? state.getAvgSalary().toString() : "0");
            locationPreferenceField.setText(state.getLocationPreference() != null ? state.getLocationPreference() : "");
            preferredProgramField.setText(state.getPreferredProgram() != null ? state.getPreferredProgram() : "");
            // Convert array to comma-separated string
            universityRankingRangeField.setText(Arrays.toString(state.getUniversityRankingRange()).replaceAll("[\\[\\]]", ""));
        } else {
            // Clear the fields or set them to default values when switching to edit mode
            finAidRequirementField.setText("0");
            avgSalaryField.setText("0");
            locationPreferenceField.setText("");
            preferredProgramField.setText("");
            universityRankingRangeField.setText("");
        }
    }*/

    // TODO: Check if the following CardLayout implementation works better than the one above

    private void setEditMode(boolean editable) {
        CardLayout cardLayout = (CardLayout) (this.cards.getLayout());
        if (editable) {
            cardLayout.show(this.cards, "Edit");
            // Populate the edit fields with current state if necessary
        } else {
            cardLayout.show(this.cards, "View");
        }
        this.revalidate();
        this.repaint();
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Button Clicked" + e.getActionCommand());
        if (e.getSource() == save) {
            // Update UserProfileState with new values
            int finAidRequirement = Integer.parseInt(finAidRequirementField.getText());
            int avgSalary = Integer.parseInt(avgSalaryField.getText());
            //... parse and set other fields ...

            // Invoke controller method to save changes
            userProfileController.updateUserProfile(
                    finAidRequirement, avgSalary);

            setEditMode(false); // Switch back to view mode after saving


            // need to figure how to properly initialize this event in the listener above - need the use case implemented
        }
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Check if the property name is "state" which indicates a change in UserProfileState
        if ("state".equals(evt.getPropertyName())) {
            UserProfileState state = (UserProfileState) evt.getNewValue();

            // Update the view components based on the new state
            finAidRequirementField.setText(state.getFinAidRequirement() != null ? state.getFinAidRequirement().toString() : "");
            avgSalaryField.setText(state.getAvgSalary() != null ? state.getAvgSalary().toString() : "");
            locationPreferenceField.setText(state.getLocationPreference() != null ? state.getLocationPreference() : "");
            preferredProgramField.setText(state.getPreferredProgram() != null ? state.getPreferredProgram() : "");

            // Handle the universityRankingRange array
            Integer[] universityRankingRange = state.getUniversityRankingRange();

            if (universityRankingRange != null && universityRankingRange.length > 0) {
                // Use Arrays.stream() to convert the array to a stream, then process it
                universityRankingRangeField.setText(Arrays.stream(universityRankingRange)
                        .map(String::valueOf) // Convert Integer to String
                        .collect(Collectors.joining(", ")));
            } else {
                universityRankingRangeField.setText("");
            }
        }
    }
}

/*    public static void main(String[] args) {
        JFrame application = new JFrame("User Profile Test");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        UserProfileViewModel userProfileViewModel1 = new UserProfileViewModel("User Profile");
        UserProfileController userProfileController1 = new UserProfileController();
        UserProfileView userProfileView = new UserProfileView(userProfileViewModel1, userProfileController1);

        views.add(userProfileView, userProfileView.viewName);

        application.pack();
        application.setVisible(true);

    }*/

