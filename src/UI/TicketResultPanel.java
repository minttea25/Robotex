package UI;

import ConstantValues.GUIString;
import ConstantValues.GUIValue;
import Model.TeamModel;
import Util.GUIUtil;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;

public class TicketResultPanel extends JPanel {
    List<TeamModel> list;
    int showingNumber;

    JLabel panel;
    JPanel upPanel;
    JPanel downPanel;
    JLabel label;


    public TicketResultPanel(List<TeamModel> list, int showingNumber) {
        this.list = list;
        this.showingNumber = showingNumber;

        initPanel();
        initComponents();

        attachComponents();
    }

    private void attachComponents() {
        add(panel);

    }

    private void initComponents() {
        panel = new JLabel();
        upPanel = new JPanel();
        downPanel = new JPanel();
        label = new JLabel();

        panel.setLayout(null);
        GUIUtil.setSize(panel,
                GUIValue.RESULT_WIDTH, GUIValue.RESULT_PANEL_HEIGHT);

        upPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        Random r = new Random();
        upPanel.setBackground(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));

        label.setText(GUIString.TICKET_FINAL_LIST);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        GUIUtil.setSize(label,
                GUIValue.RESULT_WIDTH, GUIValue.RESULT_PANEL_ENTRY_BOX_HEIGHT);
        upPanel.add(label);

        downPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        GUIUtil.setGridBagConstraintsWeight(gbc, 1.0, 1.0);

        for (int i=0; i<list.size(); i++) {
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

            teamNumberLabel.setText(list.get(i).getTeamNumber());
            teamNameLabel.setText(list.get(i).getTeamName());
        }

        panel.add(upPanel);
        upPanel.setBounds(
                0, 0,
                GUIValue.RESULT_WIDTH, GUIValue.RESULT_PANEL_ENTRY_BOX_HEIGHT
        );

        panel.add(downPanel);
        downPanel.setBounds(
                0, GUIValue.RESULT_PANEL_ENTRY_BOX_HEIGHT,
                GUIValue.RESULT_WIDTH, GUIValue.RESULT_PANEL_HEIGHT - GUIValue.RESULT_PANEL_ENTRY_BOX_HEIGHT

        );
    }

    private void initPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER));

        GUIUtil.setSize(this,
                GUIValue.RESULT_TICKET_WIDTH, GUIValue.RESULT_PANEL_HEIGHT);
    }
}
