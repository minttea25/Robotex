package UI;

import ConstantValues.*;
import Excel.ExcelWriteManagerFormation;
import Model.TeamModel;
import Util.GUIUtil;
import Util.ImageLoader;
import Util.OptionPaneUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

public class FormationFrame extends JFrame {
    boolean stateOk;

    Sections section;
    List<TeamModel> data;
    Map<Integer, List<TeamModel>> map = new HashMap<>();

    int numberOfEntries;
    int numberOfShowingCards;

    CardLayout card;
    String[] cardNames;

    JLabel backgroundLabel;
    JPanel resultPanel; // card layout
    JButton nextButton;

    FormationResultPanel[] panels;

    BufferedImage backgroundImage;
    BufferedImage nextBtnImage;

    ImageIcon nextBtnIcon;

    ExcelWriteManagerFormation ewmf;

    Set<String> loadFailSet = new HashSet<>();

    public FormationFrame(Sections section, List<TeamModel> data, int numberOfEntries) {
        this.section = section;
        this.data = data;
        this.numberOfEntries = numberOfEntries;

        stateOk = checkNumberOfEntries();
        if (!stateOk) {
            return;
        }

        loadImages();

        shuffleData();
        makeEntries();

        saveFile();

        initFrame();
        initComponents();
        attachComponents();

        showCard();

        OptionPaneUtil.showUnloadedImages(loadFailSet, getFormationFrame());
    }

