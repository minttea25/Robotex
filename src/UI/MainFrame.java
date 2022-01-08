package UI;

import ConstantValues.GUIString;
import ConstantValues.GUIValue;
import ConstantValues.Sections;
import Model.ProgramFunctions;
import Setup.Setup;
import Util.GUIUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private Setup formationSetup;
    private Setup ticketSetup;

    private CardLayout card;

    private MainPanel mainPanel;
    private MenuPanel formationPanel;
    private MenuPanel ticketPanel;

    public MainFrame() {
        formationSetup = new Setup(ProgramFunctions.Formation);
        ticketSetup = new Setup(ProgramFunctions.Ticket);

        initFrame();
        initComponents();
        attachComponents();
    }

    public void showFrame() {
        card.show(getContentPane(), GUIValue.MAIN_CARD_NAME);
        setVisible(true);
    }

    private void attachComponents() {
        add(GUIValue.MAIN_CARD_NAME, mainPanel);
        add(GUIValue.FORMATION_CARD_NAME, formationPanel);
        add(GUIValue.TICKET_CARD_NAME, ticketPanel);
    }

    private void initFrame() {
        card = new CardLayout();

        setLayout(card);
        setTitle(GUIString.MAIN_FRAME_TITLE);
        setResizable(GUIValue.WINDOW_RESIZABLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GUIUtil.setSize(this,
                new Dimension(GUIValue.MAIN_WIDTH, GUIValue.MAIN_HEIGHT));
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        initMainPanel();
        initFormationPanel();
        initTicketPanel();
    }

    private void initMainPanel() {
        mainPanel = new MainPanel();
        mainPanel.addButtonActionListener(new MainPanelActionListener());
    }

    private void initFormationPanel() {
        formationPanel = new MenuPanel(ProgramFunctions.Formation);
        formationPanel.addButtonActionListener(new FormationPanelActionListener());
    }

    private void initTicketPanel() {
        ticketPanel = new MenuPanel(ProgramFunctions.Ticket);
        ticketPanel.addButtonActionListener(new TicketPanelActionListener());
    }

    private JFrame getMainFrame() {
        return this;
    }

    class MainPanelActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object obj = e.getSource();

            if (obj == mainPanel.formationBtn) {
                if (formationSetup.isExcelFileLoaded()) {
                    card.show(getContentPane(), GUIValue.FORMATION_CARD_NAME);
                }
                else {
                    System.out.println("The excel file is not loaded, load first.");
                }
            }
            else if (obj == mainPanel.formationSetupBtn) {
                SetupDialog dialog = new SetupDialog(ProgramFunctions.Formation, getMainFrame(), formationSetup);
                dialog.showDialog();
            }
            else if (obj == mainPanel.ticketBtn) {
                if (ticketSetup.isExcelFileLoaded()) {
                    card.show(getContentPane(), GUIValue.TICKET_CARD_NAME);
                }
                else {
                    System.out.println("The excel file is not loaded, load first.");
                }
            }
            else if (obj == mainPanel.ticketSetupBtn) {
                SetupDialog dialog = new SetupDialog(ProgramFunctions.Ticket, getMainFrame(), ticketSetup);
                dialog.showDialog();
            }
        }
    }

    class FormationPanelActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object obj = e.getSource();

            if (obj == formationPanel.legoSumo1kgBtn) {
                Sections s = Sections.LegoSumo1kg;
                FormationFrame frame = new FormationFrame(
                        s,
                        formationSetup.getTeamDataBySection(s),
                        formationSetup.getSetupDataModel().getValueBySection(s)
                );

                frame.showFrame();
            }
            else if (obj == formationPanel.legoSumo3kgBtn) {
                Sections s = Sections.LegoSumo3kg;
                FormationFrame frame = new FormationFrame(
                        s,
                        formationSetup.getTeamDataBySection(s),
                        formationSetup.getSetupDataModel().getValueBySection(s)
                );

                frame.showFrame();
            }
            else if (obj == formationPanel.lineFollowingEBtn) {
                Sections s = Sections.LineFollowingE;
                FormationFrame frame = new FormationFrame(
                        s,
                        formationSetup.getTeamDataBySection(s),
                        formationSetup.getSetupDataModel().getValueBySection(s)
                );

                frame.showFrame();
            }
            else if (obj == formationPanel.lineFollowingJHBtn) {
                Sections s = Sections.LineFollowingJH;
                FormationFrame frame = new FormationFrame(
                        s,
                        formationSetup.getTeamDataBySection(s),
                        formationSetup.getSetupDataModel().getValueBySection(s)
                );

                frame.showFrame();
            }
            else if (obj == formationPanel.legoFolkraceEBtn) {
                Sections s = Sections.LegoFolkraceE;
                FormationFrame frame = new FormationFrame(
                        s,
                        formationSetup.getTeamDataBySection(s),
                        formationSetup.getSetupDataModel().getValueBySection(s)
                );

                frame.showFrame();
            }
            else if (obj == formationPanel.legoFolkraceJHBtn) {
                Sections s = Sections.LegoFolkraceJH;
                FormationFrame frame = new FormationFrame(
                        s,
                        formationSetup.getTeamDataBySection(s),
                        formationSetup.getSetupDataModel().getValueBySection(s)
                );

                frame.showFrame();
            }
        }
    }

    class TicketPanelActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object obj = e.getSource();

            if (obj == ticketPanel.legoSumo1kgBtn) {

            }
            else if (obj == ticketPanel.legoSumo3kgBtn) {

            }
            else if (obj == ticketPanel.lineFollowingEBtn) {

            }
            else if (obj == ticketPanel.lineFollowingJHBtn) {

            }
            else if (obj == ticketPanel.legoFolkraceEBtn) {

            }
            else if (obj == ticketPanel.legoFolkraceJHBtn) {

            }
        }
    }
}
