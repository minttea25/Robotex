package UI;

import ConstantValues.Constants;
import ConstantValues.GUIString;
import ConstantValues.GUIValue;
import Model.ProgramFunctions;
import Model.TeamModel;
import Util.GUIUtil;
import Util.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class TicketResultPanel extends Panel {
    List<TeamModel> list;
    int numberOfShowingTeams;
    int numberOfShowingPanels;

    int nth;

    String title;

    ResultPanel[] panels;

    BufferedImage contourImage;
    Set<String> loadFailSet = new HashSet<>();

    public TicketResultPanel(List<TeamModel> list, int nth) {
        this.list = list;
        this.numberOfShowingTeams = list.size();
        this.nth = nth;

        this.numberOfShowingPanels = (int)Math.ceil(list.size() / numberOfShowingTeams);

        /*this.numberOfShowingPanels = switch (nth) {
            case 1 -> 1;
            case 2, 3 -> (int)Math.ceil((double) list.size()/GUIValue.TICKET_PRELIMINARY_LAST_SHOWING_EACH_TEAMS);
            default -> throw new IllegalStateException("Unexpected value: " + nth);
        };*/

        switch (nth) {
            case 1 : this.numberOfShowingPanels = 1; break;
            case 2: case 3: this.numberOfShowingPanels = (int)Math.ceil((double) list.size()/GUIValue.TICKET_PRELIMINARY_LAST_SHOWING_EACH_TEAMS); break;
            default: throw new IllegalStateException("Unexpected value: " + nth);
        }

        if (nth == 1) {
            this.title = GUIString.TICKET_FINAL_LIST;
        }
        else {
            this.title = GUIString.TICKET_PRELIMINARY_LIST;
        }

        loadImages();

        initPanel();
        initComponents();
        attachComponents();
    }

    private void loadImages() {
        contourImage = ImageLoader.loadImage(Constants.VERTICAL_CONTOUR_WHITE_PATH);
        if (contourImage == null) {
            loadFailSet.add(Constants.VERTICAL_CONTOUR_WHITE_PATH);
        }
    }

    private void initPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBackground(Constants.THEME_COLOR);

        GUIUtil.setSize(this,
                GUIValue.RESULT_BOX_WIDTH, GUIValue.RESULT_BOX_HEIGHT);
    }

    private void initComponents() {
        if (nth == 1) {
            panels = new ResultPanelWorldQualification[numberOfShowingPanels];
        }
        else {
            panels = new ResultPanel[numberOfShowingPanels];
        }

        Map<Integer, List<TeamModel>> map = new HashMap<>();
        int index = 0;
        for (int i = 0; i< numberOfShowingPanels; i++) {
            List<TeamModel> teams = new ArrayList<>();
            for (int j = 0; j< GUIValue.TICKET_PRELIMINARY_LAST_SHOWING_EACH_TEAMS; j++) {
                if (index >= list.size()) {
                    break;
                }
                teams.add(list.get(index));
                index++;
            }
            map.put(i, teams);

            if (nth == 1) {
                panels[i] = new ResultPanelWorldQualification(title, map.get(i), ProgramFunctions.Ticket);
            }
            else {
                panels[i] = new ResultPanel(title + " " + (nth-1), map.get(i), ProgramFunctions.Ticket);
            }
        }
    }

    private void attachComponents() {
        for (int i=0; i<panels.length; i++) {
            add(panels[i]);

            if (contourImage != null) {
                if (i != panels.length - 1) {
                    add(new JLabel(new ImageIcon(contourImage)));
                }
            }
        }
    }

}
