package UI;

import ConstantValues.Constants;

import javax.swing.*;
import java.awt.*;

public class CountDownPanel extends JPanel {
    JLabel imageLabel;

    ImageIcon icon;

    CountDownPanel(){
        init();
    }

    private void init() {
        this.setLayout(new BorderLayout());
        this.setBackground(Constants.themeColor);

        icon = new ImageIcon(Constants.COUNT_DOWN_PATH);
        imageLabel = new JLabel(icon);
        add(imageLabel, BorderLayout.CENTER);
    }
}
