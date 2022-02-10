package UI;

import ConstantValues.Constants;
import ConstantValues.GUIValue;
import Model.ProgramFunctions;
import Model.TeamModel;
import Util.GUIUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Objects;

public class ResultPanel extends JPanel {
    ProgramFunctions pf;
    String title;
    List<TeamModel> data;

    JLabel titleLabel;
    JPanel upPanel;
    JPanel downPanel;
    JLabel backgroundLabel;

    ImageIcon icon;
    BufferedImage backgroundImage;

    Font titleFont;
    Font font;

    Color titleBackColor;
    Color titleFontColor;
    Color contentBackColor;
    Color contentFontColor;

    public ResultPanel(String title, List<TeamModel> data, ProgramFunctions pf) {
        this.title = title;
        this.data = data;
        this.pf = pf;

        initFont();
        initColor();
        initPanel();
        initComponents();
        attachComponents();
    }

    private void initColor() {
        if (pf == ProgramFunctions.Formation) {
            titleBackColor = Constants.THEME_COLOR;
            titleFontColor = Color.white;
            contentBackColor = Constants.FORMATION_PANEL_BACK;
            contentFontColor = Color.black;
        }
        else {
            titleBackColor = contentBackColor = Constants.THEME_COLOR;
            titleFontColor = contentFontColor = Color.white;
        }
    }

    protected void initFont() {
        titleFont = GUIValue.TITLE_FONT;
        font = GUIValue.TEXT_FONT;
    }

    protected void initPanel() {
        setLayout(null);

        GUIUtil.setSize(this,
                GUIValue.RESULT_PANEL_WIDTH, GUIValue.RESULT_PANEL_HEIGHT);

    }

    protected void initComponents() {
        upPanel = new JPanel();
        downPanel = new JPanel();
        titleLabel = new JLabel();
        backgroundLabel = new JLabel();

        upPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        if (!Objects.equals(title, "") && title != null) {
            upPanel.setBackground(titleBackColor);
            titleLabel.setText(title);
            titleLabel.setVerticalAlignment(JLabel.CENTER);
            titleLabel.setHorizontalAlignment(JLabel.CENTER);
            titleLabel.setFont(titleFont);
            titleLabel.setForeground(titleFontColor);
            upPanel.add(titleLabel);
        }

        downPanel.setLayout(new GridBagLayout());
        downPanel.setBackground(contentBackColor);
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

            teamNumberLabel.setForeground(contentFontColor);
            teamNameLabel.setForeground(contentFontColor);

            teamNumberLabel.setHorizontalAlignment(JLabel.CENTER);
            teamNameLabel.setHorizontalAlignment(JLabel.CENTER);

            teamNumberLabel.setText(data.get(i).getTeamNumber());
            teamNameLabel.setText(data.get(i).getTeamName());

            teamNumberLabel.setFont(font);
            teamNameLabel.setFont(font);
        }
    }

    protected void attachComponents() {
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
