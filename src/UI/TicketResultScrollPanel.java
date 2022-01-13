package UI;

import ConstantValues.GUIString;
import ConstantValues.GUIValue;
import Model.TeamModel;
import Util.GUIUtil;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class TicketResultScrollPanel extends JPanel {
    List<TeamModel> list;
    int showingNumber;
    int showingNumberOfPanels;

    String title;

    JPanel view;
    JScrollPane scrollPane;
    ResultPanel[] panels;

    public TicketResultScrollPanel(List<TeamModel> list, int showingNumber) {
        this.list = list;
        this.showingNumber = showingNumber;

        this.showingNumberOfPanels = (int)(Math.ceil(list.size() / (double)this.showingNumber));
        this.title = GUIString.TICKET_PRELIMINARY_LIST_3;

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
        view = new JPanel();
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
        layout.setHgap(GUIValue.RESULT_PANEL_INTERVAL);
        setLayout(layout);
        view.setLayout(layout);
        GUIUtil.setSize(view,
                getCalculatedWidth(), GUIValue.RESULT_BOX_HEIGHT);

        panels = new ResultPanel[showingNumberOfPanels];

        Map<Integer, List<TeamModel>> map = new HashMap<>();
        int index = 0;
        for (int i=0; i<showingNumberOfPanels; i++) {
            List<TeamModel> teams = new ArrayList<>();
            for (int j=0; j<showingNumber; j++) {
                if (index >= list.size()) {
                    break;
                }
                teams.add(list.get(index));
                index++;
            }
            map.put(i, teams);

            panels[i] = new ResultPanel(title, map.get(i));
            view.add(panels[i]);
        }

        scrollPane = new JScrollPane();
        GUIUtil.setSize(scrollPane,
                GUIValue.RESULT_BOX_WIDTH, GUIValue.RESULT_BOX_HEIGHT);

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setViewportView(view);
    }

    private void attachComponents() {
        add(scrollPane);
    }


    private int getCalculatedWidth() {
        int width = IntStream.range(0, showingNumberOfPanels).map(i -> (GUIValue.RESULT_PANEL_WIDTH + GUIValue.RESULT_PANEL_INTERVAL)).sum();
        return width + GUIValue.RESULT_PANEL_INTERVAL;
    }
}
