package app;

import javax.swing.*;

public class ResultsButtonFactory {

    public JButton create(String uniName) {
        return new JButton(uniName);
    }

}
