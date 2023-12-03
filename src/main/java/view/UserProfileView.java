// Author: Kanish
package view;

import interface_adapter.user_profiles.UserProfileController;
import interface_adapter.user_profiles.UserProfileState;
import interface_adapter.user_profiles.UserProfileViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;
import java.util.stream.Collectors;


public class UserProfileView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "userProfileView";

    private final UserProfileViewModel userProfileViewModel;

    private final JPanel cards;

    final JButton profile;
    final JButton newProfile;
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
        newProfile = new JButton("Create New Profile");
        buttons.add(newProfile);


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

        newProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Switch to the new profile creation view
                showNewProfileForm();
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
                universityRankingRangeField.setText("");
            }
            cardLayout.show(this.cards, "Edit");
        } else {
            cardLayout.show(this.cards, "View");
        }
        this.revalidate();
        this.repaint();
    }

    // Method to parse university ranking range from a string input
    private int[] parseUniversityRankingRange(String rangeText) throws IllegalArgumentException {
        // Check if the rangeText is null or empty
        if (rangeText == null || rangeText.trim().isEmpty()) {
            throw new IllegalArgumentException("University ranking range cannot be empty.");
        }

        // Split the input text by '-'
        String[] parts = rangeText.split("-");
        if (parts.length != 2) {
            throw new IllegalArgumentException("University ranking range must be in the format 'start-end'.");
        }

        try {
            // Parse start and end values from the string parts
            int start = Integer.parseInt(parts[0].trim());
            int end = Integer.parseInt(parts[1].trim());

            // Check if start is less than end
            if (start >= end) {
                throw new IllegalArgumentException("Start of the range must be less than the end.");
            }

            // Return the parsed range as an array
            return new int[]{start, end};
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("University ranking range must contain valid numbers.");
        }
    }

    // Method to show the new profile creation form
    private void showNewProfileForm() {
        // Create a new panel with a form layout for the profile data
        JPanel newProfilePanel = new JPanel(new GridLayout(0, 2, 5, 5)); // Rows, Cols, Hgap, Vgap

        // Create labels and text fields for the profile data
        JLabel finAidLabel = new JLabel("Financial Aid Requirement:");
        JTextField newFinAidRequirementField = new JTextField(10);
        JLabel avgSalaryLabel = new JLabel("Average Salary:");
        JTextField newAvgSalaryField = new JTextField(10);
        JLabel locationPrefLabel = new JLabel("Location Preference:");
        JTextField newLocationPreferenceField = new JTextField(10);
        JLabel preferredProgramLabel = new JLabel("Preferred Program:");
        JTextField newPreferredProgramField = new JTextField(10);
        JLabel rankingRangeLabel = new JLabel("University Ranking Range (start-end):");
        JTextField newUniversityRankingRangeField = new JTextField(10);

        // Button to save the new profile
        JButton saveNewProfileButton = new JButton("Save New Profile");
        saveNewProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Collect the data from text fields
                    int finAidRequirement = Integer.parseInt(newFinAidRequirementField.getText());
                    int avgSalary = Integer.parseInt(newAvgSalaryField.getText());
                    String locationPreference = newLocationPreferenceField.getText();
                    String preferredProgram = newPreferredProgramField.getText();
                    int[] universityRankingRange = parseUniversityRankingRange(newUniversityRankingRangeField.getText());

                    // Call the controller method to create a new profile
                    userProfileController.createNewUserProfile(
                            finAidRequirement, avgSalary, locationPreference, preferredProgram, universityRankingRange
                    );

                    // Switch back to the profile view
                    CardLayout cardLayout = (CardLayout) (cards.getLayout());
                    cardLayout.show(cards, "View");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid numbers for financial aid and average salary.", "Input Error", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add components to panel
        newProfilePanel.add(finAidLabel);
        newProfilePanel.add(newFinAidRequirementField);
        newProfilePanel.add(avgSalaryLabel);
        newProfilePanel.add(newAvgSalaryField);
        newProfilePanel.add(locationPrefLabel);
        newProfilePanel.add(newLocationPreferenceField);
        newProfilePanel.add(preferredProgramLabel);
        newProfilePanel.add(newPreferredProgramField);
        newProfilePanel.add(rankingRangeLabel);
        newProfilePanel.add(newUniversityRankingRangeField);

        // Add save button
        newProfilePanel.add(saveNewProfileButton);

        // Add the new profile panel to the cards
        cards.add(newProfilePanel, "NewProfile");

        // Switch to the new profile form view
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, "NewProfile");
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

