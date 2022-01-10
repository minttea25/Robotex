package UI;

import ConstantValues.Constants;
import ConstantValues.GUIString;
import ConstantValues.GUIValue;
import ConstantValues.Sections;
import Excel.ExcelWriteManagerFormation;
import Excel.ExcelWriteManagerTicket;
import Model.TeamModel;
import Util.GUIUtil;
import Util.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TicketFrame extends JFrame {
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
    TicketResultPanel panel3rd;

    BufferedImage backgroundImage;

    ExcelWriteManagerTicket ewmt;

    public TicketFrame(Sections section, List<TeamModel> data, int numberOfTickets) {
        this.section = section;
        this.data = data;
        this.numberOfTickets = numberOfTickets;

        loadImages();

        shuffleData();
        divideData();

        saveFiles();

        initFrame();
        initComponents();
        attachComponents();

        showCard();
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
        setVisible(true);
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
        panel3rd = new TicketResultPanel(ticket3rd, numberOfTickets);


        if (backgroundImage != null) {
            backgroundLabel.setIcon(new ImageIcon(backgroundImage));
        }

        nextButton.setText(GUIString.NEXT);
        nextButton.addActionListener(new NextButtonActionListener());

        resultPanel.setLayout(card);

        resultPanel.add(GUIValue.TICKET_1ST_CARD_NAME, panel1st);
        resultPanel.add(GUIValue.TICKET_2nd_CARD_NAME, panel2nd);
        resultPanel.add(GUIValue.TICKET_3rd_CARD_NAME, panel3rd);
    }

    private void attachComponents() {
        add(resultPanel);
        resultPanel.setBounds(
                GUIValue.RESULT_PANEL_X, GUIValue.RESULT_PANEL_Y,
                GUIValue.RESULT_PANEL_WIDTH, GUIValue.RESULT_PANEL_HEIGHT
        );

        add(nextButton);
        nextButton.setBounds(
                GUIValue.RESULT_PANEL_X + GUIValue.RESULT_PANEL_WIDTH - GUIValue.NEXT_BUTTON_WIDTH,
                GUIValue.RESULT_PANEL_Y + GUIValue.RESULT_PANEL_HEIGHT + 20,
                GUIValue.NEXT_BUTTON_WIDTH, GUIValue.NEXT_BUTTON_HEIGHT
        );

        add(backgroundLabel);
        backgroundLabel.setBounds(
                0, 0,
                backgroundImage.getWidth(), backgroundImage.getHeight()
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

    private void shuffleData() {
        Collections.shuffle(data);
    }

    private void loadImages() {
        switch (section) {
            case LegoSumo1kg, LegoSumo3kg -> backgroundImage = ImageLoader.loadImage(Constants.TICKET_LEGO_SUMO_PATH);
            case LineFollowingE, LineFollowingJH -> backgroundImage = ImageLoader.loadImage(Constants.TICKET_LINE_FOLLOWING_PATH);
            case LegoFolkraceE, LegoFolkraceJH -> backgroundImage = ImageLoader.loadImage(Constants.TICKET_LEGO_FOLKRACE_PATH);
        }
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
