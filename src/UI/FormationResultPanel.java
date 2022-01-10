package UI;

import ConstantValues.GUIValue;
import Model.TeamModel;
import Util.GUIUtil;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;

public class FormationResultPanel extends JPanel {
    int entry;
    List<TeamModel> teams;

    JLabel entryLabel;
    JPanel upPanel;
    JPanel downPanel;

    public FormationResultPanel(int entry, List<TeamModel> lists) {
        this.entry = entry;
        this.teams = lists;

        initPanel();
        initComponents();
        attachComponents();
    }

    private void attachComponents() {
        add(upPanel);
        upPanel.setBounds(
                0, 0,
                GUIValue.RESULT_WIDTH, GUIValue.RESULT_PANEL_ENTRY_BOX_HEIGHT
        );

        add(downPanel);
        downPanel.setBounds(
                0, GUIValue.RESULT_PANEL_ENTRY_BOX_HEIGHT,
                GUIValue.RESULT_WIDTH, GUIValue.RESULT_PANEL_HEIGHT - GUIValue.RESULT_PANEL_ENTRY_BOX_HEIGHT

        );
    }

    private void initComponents() {
        entryLabel = new JLabel();
        upPanel = new JPanel();
        downPanel = new JPanel();

        entryLabel.setText(String.valueOf(entry));
        entryLabel.setVerticalTextPosition(JLabel.CENTER);

        upPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        downPanel.setLayout(new GridBagLayout());

        Random r = new Random();
        upPanel.setBackground(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));

        upPanel.add(entryLabel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        GUIUtil.setGridBagConstraintsWeight(gbc, 1.0, 1.0);

        int i=0;
        for (var team : teams) {
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

            teamNumberLabel.setText(team.getTeamNumber());
            teamNameLabel.setText(team.getTeamName());

            i++;
        }
    }

    private void initPanel() {
        setLayout(null);

        GUIUtil.setSize(this,
                new Dimension(
                        GUIValue.RESULT_WIDTH, GUIValue.RESULT_PANEL_HEIGHT
                ));
    }
}
