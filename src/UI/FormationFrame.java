package UI;

import ConstantValues.*;
import Model.TeamModel;
import Util.GUIUtil;
import Util.ImageLoader;
import Util.OptionPaneUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class FormationFrame extends JFrame {
    Sections section;
    Map<Integer, List<TeamModel>> map;

    int numberOfEntries;
    int numberOfShowingCards;

    CardLayout card;
    String[] cardNames;

    JLabel backgroundLabel;
    JPanel resultPanel; // card layout
    JButton nextButton;

    FormationResultPanel[] panels;

    BufferedImage backgroundImage;

    Set<String> loadFailSet = new HashSet<>();

    public FormationFrame(Sections section, Map<Integer, List<TeamModel>> map) {
        this.section = section;
        this.map = map;
        this.numberOfEntries = map.size();

        this.numberOfShowingCards = (int) (Math.ceil(map.size() / (double)GUIValue.FORMATION_SHOWING_NUMBERS_OF_TEAMS_EACH_PANEL));
        this.cardNames = new String[this.numberOfShowingCards];

        loadImages();

        initFrame();
        initComponents();
        attachComponents();

        showCard();

        OptionPaneUtil.showUnloadedImages(loadFailSet, getFormationFrame());
    }

    private void loadImages() {
        switch (section) {
            case LegoSumo1kg -> backgroundImage = ImageLoader.loadImage(Constants.FORMATION_LEGO_SUMO_1KG_BG_PATH);
            case LegoSumo3kg -> backgroundImage = ImageLoader.loadImage(Constants.FORMATION_LEGO_SUMO_3KG_BG_PATH);
            case LineFollowingE -> backgroundImage = ImageLoader.loadImage(Constants.FORMATION_LINE_FOLLOWING_E_BG_PATH);
            case LineFollowingJH -> backgroundImage = ImageLoader.loadImage(Constants.FORMATION_LINE_FOLLOWING_JH_BG_PATH);
            case LegoFolkraceE -> backgroundImage = ImageLoader.loadImage(Constants.FORMATION_LEGO_FOLKRACE_E_BG_PATH);
            case LegoFolkraceJH -> backgroundImage = ImageLoader.loadImage(Constants.FORMATION_LEGO_FOLKRACE_JH_BG_PATH);
        }

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
            Map<Integer, List<TeamModel>> data = new HashMap<>();
            for (int j=0; j<GUIValue.FORMATION_SHOWING_NUMBERS_OF_TEAMS_EACH_PANEL; j++) {
                if (index >= map.size()) {
                    break;
                }
                data.put(index, map.get(index));
                index++;
            }
            panels[i] = new FormationResultPanel(data);
        }

        if (backgroundImage != null) {
            backgroundLabel.setIcon(new ImageIcon(backgroundImage));
        }
        GUIUtil.makeButtonTransparent(nextButton);
        nextButton.addActionListener(new NextButtonActionListener());

        resultPanel.setLayout(card);
        for (int i=0; i<panels.length; i++){
            String cardName = GUIValue.FORMATION_BASE_CARD_NAME + i;
            resultPanel.add(cardName, panels[i]);
            cardNames[i] = cardName;
        }
    }

    private void attachComponents() {
        add(resultPanel);
        resultPanel.setBounds(
                GUIValue.RESULT_BOX_X, GUIValue.RESULT_BOX_Y,
                GUIValue.RESULT_BOX_WIDTH, GUIValue.RESULT_BOX_HEIGHT
        );

        if (! (numberOfShowingCards <= 1)) {
            add(nextButton);
            nextButton.setBounds(
                    GUIValue.RESULT_BOX_X,
                    GUIValue.RESULT_BOX_Y,
                    GUIValue.RESULT_BOX_WIDTH, GUIValue.RESULT_BOX_HEIGHT
            );
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
        setVisible(true);
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
