package UI;

import ConstantValues.Constants;
import ConstantValues.GUIString;
import ConstantValues.GUIValue;
import ConstantValues.Sections;
import Excel.ExcelWriteManagerFormation;
import Model.TeamModel;
import Util.GUIUtil;
import Util.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

public class FormationFrame extends JFrame {
    Sections section;
    List<TeamModel> data;
    Map<Integer, List<TeamModel>> map = new HashMap<>();

    int numberOfEntries;

    JLabel backgroundLabel;
    JScrollPane scrollPane;
    JPanel resultPanel;
    FormationResultPanel[] panels;

    BufferedImage backgroundImage;

    ExcelWriteManagerFormation ewmf;

    public FormationFrame(Sections section, List<TeamModel> data, int numberOfEntries) {
        this.section = section;
        this.data = data;
        this.numberOfEntries = numberOfEntries;

        loadImages();

        shuffleData();
        makeEntries();

        saveFile();

        initFrame();
        initComponents();
        attachComponents();
    }

    private void saveFile() {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(Constants.SAVE_FILE_DATE_FORMAT);
        String fileName = section.toString() + " - " + date + " " + time.format(timeFormatter);
        ewmf = new ExcelWriteManagerFormation(
                section,
                map,
                fileName
        );

        ewmf.createExcelFile();

        if (!ewmf.isWritten()) {
            System.out.println("Failed to create file");
        }
    }

    private void loadImages() {
        switch (section) {
            case LegoSumo1kg, LegoSumo3kg -> backgroundImage = ImageLoader.loadImage(Constants.FORMATION_LEGO_SUMO_PATH);
            case LineFollowingE, LineFollowingJH -> backgroundImage = ImageLoader.loadImage(Constants.FORMATION_LINE_FOLLOWING_PATH);
            case LegoFolkraceE, LegoFolkraceJH -> backgroundImage = ImageLoader.loadImage(Constants.FORMATION_LEGO_FOLKRACE_PATH);
        }
    }

    private void initComponents() {
        backgroundLabel = new JLabel();
        panels = new FormationResultPanel[numberOfEntries];
        for (int entry : map.keySet()) {
            panels[entry] = new FormationResultPanel(entry, map.get(entry));
        }
        resultPanel = new JPanel();
        scrollPane = new JScrollPane();

        if (backgroundImage != null) {
            backgroundLabel.setIcon(new ImageIcon(backgroundImage));
        }

        resultPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        GUIUtil.setSize(resultPanel,
                new Dimension(
                        (numberOfEntries - 1) * GUIValue.RESULT_INTERVAL + numberOfEntries * GUIValue.RESULT_WIDTH,
                        GUIValue.RESULT_PANEL_HEIGHT
                ));

        for (var panel : panels) {
            resultPanel.add(panel);
        }

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setViewportView(resultPanel);
    }

    private void attachComponents() {
        add(scrollPane);
        scrollPane.setBounds(
                GUIValue.RESULT_PANEL_X, GUIValue.RESULT_PANEL_Y,
                GUIValue.RESULT_PANEL_WIDTH, GUIValue.RESULT_PANEL_HEIGHT
        );

        add(backgroundLabel);
        backgroundLabel.setBounds(
                0, 0,
                backgroundImage.getWidth(), backgroundImage.getHeight()
        );
    }

    private void initFrame() {
        setLayout(null);
        setTitle(GUIString.FORMATION_FRAME_TITLE + " - " + section.toString());
        setResizable(GUIValue.WINDOW_RESIZABLE);
        GUIUtil.setSize(this,
                new Dimension(GUIValue.MAIN_WIDTH, GUIValue.MAIN_HEIGHT));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void showFrame() {
        setVisible(true);
    }

    private void makeEntries() {
        for (int i=0; i<numberOfEntries; i++) {
            map.put(i, new ArrayList<>());
        }

        int i = 0;
        for (var team : data) {
            map.get(i).add(team);
            if (i >= map.size() - 1) {
                i = 0;
                continue;
            }
            i++;
        }


        /*for (int key : map.keySet()) {
            System.out.println(key);
            System.out.println(map.get(key).size());
            for (int j=0; j<map.get(key).size(); j++) {
                System.out.println(map.get(key).get(j));
            }
            System.out.println();
        }*/
    }

    private void shuffleData() {
        Collections.shuffle(data);
    }

}
