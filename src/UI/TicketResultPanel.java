package UI;

import ConstantValues.Constants;
import ConstantValues.GUIString;
import ConstantValues.GUIValue;
import Model.ProgramFunctions;
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
        /*else {
            this.title = GUIString.TICKET_PRELIMINARY_LIST;
        }*/
        else {
            this.title = "";
        }

        initPanel();
        initComponents();
        attachComponents();
    }

    private void initPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBackground(Constants.THEME_COLOR);

        GUIUtil.setSize(this,
                GUIValue.RESULT_BOX_WIDTH, GUIValue.RESULT_BOX_HEIGHT);
    }

    private void initComponents() {
        if (numberOfShowingPanels == 1) {
            panels = new ResultPanelWorldQualification[numberOfShowingPanels];
        }
        else {
            panels = new ResultPanel[numberOfShowingPanels];
        }

        Map<Integer, List<TeamModel>> map = new HashMap<>();
        int index = 0;
        for (int i = 0; i< numberOfShowingPanels; i++) {
            List<TeamModel> teams = new ArrayList<>();
            for (int j = 0; j< numberOfShowingTeams; j++) {
                teams.add(list.get(index));
                index++;
            }
            map.put(i, teams);

            if (numberOfShowingPanels == 1) {
                panels[i] = new ResultPanelWorldQualification(title, map.get(i), ProgramFunctions.Ticket);
            }
            else {
                panels[i] = new ResultPanel(title, map.get(i), ProgramFunctions.Ticket);
            }
        }
    }

    private void attachComponents() {
        for (ResultPanel p : panels) {
            add(p);
        }
    }

}
