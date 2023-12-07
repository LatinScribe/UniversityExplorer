// Author: André

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

    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Search not implemented yet.");
    }

    public void propertyChange(PropertyChangeEvent evt) {
        String y = evt.getPropertyName();
        if (y.equals("uni selected")) {
            ResultsState state = (ResultsState) evt.getNewValue();
            JOptionPane.showMessageDialog(this, state.getChosenUniversityString());
        } else if (y.equals("error")) {
            ResultsState state = (ResultsState) evt.getNewValue();
            JOptionPane.showMessageDialog(this, state.getSearchError());
        } else {
            ResultsState state = (ResultsState) evt.getNewValue();
            this.updateButtons(state.getUniversityNames());
        }

    }

    private void updateButtons(List<String> uniList) {
        listModel = new DefaultListModel<>();
        for (String name : uniList) {
            listModel.addElement(name);
        }
        myList = new JList<>(listModel);

        myList.addListSelectionListener(

                new ListSelectionListener() {
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

    private void removeButtons() {
        this.remove(jScrollPane);
        this.jScrollPane = null;
    }
}
