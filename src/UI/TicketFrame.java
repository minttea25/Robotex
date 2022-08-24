package UI;

import ConstantValues.*;
import Model.TeamModel;
import Util.GUIUtil;
import Util.ImageLoader;
import Util.OptionPaneUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class TicketFrame extends JFrame {
    CountDownPanel countDownPanel;
    JPanel contentPanel;

    Sections section;
    List<TeamModel> data;

    List<TeamModel> ticket1st = new ArrayList<>();
    List<TeamModel> ticket2nd = new ArrayList<>();
    List<TeamModel> ticket3rd = new ArrayList<>();

    int numberOfTickets;
    int numberOfPanels;
    int numberOf3rdPanels;

    final int NUMBER_OF_TEAMS_ON_3RD_PANEL = GUIValue.TICKET_PRELIMINARY_LAST_SHOWING_EACH_TEAMS*GUIValue.TICKET_SHOWING_NUMBERS_OF_TEAMS_EACH_PANEL;

    CardLayout mainCard;

    CardLayout card;

    JLabel backgroundLabel;
    JPanel resultPanel; // card layout
    //JButton nextButton;

    TicketResultPanel[] panels;

    BufferedImage backgroundImage;

    Set<String> loadFailSet = new HashSet<>();

    public TicketFrame(Sections section, List<TeamModel> data, int numberOfTickets) {
        this.section = section;
        this.data = data;
        this.numberOfTickets = numberOfTickets;

        if (data.size() > numberOfTickets*3) {
            this.numberOf3rdPanels = (int) Math.ceil(
                    (double)(data.size() - 3*numberOfTickets)/NUMBER_OF_TEAMS_ON_3RD_PANEL);
        }
        else {
            this.numberOf3rdPanels = 0;
        }

        this.numberOfPanels = (data.size() > numberOfTickets ? 2 : 1) + numberOf3rdPanels;

        loadImages();

        divideData();

        initFrame();
        initComponents();
        attachComponents();

        OptionPaneUtil.showUnloadedImages(loadFailSet, getTicketFrame());
    }

    public void showFrame() {
        mainCard.show(getTicketFrame().getContentPane(), GUIValue.TICKET_COUNT_CARD_NAME);
        setVisible(true);

        countDownPanel.StartTimer();
    }

    private void initFrame() {
        mainCard = new CardLayout();
        card = new CardLayout();

        setLayout(mainCard);
        setTitle(GUIString.TICKET_FRAME_TITLE + " - " + section.toString());
        setResizable(GUIValue.WINDOW_RESIZABLE);
        GUIUtil.setSize(this,
                new Dimension(GUIValue.MAIN_WIDTH, GUIValue.MAIN_HEIGHT));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        countDownPanel = new CountDownPanel(this.getTicketFrame());
        contentPanel = new JPanel();

        contentPanel.setLayout(null);

        backgroundLabel = new JLabel();
        resultPanel = new JPanel();
        //nextButton = new JButton();
        panels = new TicketResultPanel[numberOfPanels];

        int idx = 0;
        for (int i=0; i<numberOfPanels; i++) {
            if (i==0) {
                panels[i] = new TicketResultPanel(ticket1st, 1);
            }
            else if (i==1) {
                panels[i] = new TicketResultPanel(ticket2nd, 2);
            }
            else {
                List<TeamModel> t = new ArrayList<>();
                for (int j=0; j<NUMBER_OF_TEAMS_ON_3RD_PANEL; j++) {
                    if (idx >= ticket3rd.size()) {
                        break;
                    }
                    t.add(ticket3rd.get(idx++));
                }
                panels[i] = new TicketResultPanel(t, 3);
            }
        }

        if (backgroundImage != null) {
            backgroundLabel.setIcon(new ImageIcon(backgroundImage));
        }

        //GUIUtil.makeButtonTransparent(nextButton);
        //nextButton.addActionListener(new NextButtonActionListener());

        resultPanel.setLayout(card);

        for (int i=0; i<numberOfPanels; i++) {
            resultPanel.add(String.valueOf(i), panels[i]);
            panels[i].addMouseListener(new NextCheckMouseListener());
        }
    }

    private void attachComponents() {
        /*contentPanel.add(nextButton);
        nextButton.setBounds(
                GUIValue.RESULT_BOX_X,
                GUIValue.RESULT_BOX_Y,
                GUIValue.RESULT_BOX_WIDTH/2, GUIValue.RESULT_BOX_HEIGHT
        );*/

        contentPanel.add(resultPanel);
        resultPanel.setBounds(
                GUIValue.RESULT_BOX_X, GUIValue.RESULT_BOX_Y,
                GUIValue.RESULT_BOX_WIDTH, GUIValue.RESULT_BOX_HEIGHT
        );

        contentPanel.add(backgroundLabel);
        if(backgroundImage != null) {
            backgroundLabel.setBounds(
                    0, 0,
                    backgroundImage.getWidth(), backgroundImage.getHeight()
            );
        }

        add(GUIValue.TICKET_CONTENT_CARD_NAME, contentPanel);
        contentPanel.setBounds(
                0, 0,
                getWidth(), getHeight()
        );

        add(GUIValue.TICKET_COUNT_CARD_NAME, countDownPanel);
        countDownPanel.setBounds(
                0, 0,
                this.getWidth(), this.getHeight()
        );
    }

    private void divideData() {
        for (int i=0; i<data.size(); i++) {
            if (i < numberOfTickets) {
                ticket1st.add(data.get(i));
            }
            else if (i < numberOfTickets * 3) {
                ticket2nd.add(data.get(i));
            }
            else {
                ticket3rd.add(data.get(i));
            }
        }
    }

    private void showNextCard() {
        card.next(resultPanel);
    }

    private void loadImages() {
        switch (section) {
            case LegoSumo1kg : backgroundImage = ImageLoader.loadImage(Constants.TICKET_LEGO_SUMO_1KG_BG_PATH); break;
            case LegoSumo3kg : backgroundImage = ImageLoader.loadImage(Constants.TICKET_LEGO_SUMO_3KG_BG_PATH); break;
            case LineFollowingE : backgroundImage = ImageLoader.loadImage(Constants.TICKET_LINE_FOLLOWING_E_BG_PATH); break;
            case LineFollowingJH : backgroundImage = ImageLoader.loadImage(Constants.TICKET_LINE_FOLLOWING_JH_BG_PATH); break;
            case LegoFolkraceE : backgroundImage = ImageLoader.loadImage(Constants.TICKET_LEGO_FOLKRACE_E_BG_PATH); break;
            case LegoFolkraceJH : backgroundImage = ImageLoader.loadImage(Constants.TICKET_LEGO_FOLKRACE_JH_BG_PATH); break;
        }

        /*switch (section) {
            case LegoSumo1kg -> backgroundImage = ImageLoader.loadImage(Constants.TICKET_LEGO_SUMO_1KG_BG_PATH);
            case LegoSumo3kg -> backgroundImage = ImageLoader.loadImage(Constants.TICKET_LEGO_SUMO_3KG_BG_PATH);
            case LineFollowingE -> backgroundImage = ImageLoader.loadImage(Constants.TICKET_LINE_FOLLOWING_E_BG_PATH);
            case LineFollowingJH -> backgroundImage = ImageLoader.loadImage(Constants.TICKET_LINE_FOLLOWING_JH_BG_PATH);
            case LegoFolkraceE -> backgroundImage = ImageLoader.loadImage(Constants.TICKET_LEGO_FOLKRACE_E_BG_PATH);
            case LegoFolkraceJH -> backgroundImage = ImageLoader.loadImage(Constants.TICKET_LEGO_FOLKRACE_JH_BG_PATH);
        }*/

        if (backgroundImage == null) {
            switch (section) {
                case LegoSumo1kg : loadFailSet.add(Constants.TICKET_LEGO_SUMO_1KG_BG_PATH); break;
                case LegoSumo3kg : loadFailSet.add(Constants.TICKET_LEGO_SUMO_3KG_BG_PATH); break;
                case LineFollowingE : loadFailSet.add(Constants.TICKET_LINE_FOLLOWING_E_BG_PATH); break;
                case LineFollowingJH : loadFailSet.add(Constants.TICKET_LINE_FOLLOWING_JH_BG_PATH); break;
                case LegoFolkraceE : loadFailSet.add(Constants.TICKET_LEGO_FOLKRACE_E_BG_PATH); break;
                case LegoFolkraceJH : loadFailSet.add(Constants.TICKET_LEGO_FOLKRACE_JH_BG_PATH); break;

            }
        }

        /*if (backgroundImage == null) {
            switch (section) {
                case LegoSumo1kg -> loadFailSet.add(Constants.TICKET_LEGO_SUMO_1KG_BG_PATH);
                case LegoSumo3kg -> loadFailSet.add(Constants.TICKET_LEGO_SUMO_3KG_BG_PATH);
                case LineFollowingE -> loadFailSet.add(Constants.TICKET_LINE_FOLLOWING_E_BG_PATH);
                case LineFollowingJH -> loadFailSet.add(Constants.TICKET_LINE_FOLLOWING_JH_BG_PATH);
                case LegoFolkraceE -> loadFailSet.add(Constants.TICKET_LEGO_FOLKRACE_E_BG_PATH);
                case LegoFolkraceJH -> loadFailSet.add(Constants.TICKET_LEGO_FOLKRACE_JH_BG_PATH);

            }
        }*/
    }

    protected void showContentPanel() {
        mainCard.show(getTicketFrame().getContentPane(), GUIValue.TICKET_CONTENT_CARD_NAME);
        remove(countDownPanel);
        card.show(resultPanel, "0");
    }

    private TicketFrame getTicketFrame() {
        return this;
    }

    class GifCloseThread extends Thread {

        @Override
        public void run() {
            try {
                mainCard.show(getTicketFrame().getContentPane(), GUIValue.TICKET_COUNT_CARD_NAME);
                Thread.sleep(Constants.COUNTDOWN_CLOSE_TIME);
                mainCard.show(getTicketFrame().getContentPane(), GUIValue.TICKET_CONTENT_CARD_NAME);
                remove(countDownPanel);

                card.show(resultPanel, String.valueOf(0));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /*class NextButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object obj = e.getSource();

            if (obj == nextButton) {
                showNextCard();
            }
        }
    }*/

    class NextCheckMouseListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            showNextCard();
            /*int x = e.getX();
            int y = e.getY();
            System.out.println("Clicked: " + x + " " + y);
            if (x>=GUIValue.RESULT_BOX_X && x <= GUIValue.RESULT_BOX_X+GUIValue.RESULT_BOX_WIDTH
                && y >= GUIValue.RESULT_BOX_Y && y <= GUIValue.RESULT_BOX_Y+GUIValue.RESULT_BOX_WIDTH) {
                showNextCard();
            }*/
        }
    }
}
