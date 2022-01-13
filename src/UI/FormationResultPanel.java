package UI;

import ConstantValues.GUIValue;
import Model.TeamModel;
import Util.GUIUtil;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class FormationResultPanel extends JPanel {
    Map<Integer, List<TeamModel>> data;

    int numberOfShowingPanels;
    ResultPanel[] panels;

    public FormationResultPanel(Map<Integer, List<TeamModel>> data) {
        this.data = data;
        this.numberOfShowingPanels = data.size();

        initPanel();
        initComponents();
        attachComponents();
    }

    private void initPanel() {
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        layout.setHgap(GUIValue.RESULT_PANEL_INTERVAL);
        setLayout(layout);


        GUIUtil.setSize(this,
                GUIValue.RESULT_BOX_WIDTH, GUIValue.RESULT_BOX_HEIGHT);
    }

    private void initComponents() {
        panels = new ResultPanel[numberOfShowingPanels];

        int i=0;
        for (int key : data.keySet()) {
            panels[i] = new ResultPanel(String.valueOf(key), data.get(key));
            i++;
        }

    }

    private void attachComponents() {
        for (ResultPanel p : panels) {
            add(p);
        }
    }

}
