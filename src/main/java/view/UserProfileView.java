// Author: Kanish
package view;

import interface_adapter.user_profiles.UserProfileController;
import interface_adapter.user_profiles.UserProfileState;
import interface_adapter.user_profiles.UserProfileViewModel;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

import javax.lang.model.type.ArrayType;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class UserProfileView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "userProfileView";

    private final UserProfileViewModel userProfileViewModel;

    private final JPanel cards;

    final JButton profile;
    final JButton editProfile;
    final JButton save;

    JLabel finAidRequirementValue;
    JLabel avgSalaryValue;
    JLabel locationPreferenceValue;
    JLabel preferredProgramValue;
    JLabel universityRankingRangeValue;

    final JTextField finAidRequirementField;
    final JTextField avgSalaryField;
    final JTextField locationPreferenceField;
    final JTextField preferredProgramField;
    // Assuming universityRankingRange is an array of integers
    final JTextField universityRankingRangeField;

    private final JLabel finAidRequirementLabel;
    private final JLabel avgSalaryLabel;
    private final JLabel locationPreferenceLabel;
    private final JLabel preferredProgramLabel;
    private final JLabel universityRankingRangeLabel;


    private final UserProfileController userProfileController;

    public UserProfileView(UserProfileViewModel userProfileViewModel, UserProfileController controller) {

        this.userProfileController = controller;
        this.userProfileViewModel = userProfileViewModel;
        this.userProfileViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("User Profile");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        finAidRequirementLabel = new JLabel("- Financial Aid Requirement");
        avgSalaryLabel = new JLabel("- Average Salary");
        locationPreferenceLabel = new JLabel("- Location Preference");
        preferredProgramLabel = new JLabel("- Preferred Program");
        universityRankingRangeLabel = new JLabel("- University Ranking Range");

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

        finAidRequirementValue = new JLabel();
        avgSalaryValue = new JLabel();
        locationPreferenceValue = new JLabel();
        preferredProgramValue = new JLabel();
        universityRankingRangeValue = new JLabel();

        viewPanel.add(finAidRequirementValue);
        viewPanel.add(avgSalaryValue);
        viewPanel.add(locationPreferenceValue);
        viewPanel.add(preferredProgramValue);
        viewPanel.add(universityRankingRangeValue);


        finAidRequirementField = new JTextField(10);
        avgSalaryField = new JTextField(10);
        locationPreferenceField = new JTextField(10);
        preferredProgramField = new JTextField(10);
        universityRankingRangeField = new JTextField(10);

        viewPanel.add(profile);
        viewPanel.add(editProfile);

        // TODO - implement the view so that users can see what they've already input or blank if they haven't

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

        setEditMode(false); // Initially set to view mode

        profile.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(profile)) {
                            userProfileController.getProfile();
                            CardLayout c1 = (CardLayout) (cards.getLayout());
                            c1.show(cards, "View");
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
                    int[] universityRankingRange = Arrays.stream(universityRankingRangeField.getText().split(","))
                            .map(String::trim)
                            .filter(str -> !str.isEmpty())
                            .mapToInt(Integer::parseInt) // Use mapToInt instead of map
                            .toArray(); // Convert to int[] directly


                    userProfileController.updateUserProfile(
                            finAidRequirement, avgSalary, locationPreference, preferredProgram, universityRankingRange);

                    // TODO - determine whether to use Array or Integer[]

                    setEditMode(false); // Switch back to view mode after saving
                } catch (NumberFormatException ex) {
                    // Handle invalid input format
                    JOptionPane.showMessageDialog(this, "Invalid input format", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        this.add(title);
        this.add(buttons);
    }


    private void setEditMode(boolean editable) {
        CardLayout cardLayout = (CardLayout) (this.cards.getLayout());
        if (editable) {
            UserProfileState state = userProfileViewModel.getState();
            finAidRequirementField.setText(state.getFinAidRequirement() != null ? String.valueOf(state.getFinAidRequirement()) : "");
            avgSalaryField.setText(state.getAvgSalary() != null ? String.valueOf(state.getAvgSalary()) : "");
            locationPreferenceField.setText(state.getLocationPreference() != null ? state.getLocationPreference() : "");
            preferredProgramField.setText(state.getPreferredProgram() != null ? state.getPreferredProgram() : "");
            // Set universityRankingRangeField by parsing the array and representing it as a string
            int[] universityRankingRange = state.getUniversityRankingRange();
          
            if (universityRankingRange != null && universityRankingRange.length > 0) {
                universityRankingRangeField.setText(Arrays.stream(universityRankingRange)
                        .mapToObj(String::valueOf) // Convert each int to String
                        .collect(Collectors.joining(", ")));
            } else {
// =======
//             if (universityRankingRange != null && universityRankingRange.length == 1) {
//                 universityRankingRangeField.setText(String.valueOf(universityRankingRange[0]));
//             } if(universityRankingRange != null && universityRankingRange.length == 2) {
//                 universityRankingRangeField.setText(universityRankingRange[0] + ", " + universityRankingRange[1]);
//             }

//             else {
// >>>>>>> main
                universityRankingRangeField.setText("");
            }
            cardLayout.show(this.cards, "Edit");
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
            try {
                // Parse integer fields
                int finAidRequirement = Integer.parseInt(finAidRequirementField.getText());
                int avgSalary = Integer.parseInt(avgSalaryField.getText());

                // Parse string fields
                String locationPreference = locationPreferenceField.getText();
                String preferredProgram = preferredProgramField.getText();

                // Parse the university ranking range
                int[] universityRankingRange = Arrays.stream(universityRankingRangeField.getText().split(","))
                        .map(String::trim)
                        .filter(str -> !str.isEmpty())
                  
                        .mapToInt(Integer::parseInt) // Convert to int

                        .toArray();

                // Invoke controller method to save changes
                userProfileController.updateUserProfile(
                        finAidRequirement, avgSalary, locationPreference, preferredProgram, universityRankingRange);

                setEditMode(false); // Switch back to view mode after saving
            } catch (NumberFormatException ex) {
                // Handle invalid input format
                JOptionPane.showMessageDialog(this, "Invalid input format. Please check your inputs.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Check if the property change event is for the state of the user profile
        if ("state".equals(evt.getPropertyName())) {
            // Retrieve the updated state
            UserProfileState state = (UserProfileState) evt.getNewValue();

            // Update the labels with the new state values
            finAidRequirementValue.setText(state.getFinAidRequirement() != null ? String.valueOf(state.getFinAidRequirement()) : "Not Set");
            avgSalaryValue.setText(state.getAvgSalary() != null ? String.valueOf(state.getAvgSalary()) : "Not Set");
            locationPreferenceValue.setText(state.getLocationPreference() != null ? state.getLocationPreference() : "Not Set");
            preferredProgramValue.setText(state.getPreferredProgram() != null ? state.getPreferredProgram() : "Not Set");

            // Handle the universityRankingRange array
            int[] universityRankingRange = state.getUniversityRankingRange();
            if (universityRankingRange != null && universityRankingRange.length > 0) {
                universityRankingRangeValue.setText(Arrays.stream(universityRankingRange)
                        .mapToObj(String::valueOf) // Convert each int to String
// =======
//                         //
//                         .mapToObj(Integer::toString)
// >>>>>>> main
                        .collect(Collectors.joining(", ")));
            } else {
                universityRankingRangeValue.setText("Not Set");
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

