package UI;

import ConstantValues.Constants;
import ConstantValues.GUIValue;
import Model.TeamModel;
import Util.GUIUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Objects;

public class ResultPanel extends JPanel {
    String title;
    List<TeamModel> data;

    JLabel titleLabel;
    JPanel upPanel;
    JPanel downPanel;
    JLabel backgroundLabel;

    ImageIcon icon;
    BufferedImage backgroundImage;

    public ResultPanel(String title, List<TeamModel> data) {
        this.title = title;
        this.data = data;

        initPanel();
        initComponents();
        attachComponents();
    }

    private void initPanel() {
        setLayout(null);

        GUIUtil.setSize(this,
                GUIValue.RESULT_PANEL_WIDTH, GUIValue.RESULT_PANEL_HEIGHT);

    }

    private void initComponents() {
        upPanel = new JPanel();
        downPanel = new JPanel();
        titleLabel = new JLabel();
        backgroundLabel = new JLabel();


        upPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        if (!Objects.equals(title, "") && title != null) {
            upPanel.setBackground(Constants.themeColor);
            titleLabel.setText(title);
            titleLabel.setVerticalAlignment(JLabel.CENTER);
            titleLabel.setHorizontalAlignment(JLabel.CENTER);
            titleLabel.setFont(GUIValue.TITLE_FONT);
            titleLabel.setForeground(Color.white);
            upPanel.add(titleLabel);
        }

        downPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        GUIUtil.setGridBagConstraintsWeight(gbc, 1.0, 1.0);

        for (int i=0; i<data.size(); i++) {
            JLabel teamNumberLabel = new JLabel();
            JLabel teamNameLabel = new JLabel();

            GUIUtil.setGridBagConstraints(gbc,
                    0, i,
                    1, 1);
            downPanel.add(teamNumberLabel, gbc);

            GUIUtil.setGridBagConstraints(gbc,
                    1, i,
                    1, 1);
            downPanel.add(teamNameLabel, gbc);

            teamNumberLabel.setHorizontalAlignment(JLabel.CENTER);
            teamNameLabel.setHorizontalAlignment(JLabel.CENTER);

            teamNumberLabel.setText(data.get(i).getTeamNumber());
            teamNameLabel.setText(data.get(i).getTeamName());

            teamNumberLabel.setFont(GUIValue.TEXT_FONT);
            teamNameLabel.setFont(GUIValue.TEXT_FONT);
        }
    }

    private void attachComponents() {
        if (title == null || title.equals("")) {
            add(downPanel);
            downPanel.setBounds(
                    0, 0,
                    GUIValue.RESULT_PANEL_BODY_WIDTH, GUIValue.RESULT_PANEL_TITLE_HEIGHT + GUIValue.RESULT_PANEL_BODY_HEIGHT
            );
        }
        else {
            add(upPanel);
            upPanel.setBounds(
                    0, 0,
                    GUIValue.RESULT_PANEL_TITLE_WIDTH, GUIValue.RESULT_PANEL_TITLE_HEIGHT
            );

            add(downPanel);
            downPanel.setBounds(
                    0, GUIValue.RESULT_PANEL_TITLE_HEIGHT,
                    GUIValue.RESULT_PANEL_BODY_WIDTH, GUIValue.RESULT_PANEL_BODY_HEIGHT
            );
        }

    }
}