    private boolean checkNumberOfEntries() {
        if (data == null) {
            setVisible(false);
            GUIUtil.setSize(this,
                    new Dimension(GUIValue.MAIN_WIDTH, GUIValue.MAIN_HEIGHT));
            int ok =JOptionPane.showConfirmDialog(
                    getFormationFrame(),
                    ErrorMsg.e012Msg + section,
                    ErrorMsg.error012,
                    JOptionPane.DEFAULT_OPTION
            );
            if (ok == JOptionPane.OK_OPTION) {
                getFormationFrame().dispose();
            }
            return false;
        }
        else if (data.size() < numberOfEntries) {
            GUIUtil.setSize(this,
                    new Dimension(GUIValue.MAIN_WIDTH, GUIValue.MAIN_HEIGHT));
            int ok = JOptionPane.showConfirmDialog(
                    getFormationFrame(),
                    ErrorMsg.e011Msg,
                    ErrorMsg.error011,
                    JOptionPane.DEFAULT_OPTION
            );
            if (ok == JOptionPane.OK_OPTION) {
                getFormationFrame().dispose();
            }
            return false;
        }
        return data.size() >= numberOfEntries;
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
            //System.out.println("Failed to create file");
        }
    }

    private void loadImages() {
        nextBtnImage = ImageLoader.loadImage(Constants.NEXT_BUTTON_RED_PATH);

        switch (section) {
            case LegoSumo1kg -> backgroundImage = ImageLoader.loadImage(Constants.FORMATION_LEGO_SUMO_1KG_BG_PATH);
            case LegoSumo3kg -> backgroundImage = ImageLoader.loadImage(Constants.FORMATION_LEGO_SUMO_3KG_BG_PATH);
            case LineFollowingE -> backgroundImage = ImageLoader.loadImage(Constants.FORMATION_LINE_FOLLOWING_E_BG_PATH);
            case LineFollowingJH -> backgroundImage = ImageLoader.loadImage(Constants.FORMATION_LINE_FOLLOWING_JH_BG_PATH);
            case LegoFolkraceE -> backgroundImage = ImageLoader.loadImage(Constants.FORMATION_LEGO_FOLKRACE_E_BG_PATH);
            case LegoFolkraceJH -> backgroundImage = ImageLoader.loadImage(Constants.FORMATION_LEGO_FOLKRACE_JH_BG_PATH);
        }

        if (nextBtnImage == null)
            loadFailSet.add(Constants.NEXT_BUTTON_RED_PATH);
        if (backgroundImage == null) {
            switch (section) {
                case LegoSumo1kg -> loadFailSet.add(Constants.FORMATION_LEGO_SUMO_1KG_BG_PATH);
                case LegoSumo3kg -> loadFailSet.add(Constants.FORMATION_LEGO_SUMO_3KG_BG_PATH);
                case LineFollowingE -> loadFailSet.add(Constants.FORMATION_LINE_FOLLOWING_E_BG_PATH);
                case LineFollowingJH -> loadFailSet.add(Constants.FORMATION_LINE_FOLLOWING_JH_BG_PATH);
                case LegoFolkraceE -> loadFailSet.add(Constants.FORMATION_LEGO_FOLKRACE_E_BG_PATH);
                case LegoFolkraceJH -> loadFailSet.add(Constants.FORMATION_LEGO_FOLKRACE_JH_BG_PATH);

            }
        }
    }

    private void initFrame() {
        card = new CardLayout();

        setLayout(null);
        setTitle(GUIString.FORMATION_FRAME_TITLE + " - " + section.toString());
        setResizable(GUIValue.WINDOW_RESIZABLE);
        GUIUtil.setSize(this,
                new Dimension(GUIValue.MAIN_WIDTH, GUIValue.MAIN_HEIGHT));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        backgroundLabel = new JLabel();
        resultPanel = new JPanel();
        nextButton = new JButton();
        panels = new FormationResultPanel[numberOfShowingCards];

        int index = 0;
        for (int i=0; i<numberOfShowingCards; i++) {
            Object[] keyArr = map.keySet().toArray();

            Map<Integer, List<TeamModel>> data = new HashMap<>();
            for (int j=0; j<GUIValue.FORMATION_SHOWING_NUMBERS_OF_TEAMS_EACH_PANEL; j++) {
                if (index >= map.size()) {
                    break;
                }
                data.put((int) keyArr[index], map.get(keyArr[index]));
                index++;
            }
            panels[i] = new FormationResultPanel(data);
        }

        if (backgroundImage != null) {
            backgroundLabel.setIcon(new ImageIcon(backgroundImage));
        }

        if (nextBtnImage != null) {
            nextButton.setIcon(nextBtnIcon = new ImageIcon(nextBtnImage));
        }
        else {
            nextButton.setText(GUIString.NEXT);
        }
        GUIUtil.makeButtonForImage(nextButton);
        nextButton.addActionListener(new NextButtonActionListener());

        resultPanel.setLayout(card);
        for (int i=0; i<panels.length; i++){
            String cardName = GUIValue.FORMATION_BASE_CARD_NAME + i;
            resultPanel.add(cardName, panels[i]);
            cardNames[i] = cardName;
        }
    }

    private void attachComponents() {
        if (ewmf != null && ewmf.isWritten()) {
            add(resultPanel);
            resultPanel.setBounds(
                    GUIValue.RESULT_BOX_X, GUIValue.RESULT_BOX_Y,
                    GUIValue.RESULT_BOX_WIDTH, GUIValue.RESULT_BOX_HEIGHT
            );
        }
        if (! (numberOfShowingCards <= 1)) {
            add(nextButton);
            if (nextBtnImage != null) {
                nextButton.setBounds(
                        GUIValue.RESULT_BOX_X + GUIValue.RESULT_BOX_WIDTH - nextBtnIcon.getIconWidth(),
                        GUIValue.RESULT_BOX_Y + GUIValue.RESULT_BOX_HEIGHT + 10,
                        nextBtnIcon.getIconWidth(), nextBtnIcon.getIconHeight()
                );
            }
            else {
                nextButton.setBounds(
                        GUIValue.RESULT_BOX_X + GUIValue.RESULT_BOX_WIDTH - GUIValue.NEXT_BUTTON_WIDTH/4,
                        GUIValue.RESULT_BOX_Y + GUIValue.RESULT_BOX_HEIGHT + 15,
                        GUIValue.NEXT_BUTTON_WIDTH, GUIValue.NEXT_BUTTON_HEIGHT
                );
                nextButton.setText(GUIString.NEXT);
            }
        }


        add(backgroundLabel);
        if (backgroundImage != null) {
            backgroundLabel.setBounds(
                    0, 0,
                    backgroundImage.getWidth(), backgroundImage.getHeight()
            );
        }
    }

    private void showCard() {
        if (cardNames != null) {
            card.show(resultPanel, cardNames[0]);
        }
        else {
            //System.out.println("CardName is null");
        }
    }



    public void showFrame() {
        setVisible(stateOk);
        if (!stateOk) {
            dispose();
        }
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

        this.numberOfShowingCards = (int) (Math.ceil(map.size() / (double)GUIValue.FORMATION_SHOWING_NUMBERS_OF_TEAMS_EACH_PANEL));
        this.cardNames = new String[this.numberOfShowingCards];

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

    private FormationFrame getFormationFrame() {
        return this;
    }

    private void showNextCard() {
        card.next(resultPanel);
    }


    class NextButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object obj = e.getSource();

            if (obj == nextButton) {
                showNextCard();
            }
        }
    }
}
