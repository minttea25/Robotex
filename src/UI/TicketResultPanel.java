package UI;

import ConstantValues.GUIString;
import ConstantValues.GUIValue;
import Model.TeamModel;
import Util.GUIUtil;

import java.awt.*;
import java.util.*;
import java.util.List;

public class TicketResultPanel extends Panel {
    List<TeamModel> list;
    int numberOfShowingTeams;
    int numberOfShowingPanels;

    String title;

    ResultPanel[] panels;


    public TicketResultPanel(List<TeamModel> list, int showingNumber) {
        this.list = list;
        this.numberOfShowingTeams = showingNumber;

        this.numberOfShowingPanels = (int)Math.ceil(list.size() / showingNumber);

        if (numberOfShowingPanels == 1) {
            this.title = GUIString.TICKET_FINAL_LIST;
        }
        else {
            this.title = GUIString.TICKET_PRELIMINARY_LIST;
        }

        initPanel();
        initComponents();
        attachComponents();
    }

    private void initPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER));

        GUIUtil.setSize(this,
                GUIValue.RESULT_BOX_WIDTH, GUIValue.RESULT_BOX_HEIGHT);
    }

    private void initComponents() {
        panels = new ResultPanel[numberOfShowingPanels];

        Map<Integer, List<TeamModel>> map = new HashMap<>();
        int index = 0;
        for (int i = 0; i< numberOfShowingPanels; i++) {
            List<TeamModel> teams = new ArrayList<>();
            for (int j = 0; j< numberOfShowingTeams; j++) {
                teams.add(list.get(index));
                index++;
            }
            map.put(i, teams);

            panels[i] = new ResultPanel(title, map.get(i));
        }
    }

    private void attachComponents() {
        for (ResultPanel p : panels) {
            add(p);
        }
    }

}
