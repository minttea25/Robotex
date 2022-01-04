package Util;

import javax.swing.*;
import java.awt.*;

public class GUIUtil {
    public static void setSize(Component component, Dimension d) {
        component.setMinimumSize(d);
        component.setPreferredSize(d);
        component.setMaximumSize(d);
    }

    public static void setSize(Component component, int width, int height) {
        Dimension d = new Dimension(width, height);
        component.setMinimumSize(d);
        component.setPreferredSize(d);
        component.setMaximumSize(d);
    }

    public static void setPanelMargin(JPanel panel, int margin) {
        panel.setBorder(BorderFactory.createEmptyBorder(
                margin, margin, margin, margin
        ));
    }

    public static void setButtonVerticalMargin(JButton button, int margin) {
        button.setBorder(BorderFactory.createEmptyBorder(
                0, margin, 0, margin
        ));
    }

    public static void setGridBagConstraintsWeight(GridBagConstraints gbc, double weightX, double weightY) {
        gbc.weightx = weightX;
        gbc.weighty = weightY;
    }

    public static void setGridBagConstraints(GridBagConstraints gbc, int gridx, int gridy, int gridWidth, int gridHeight) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridWidth;
        gbc.gridheight = gridHeight;
    }
}
