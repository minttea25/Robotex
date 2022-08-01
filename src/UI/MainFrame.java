package UI;

import ConstantValues.*;
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
                    //System.out.println("The excel file is not loaded, load first.");
                    JOptionPane.showConfirmDialog(
                            getMainFrame(),
                            ErrorMsg.e031Msg,
                            ErrorMsg.error031,
                            JOptionPane.DEFAULT_OPTION
                    );
                }
            }
            else if (obj == mainPanel.formationSetupBtn) {
                formationSetup = new Setup(ProgramFunctions.Formation);
                SetupDialog dialog = new SetupDialog(ProgramFunctions.Formation, getMainFrame(), formationSetup);
                dialog.showDialog();
            }
            else if (obj == mainPanel.ticketBtn) {
                if (ticketSetup.isExcelFileLoaded()) {
                    card.show(getContentPane(), GUIValue.TICKET_CARD_NAME);
                }
                else {
                    //System.out.println("The excel file is not loaded, load first.");
                    JOptionPane.showConfirmDialog(
                            getMainFrame(),
                            ErrorMsg.e031Msg,
                            ErrorMsg.error031,
                            JOptionPane.DEFAULT_OPTION
                    );
                }
            }
            else if (obj == mainPanel.ticketSetupBtn) {
                ticketSetup = new Setup(ProgramFunctions.Ticket);
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
                if (!checkStatus(ProgramFunctions.Formation, s)) {
                    return;
                }
                FormationFrame frame = new FormationFrame(
                        s,
                        formationSetup.getEntryMaps().get(s)
                );

                frame.showFrame();
            }
            else if (obj == formationPanel.legoSumo3kgBtn) {
                Sections s = Sections.LegoSumo3kg;
                if (!checkStatus(ProgramFunctions.Formation, s)) {
                    return;
                }
                FormationFrame frame = new FormationFrame(
                        s,
                        formationSetup.getEntryMaps().get(s)
                );

                frame.showFrame();
            }
            else if (obj == formationPanel.lineFollowingEBtn) {
                Sections s = Sections.LineFollowingE;
                if (!checkStatus(ProgramFunctions.Formation, s)) {
                    return;
                }
                FormationFrame frame = new FormationFrame(
                        s,
                        formationSetup.getEntryMaps().get(s)
                );

                frame.showFrame();
            }
            else if (obj == formationPanel.lineFollowingJHBtn) {
                Sections s = Sections.LineFollowingJH;
                if (!checkStatus(ProgramFunctions.Formation, s)) {
                    return;
                }
                FormationFrame frame = new FormationFrame(
                        s,
                        formationSetup.getEntryMaps().get(s)
                );

                frame.showFrame();
            }
            else if (obj == formationPanel.legoFolkraceEBtn) {
                Sections s = Sections.LegoFolkraceE;
                if (!checkStatus(ProgramFunctions.Formation, s)) {
                    return;
                }
                FormationFrame frame = new FormationFrame(
                        s,
                        formationSetup.getEntryMaps().get(s)
                );

                frame.showFrame();
            }
            else if (obj == formationPanel.legoFolkraceJHBtn) {
                Sections s = Sections.LegoFolkraceJH;
                if (!checkStatus(ProgramFunctions.Formation, s)) {
                    return;
                }
                FormationFrame frame = new FormationFrame(
                        s,
                        formationSetup.getEntryMaps().get(s)
                );

                frame.showFrame();
            }
            else if (obj == formationPanel.backBtn) {
                card.show(getContentPane(), GUIValue.MAIN_CARD_NAME);
            }
        }
    }

    class TicketPanelActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object obj = e.getSource();

            if (obj == ticketPanel.legoSumo1kgBtn) {
                Sections s = Sections.LegoSumo1kg;
                TicketFrame frame = new TicketFrame(
                        s,
                        ticketSetup.getTeamDataBySection(s),
                        ticketSetup.getSetupDataModel().getValueBySection(s)
                );
                frame.showFrame();
            }
            else if (obj == ticketPanel.legoSumo3kgBtn) {
                Sections s = Sections.LegoSumo3kg;
                TicketFrame frame = new TicketFrame(
                        s,
                        ticketSetup.getTeamDataBySection(s),
                        ticketSetup.getSetupDataModel().getValueBySection(s)
                );
                frame.showFrame();
            }
            else if (obj == ticketPanel.lineFollowingEBtn) {
                Sections s = Sections.LineFollowingE;
                TicketFrame frame = new TicketFrame(
                        s,
                        ticketSetup.getTeamDataBySection(s),
                        ticketSetup.getSetupDataModel().getValueBySection(s)
                );
                frame.showFrame();
            }
            else if (obj == ticketPanel.lineFollowingJHBtn) {
                Sections s = Sections.LineFollowingJH;
                TicketFrame frame = new TicketFrame(
                        s,
                        ticketSetup.getTeamDataBySection(s),
                        ticketSetup.getSetupDataModel().getValueBySection(s)
                );
                frame.showFrame();
            }
            else if (obj == ticketPanel.legoFolkraceEBtn) {
                Sections s = Sections.LegoFolkraceE;
                TicketFrame frame = new TicketFrame(
                        s,
                        ticketSetup.getTeamDataBySection(s),
                        ticketSetup.getSetupDataModel().getValueBySection(s)
                );
                frame.showFrame();
            }
            else if (obj == ticketPanel.legoFolkraceJHBtn) {
                Sections s = Sections.LegoFolkraceJH;
                TicketFrame frame = new TicketFrame(
                        s,
                        ticketSetup.getTeamDataBySection(s),
                        ticketSetup.getSetupDataModel().getValueBySection(s)
                );
                frame.showFrame();
            }
            else if (obj == ticketPanel.backBtn) {
                card.show(getContentPane(), GUIValue.MAIN_CARD_NAME);
            }
        }
    }

    private boolean checkStatus(ProgramFunctions fun, Sections s) {
        if(fun == ProgramFunctions.Formation) {
            // not load data
            if (!formationSetup.getStatus().containsKey(s)) {
                return false;
            }
            // wrong num
            else if (!formationSetup.getStatus().get(s)) {
                JOptionPane.showConfirmDialog(
                        getMainFrame(),
                        ErrorMsg.e011Msg + ": " + s.toString() + "\ndata size: " + formationSetup.getTeamDataBySection(s).size()
                                + "\nnow: " + formationSetup.getSetupDataModel().getValueBySection(s),
                        ErrorMsg.error011,
                        JOptionPane.DEFAULT_OPTION
                );
                return false;
            }
        }
        else {
            // not load data
            if (!ticketSetup.getStatus().containsKey(s)) {
                return false;
            }
            // wrong num
            else if (!ticketSetup.getStatus().get(s)) {
                JOptionPane.showConfirmDialog(
                        getMainFrame(),
                        ErrorMsg.e021Msg + ": " + s.toString() + "\ndata size: " + ticketSetup.getTeamDataBySection(s).size()
                                + "\nnow: " + ticketSetup.getSetupDataModel().getValueBySection(s),
                        ErrorMsg.error021,
                        JOptionPane.DEFAULT_OPTION
                );
                return false;
            }
        }

        return true;
    }
}
