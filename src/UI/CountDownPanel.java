package UI;

import ConstantValues.Constants;

import javax.swing.*;
import java.awt.*;

public class CountDownPanel extends JPanel {
    JLabel imageLabel;

    protected ImageIcon icon;

    CountDownPanel(){
        init();

        this.setVisible(true);
    }

    private void init() {
        this.setLayout(new BorderLayout());
        this.setBackground(Constants.THEME_COLOR);

        icon = new ImageIcon(Constants.COUNT_DOWN_PATH);
        imageLabel = new JLabel(icon);

        add(imageLabel, BorderLayout.CENTER);
    }
}
