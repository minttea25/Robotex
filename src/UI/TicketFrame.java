package UI;

import ConstantValues.*;
import Excel.ExcelWriteManagerTicket;
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

public class TicketFrame extends JFrame {
    boolean stateOk;

    Sections section;
    List<TeamModel> data;

    List<TeamModel> ticket1st = new ArrayList<>();
    List<TeamModel> ticket2nd = new ArrayList<>();
    List<TeamModel> ticket3rd = new ArrayList<>();

    int numberOfTickets;

    CardLayout card;

    JLabel backgroundLabel;
    JPanel resultPanel; // card layout
    JButton nextButton;

    TicketResultPanel panel1st;
    TicketResultPanel panel2nd;
    TicketResultScrollPanel panel3rd;

    BufferedImage backgroundImage;
    BufferedImage nextBtnImage;

    ImageIcon nextBtnIcon;

    ExcelWriteManagerTicket ewmt;

    Set<String> loadFailSet = new HashSet<>();

    public TicketFrame(Sections section, List<TeamModel> data, int numberOfTickets) {
        this.section = section;
        this.data = data;
        this.numberOfTickets = numberOfTickets;

        stateOk = checkNumberOfTickets();

        if (!stateOk) {
            return;
        }

        loadImages();

        shuffleData();
        divideData();

        saveFiles();

        initFrame();
        initComponents();
        attachComponents();

        showCard();

        OptionPaneUtil.showUnloadedImages(loadFailSet, getTicketFrame());
    }

    private boolean checkNumberOfTickets() {
        if (data == null) {
            GUIUtil.setSize(this,
                    new Dimension(GUIValue.MAIN_WIDTH, GUIValue.MAIN_HEIGHT));
            int ok = JOptionPane.showConfirmDialog(
                    getTicketFrame(),
                    ErrorMsg.e022Msg + section,
                    ErrorMsg.error022,
                    JOptionPane.DEFAULT_OPTION
            );
            if (ok == JOptionPane.OK_OPTION) {
                getTicketFrame().dispose();
            }
            return false;
        }
        else if (numberOfTickets > data.size()) {
            GUIUtil.setSize(this,
                    new Dimension(GUIValue.MAIN_WIDTH, GUIValue.MAIN_HEIGHT));
            int ok = JOptionPane.showConfirmDialog(
                    getTicketFrame(),
                    ErrorMsg.e021Msg,
                    ErrorMsg.error021,
                    JOptionPane.DEFAULT_OPTION
            );
            if (ok == JOptionPane.OK_OPTION) {
                getTicketFrame().dispose();
            }
            return false;
        }
        return numberOfTickets <= data.size();
    }

    private void saveFiles() {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(Constants.SAVE_FILE_DATE_FORMAT);
        String fileName = section.toString() + " - " + date + " " + time.format(timeFormatter);
        ewmt = new ExcelWriteManagerTicket(
                section,
                data,
                fileName,
                numberOfTickets
        );

        ewmt.createExcelFile();

        if (!ewmt.isWritten()) {
            System.out.println("Failed to create file");
        }
    }

    public void showFrame() {
        setVisible(stateOk);
        if (!stateOk) {
            dispose();
        }
    }

    private void showCard() {
        card.show(resultPanel, GUIValue.TICKET_1ST_CARD_NAME);
    }

    private void initFrame() {
        card = new CardLayout();

        setLayout(null);
        setTitle(GUIString.TICKET_FRAME_TITLE + " - " + section.toString());
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
        panel1st = new TicketResultPanel(ticket1st, numberOfTickets);
        panel2nd = new TicketResultPanel(ticket2nd, numberOfTickets);
        panel3rd = new TicketResultScrollPanel(ticket3rd, GUIValue.TICKET_PRELIMINARY_LAST_SHOWING_EACH_TEAMS);

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

        resultPanel.add(GUIValue.TICKET_1ST_CARD_NAME, panel1st);
        resultPanel.add(GUIValue.TICKET_2nd_CARD_NAME, panel2nd);
        resultPanel.add(GUIValue.TICKET_3rd_CARD_NAME, panel3rd);
    }

    private void attachComponents() {
        if (ewmt != null && ewmt.isWritten()) {
            add(resultPanel);
            resultPanel.setBounds(
                    GUIValue.RESULT_BOX_X, GUIValue.RESULT_BOX_Y,
                    GUIValue.RESULT_BOX_WIDTH, GUIValue.RESULT_BOX_HEIGHT
            );
        }

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


        add(backgroundLabel);
        if(backgroundImage != null) {
            backgroundLabel.setBounds(
                    0, 0,
                    backgroundImage.getWidth(), backgroundImage.getHeight()
            );
        }
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

    private void shuffleData() {
        Collections.shuffle(data);
    }

    private void loadImages() {
        nextBtnImage = ImageLoader.loadImage(Constants.NEXT_BUTTON_WHITE_PATH);

        switch (section) {
            case LegoSumo1kg -> backgroundImage = ImageLoader.loadImage(Constants.TICKET_LEGO_SUMO_1KG_BG_PATH);
            case LegoSumo3kg -> backgroundImage = ImageLoader.loadImage(Constants.TICKET_LEGO_SUMO_3KG_BG_PATH);
            case LineFollowingE -> backgroundImage = ImageLoader.loadImage(Constants.TICKET_LINE_FOLLOWING_E_BG_PATH);
            case LineFollowingJH -> backgroundImage = ImageLoader.loadImage(Constants.TICKET_LINE_FOLLOWING_JH_BG_PATH);
            case LegoFolkraceE -> backgroundImage = ImageLoader.loadImage(Constants.TICKET_LEGO_FOLKRACE_E_BG_PATH);
            case LegoFolkraceJH -> backgroundImage = ImageLoader.loadImage(Constants.TICKET_LEGO_FOLKRACE_JH_BG_PATH);
        }

        if (nextBtnImage == null)
            loadFailSet.add(Constants.NEXT_BUTTON_WHITE_PATH);
        if (backgroundImage == null) {
            switch (section) {
                case LegoSumo1kg -> loadFailSet.add(Constants.TICKET_LEGO_SUMO_1KG_BG_PATH);
                case LegoSumo3kg -> loadFailSet.add(Constants.TICKET_LEGO_SUMO_3KG_BG_PATH);
                case LineFollowingE -> loadFailSet.add(Constants.TICKET_LINE_FOLLOWING_E_BG_PATH);
                case LineFollowingJH -> loadFailSet.add(Constants.TICKET_LINE_FOLLOWING_JH_BG_PATH);
                case LegoFolkraceE -> loadFailSet.add(Constants.TICKET_LEGO_FOLKRACE_E_BG_PATH);
                case LegoFolkraceJH -> loadFailSet.add(Constants.TICKET_LEGO_FOLKRACE_JH_BG_PATH);

            }
        }
    }

    private TicketFrame getTicketFrame() {
        return this;
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
