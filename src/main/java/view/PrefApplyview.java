package view;

import data_access.*;
import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.apply.ApplyViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.prefapply.PrefApplyController;
import interface_adapter.prefapply.PrefApplyPresenter;
import interface_adapter.prefapply.PrefApplyState;
import interface_adapter.prefapply.PrefApplyViewModel;
import interface_adapter.sub_menu.SubViewModel;
import use_case.prefapply.PrefApplyDataAccessInterface;
import use_case.prefapply.PrefApplyInputBoundary;
import use_case.prefapply.PrefApplyInteractor;
import use_case.prefapply.PrefApplyOutputBoundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PrefApplyview extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "PrefSubmit form";

    private final PrefApplyViewModel prefapplyViewModel;
    private final JTextField actInputField = new JTextField(15);
    private final JTextField satInputField = new JTextField(15);

    private final PrefApplyController prefapplyController;

    private final JButton submit;
    private final JButton back;


    public PrefApplyview(PrefApplyController controller, PrefApplyViewModel prefapplyViewModel) {

        this.prefapplyController = controller;
        this.prefapplyViewModel = prefapplyViewModel;
        prefapplyViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(ApplyViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel actInfo = new LabelTextPanel(
                new JLabel(ApplyViewModel.ACT_LABEL), actInputField);
        LabelTextPanel satInfo = new LabelTextPanel(
                new JLabel(ApplyViewModel.SAT_LABEL), satInputField);


        JPanel buttons = new JPanel();
        submit = new JButton(ApplyViewModel.SUBMIT_BUTTON_LABEL);
        buttons.add(submit);
        back = new JButton(ApplyViewModel.BACK_BUTTON_LABEL);
        buttons.add(back);
        back.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(back)) {
                            System.out.println("Back pressed");
                            prefapplyController.executeBack();
                        }
                    }
                }
        );
        submit.addActionListener(e -> {

            if (e.getSource().equals(submit)) {
                try {
                    System.out.println("submit Button pressed");
                    PrefApplyState prefapplyState = prefapplyViewModel.getState();

                    prefapplyController.execute(prefapplyState.getSat(), prefapplyState.getAct());

                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(this, "invalid input format", "error", JOptionPane.ERROR_MESSAGE);
                }

            }

        });
        actInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        PrefApplyState currentState = prefapplyViewModel.getState();
                        String text = actInputField.getText() + e.getKeyChar();
                        currentState.setAct(text);
                        prefapplyViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                }
        );
        satInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        PrefApplyState currentState = prefapplyViewModel.getState();
                        String text = satInputField.getText() + e.getKeyChar();
                        currentState.setSat(text);
                        prefapplyViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                }
        );


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(actInfo);
        this.add(satInfo);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Apply not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String y = evt.getPropertyName();
        if (y.equals("success")) {
            // Results View Not implemented yet, will be implemented soon.
            PrefApplyState state = (PrefApplyState) evt.getNewValue();
            JOptionPane.showMessageDialog(this, state.getUni().getSchoolName());
            //System.out.println(state.getUni().getSchoolName());
        } else if (y.equals("Error")) {
            PrefApplyState state = (PrefApplyState) evt.getNewValue();
            JOptionPane.showMessageDialog(this, state.getUniversityError());
            state.setUniversityError(null);
        }
    }

    public static void main(String[] args) {
//        JFrame application = new JFrame("ApplyView Test");
//        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        ViewManagerModel viewManagerModel = new ViewManagerModel();
//        PrefApplyViewModel prefapplyViewModel = new PrefApplyViewModel();
//        LoggedInViewModel mainMenuViewModel = new LoggedInViewModel();
//        PrefApplyOutputBoundary prefapplyPresenter = new PrefApplyPresenter(prefapplyViewModel, viewManagerModel, mainMenuViewModel);
//        //ApplyInputData applyInputData = new ApplyInputData();
//        PrefApplyDataAccessInterface prefapplyDataAccessInterface = new PrefApplyDataAccessObject();
//        //UserProfileFactory userFactory = new ExistingCommonUserFactory();
//        //ProfileDataAccessInterface serverUserDataAccessObject = new ServerProfileDataAccessObject();
//        UniversityFactory universityFactory = new CommonUniversityFactory();
//        ProfileDataAccessInterface profileDataAccessInterface = new Profiledataaccessmock1weird();
//        PrefApplyInputBoundary prefapplyUseCaseInteractor = new PrefApplyInteractor(prefapplyDataAccessInterface, prefapplyPresenter, universityFactory,profileDataAccessInterface);
//        PrefApplyController prefapplyController = new PrefApplyController(prefapplyUseCaseInteractor);
//        PrefApplyview prefapplyView = new PrefApplyview(prefapplyController, prefapplyViewModel);
//
//        JPanel testPanel = new JPanel();
//        testPanel.add(prefapplyView);
//
//        application.add(testPanel);
//        application.pack();
//        application.setVisible(true);
    }
}
