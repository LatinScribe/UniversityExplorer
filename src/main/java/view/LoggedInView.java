package view;

import interface_adapter.logged_in.LoggedInController;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.user_profiles.UserProfileViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * View shown to the user after they have successfully logged in
 *
 * @author Henry
 */
public class LoggedInView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "logged in";
    private final LoggedInViewModel loggedInViewModel;

    private final LoggedInController loggedInController;

    JLabel username;

    final JButton logOut;
    final JButton viewProfile;
    final JButton getPrefRec;


    /**
     * A window with a title and a JButton.
     */
    public LoggedInView(LoggedInViewModel loggedInViewModel, UserProfileViewModel userProfileViewModel, LoggedInController loggedInController) {
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInController = loggedInController;
        this.loggedInViewModel.addPropertyChangeListener(this);
//        this.userprofileViewModel = userProfileViewModel;
//        this.userprofileViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Logged In Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel usernameInfo = new JLabel("Currently logged in: ");
        username = new JLabel();

        JPanel buttons = new JPanel();
        logOut = new JButton(LoggedInViewModel.LOGOUT_BUTTON_LABEL);
        buttons.add(logOut);

        // add view profile button
        viewProfile = new JButton(LoggedInViewModel.PROFILE_BUTTON_LABEL);
        buttons.add(viewProfile);
        viewProfile.addActionListener(// This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(viewProfile)) {
                            System.out.println("ViewProfile pressed");
                            loggedInController.swapToUserProfileView();
                        }
                    }
                });

        getPrefRec = new JButton(LoggedInViewModel.GETREC_BUTTON_LABEL);
        buttons.add(getPrefRec);
        getPrefRec.addActionListener(// This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(getPrefRec)) {
                            System.out.println("get rec pressed");
                            loggedInController.prepareUserPrefApplyView();
                        }
                    }
                });


        logOut.addActionListener(// This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logOut)) {
                            System.out.println("logOut pressed");
                            loggedInController.logOutUser();
                        }
                    }
                });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(username);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        // output some messages for debugging
        // System.out.println("Click " + evt.getActionCommand()); - removed this for view logic
        if (evt.getSource() == viewProfile) {
//            loggedInViewModel.setCurrentView("userProfileView");
//            loggedInViewModel.firePropertyChanged();
            System.out.println("View Profile BUtton LCilcked");
//            this.loggedInController.swapToUserProfileView();
            // we swap the view
            // When trying to switch views - this print statement seems to work
        } else {
            System.out.println("Click " + evt.getActionCommand());
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName()) && evt.getNewValue() instanceof LoggedInState) {
            System.out.println("Something apart from View Profile clicked");
            LoggedInState state = (LoggedInState) evt.getNewValue();
            username.setText(state.getUsername());
        } else if ("currentView".equals(evt.getPropertyName()) && evt.getNewValue() instanceof String) {
            String newView = (String) evt.getNewValue();
            System.out.println("View Profile Button Clicked");
            // Handle the view change here. Typically, this would involve
            // telling a ViewManager or similar to change the view.
        }
    }
}