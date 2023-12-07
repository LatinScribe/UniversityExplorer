package view;

import interface_adapter.results.ResultsController;
import interface_adapter.results.ResultsState;
import interface_adapter.results.ResultsViewModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

/**
 * A view with a JScrollPane and 2 buttons which allows for the Search use case to be performed.
 * @author Andre, Henry
 */
public class ResultsView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "results";
    private final ResultsViewModel resultsViewModel;
    private final ResultsController resultsController;
    private final JButton back;
    private final JButton confirm;
    private JList<String> myList;
    private DefaultListModel<String> listModel;
    private JScrollPane jScrollPane;
    private String selectedValue;

    /**
     * Instantiates the ResultsView.
     * @param resultsController
     * @param resultsViewModel
     */
    public ResultsView(ResultsController resultsController, ResultsViewModel resultsViewModel) {
        this.resultsController = resultsController;
        this.resultsViewModel = resultsViewModel;
        resultsViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Results View");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel instructions = new JLabel("For more information on a university, select a university in the list, then press confirm!");
        JPanel buttons = new JPanel();
        back = new JButton(ResultsViewModel.BACK_BUTTON_LABEL);
        confirm = new JButton(ResultsViewModel.CONFIRM_BUTTON_LABEL);

        back.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    /**
                     * When the back button is pressed, call the executeBack method of the resultsController.
                     * @param evt the event to be processed
                     */
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(back)) {
                            System.out.println("Back pressed");
                            removeButtons();
                            resultsController.executeBack();
                        }
                    }
                }
        );

        confirm.addActionListener(
                new ActionListener() {
                    /**
                     * When the confirm button is pressed, call the executeUniPress method of the resultsController,
                     * providing the name of the value selected in the JList/JScrollPane.
                     * @param evt the event to be processed
                     */
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(confirm)) {
                            System.out.println("Confirm pressed");
                            resultsController.executeUniPress(selectedValue);
                        }
                    }
                }
        );

        buttons.add(back);

        this.setLayout(new BoxLayout(this, 1));
        this.add(title);
        this.add(instructions);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Search not implemented yet.");
    }

    /**
     * If a university was selected and the confirm button is pressed, show a popup showing the universities If there
     * is an error in the search query or a lack of results, show a popup to the user regarding what has happened. If
     * there is an error in the query for any reason, show a popup to the user describing the error. Otherwise, create
     * a JScrollPane of university names that will be displayed to the user.
     * @param evt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    public void propertyChange(PropertyChangeEvent evt) {
        String y = evt.getPropertyName();
        if (y.equals("uni selected")) {
            ResultsState state = (ResultsState) evt.getNewValue();
            JOptionPane.showMessageDialog(this, state.getChosenUniversityString());
        } else if (y.equals("error")) {
            ResultsState state = (ResultsState) evt.getNewValue();
            JOptionPane.showMessageDialog(this, state.getResultsError());
        } else {
            ResultsState state = (ResultsState) evt.getNewValue();
            this.updateButtons(state.getUniversityNames());
        }

    }

    /**
     * Create a JScrollPane featuring multiple university name elements in a JList which will be displayed to the user.
     * @param uniList
     */
    private void updateButtons(List<String> uniList) {
        listModel = new DefaultListModel<>();
        for (String name : uniList) {
            listModel.addElement(name);
        }
        myList = new JList<>(listModel);

        myList.addListSelectionListener(

                new ListSelectionListener() {
                    /**
                     * Whenever a new element of the JList is changed, change the selected value parameter showing
                     * that university's name.
                     * @param e the event that characterizes the change.
                     */
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        Object[] chosen = myList.getSelectedValues();
                        String universityName = (String) chosen[0];
                        selectedValue = universityName;
                    }
                });

        this.remove(back);
        this.remove(confirm);
        JPanel buttons = new JPanel();
        jScrollPane = new JScrollPane(myList);
        jScrollPane.setPreferredSize(new Dimension(300, 200));
        this.add(jScrollPane);
        buttons.add(confirm);
        buttons.add(back);
        this.add(buttons);
    }

    /**
     * Remove the jScrollPane from the view in order to prevent duplicate code, and adjust the selectedValue to ensure
     * it remains null for the next time a search is performed.
     */
    private void removeButtons() {
        this.remove(jScrollPane);
        this.jScrollPane = null;
        selectedValue = null;
    }
}
