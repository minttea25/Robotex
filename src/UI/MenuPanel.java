package UI;

import ConstantValues.Constants;
import ConstantValues.GUIString;
import ConstantValues.GUIValue;
import ConstantValues.Sections;
import Model.ProgramFunctions;
import Util.ImageLoader;
import Util.OptionPaneUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

public class MenuPanel extends JPanel {
    ProgramFunctions fun;
    
    private JLabel backImageLabel;
    protected JButton legoSumo1kgBtn;
    protected JButton legoSumo3kgBtn;
    protected JButton lineFollowingEBtn;
    protected JButton lineFollowingJHBtn;
    protected JButton legoFolkraceEBtn;
    protected JButton legoFolkraceJHBtn;
    protected JButton backBtn;

    private ImageIcon legoSumo1kgIcon;
    private ImageIcon legoSumo3kgIcon;
    private ImageIcon lineFollowingEIcon;
    private ImageIcon lineFollowingJHIcon;
    private ImageIcon legoFolkraceEIcon;
    private ImageIcon legoFolkraceJHIcon;
    private ImageIcon homeBtnIcon;

    private BufferedImage backgroundImage;
    private BufferedImage legoSumo1kgImage;
    private BufferedImage legoSumo3kgImage;
    private BufferedImage lineFollowingEImage;
    private BufferedImage lineFollowingJHImage;
    private BufferedImage legoFolkraceEImage;
    private BufferedImage legoFolkraceJHImage;
    private BufferedImage homeBtnImage;

    // for only ticket
    protected JButton roboLeagueBtn1;
    protected JButton roboLeagueBtn2;
    private ImageIcon roboLeagueIcon1;
    private ImageIcon roboLeagueIcon2;
    private BufferedImage roboLeagueImage1;
    private BufferedImage roboLeagueImage2;

    Set<String> loadFailSet = new HashSet<>();

    public MenuPanel(ProgramFunctions fun) {
        this.fun = fun;

        initPanel();
        initComponents();
        if (fun == ProgramFunctions.Formation) {
            attachComponents();
        }
        else {
            attachComponentsForTicket();
        }


        OptionPaneUtil.showUnloadedImages(loadFailSet, null);
    }

    private void attachComponents() {
        add(legoSumo1kgBtn);
        if (legoSumo1kgImage != null) {
            legoSumo1kgBtn.setBounds(
                    GUIValue.MAIN_WIDTH / 2 - GUIValue.SECTION_UP_BTN_WIDTH / 2 - GUIValue.MENU_BUTTON_INTERVAL - GUIValue.SECTION_UP_BTN_WIDTH,
                    GUIValue.MENU_BUTTON_Y,
                    legoSumo1kgIcon.getIconWidth(), legoSumo1kgIcon.getIconHeight()
            );
        }
        else {
            legoSumo1kgBtn.setBounds(
                    GUIValue.MAIN_WIDTH / 2 - GUIValue.SECTION_UP_BTN_WIDTH / 2 - GUIValue.MENU_BUTTON_INTERVAL - GUIValue.SECTION_UP_BTN_WIDTH,
                    GUIValue.MENU_BUTTON_Y,
                    GUIValue.SECTION_UP_BTN_WIDTH, GUIValue.SECTION_UP_BTN_HEIGHT
            );
            legoSumo1kgBtn.setText(Sections.LegoSumo1kg.toString());
        }

        add(legoSumo3kgBtn);
        if (legoSumo3kgImage != null) {
            legoSumo3kgBtn.setBounds(
                    GUIValue.MAIN_WIDTH / 2 - GUIValue.SECTION_UP_BTN_WIDTH / 2 - GUIValue.MENU_BUTTON_INTERVAL - GUIValue.SECTION_UP_BTN_WIDTH,
                    GUIValue.MENU_BUTTON_Y + GUIValue.SECTION_UP_BTN_HEIGHT - 3,
                    legoSumo3kgIcon.getIconWidth(), legoSumo3kgIcon.getIconHeight()
            );
        }
        else {
            legoSumo3kgBtn.setBounds(
                    GUIValue.MAIN_WIDTH / 2 - GUIValue.SECTION_UP_BTN_WIDTH / 2 - GUIValue.MENU_BUTTON_INTERVAL - GUIValue.SECTION_UP_BTN_WIDTH,
                    GUIValue.MENU_BUTTON_Y + GUIValue.SECTION_UP_BTN_HEIGHT,
                    GUIValue.SECTION_DOWN_BTN_WIDTH, GUIValue.SECTION_DOWN_BTN_HEIGHT
            );
            legoSumo3kgBtn.setText(Sections.LegoSumo3kg.toString());
        }

        add(lineFollowingEBtn);
        if (lineFollowingEImage != null) {
            lineFollowingEBtn.setBounds(
                    GUIValue.MAIN_WIDTH / 2 - GUIValue.SECTION_UP_BTN_WIDTH / 2,
                    GUIValue.MENU_BUTTON_Y,
                    lineFollowingEIcon.getIconWidth(), lineFollowingEIcon.getIconHeight()
            );
        }
        else {
            lineFollowingEBtn.setBounds(
                    GUIValue.MAIN_WIDTH / 2 - GUIValue.SECTION_UP_BTN_WIDTH / 2,
                    GUIValue.MENU_BUTTON_Y,
                    GUIValue.SECTION_UP_BTN_WIDTH, GUIValue.SECTION_UP_BTN_HEIGHT
            );
            lineFollowingEBtn.setText(Sections.LineFollowingE.toString());
        }

        add(lineFollowingJHBtn);
        if (lineFollowingJHImage != null) {
            lineFollowingJHBtn.setBounds(
                    GUIValue.MAIN_WIDTH / 2 - GUIValue.SECTION_UP_BTN_WIDTH / 2,
                    GUIValue.MENU_BUTTON_Y + GUIValue.SECTION_UP_BTN_HEIGHT - 3,
                    lineFollowingJHIcon.getIconWidth(), lineFollowingJHIcon.getIconHeight()
            );
        }
        else {
            lineFollowingJHBtn.setBounds(
                    GUIValue.MAIN_WIDTH / 2 - GUIValue.SECTION_UP_BTN_WIDTH / 2,
                    GUIValue.MENU_BUTTON_Y + GUIValue.SECTION_UP_BTN_HEIGHT,
                    GUIValue.SECTION_DOWN_BTN_WIDTH, GUIValue.SECTION_DOWN_BTN_HEIGHT
            );
            lineFollowingJHBtn.setText(Sections.LineFollowingJH.toString());
        }

        add(legoFolkraceEBtn);
        if (legoFolkraceEImage != null) {
            legoFolkraceEBtn.setBounds(
                    GUIValue.MAIN_WIDTH / 2 + GUIValue.SECTION_UP_BTN_WIDTH / 2 + GUIValue.MENU_BUTTON_INTERVAL,
                    GUIValue.MENU_BUTTON_Y,
                    legoFolkraceEIcon.getIconWidth(), legoFolkraceJHIcon.getIconHeight()
            );
        }
        else {
            legoFolkraceEBtn.setBounds(
                    GUIValue.MAIN_WIDTH / 2 + GUIValue.SECTION_UP_BTN_WIDTH / 2 + GUIValue.MENU_BUTTON_INTERVAL,
                    GUIValue.MENU_BUTTON_Y,
                    GUIValue.SECTION_UP_BTN_WIDTH, GUIValue.SECTION_UP_BTN_HEIGHT
            );
            legoFolkraceEBtn.setText(Sections.LegoFolkraceE.toString());
        }

        add(legoFolkraceJHBtn);
        if (legoFolkraceJHImage != null) {
            legoFolkraceJHBtn.setBounds(
                    GUIValue.MAIN_WIDTH / 2 + GUIValue.SECTION_UP_BTN_WIDTH / 2 + GUIValue.MENU_BUTTON_INTERVAL,
                    GUIValue.MENU_BUTTON_Y + GUIValue.SECTION_UP_BTN_HEIGHT - 2,
                    legoFolkraceJHIcon.getIconWidth(), legoFolkraceJHIcon.getIconHeight()
            );
        }
        else {
            legoFolkraceJHBtn.setBounds(
                    GUIValue.MAIN_WIDTH / 2 + GUIValue.SECTION_UP_BTN_WIDTH / 2 + GUIValue.MENU_BUTTON_INTERVAL,
                    GUIValue.MENU_BUTTON_Y + GUIValue.SECTION_UP_BTN_HEIGHT,
                    GUIValue.SECTION_DOWN_BTN_WIDTH, GUIValue.SECTION_DOWN_BTN_HEIGHT
            );
            legoFolkraceJHBtn.setText(Sections.LegoFolkraceJH.toString());
        }

        add(backBtn);
        if (homeBtnImage != null) {
            backBtn.setBounds(
                    GUIValue.HOME_BTN_X, GUIValue.HOME_BTN_Y,
                    homeBtnIcon.getIconWidth(), homeBtnIcon.getIconHeight()
            );
        }
        else {
            backBtn.setBounds(
                    GUIValue.HOME_BTN_X, GUIValue.HOME_BTN_Y,
                    GUIValue.HOME_BTN_WIDTH, GUIValue.HOME_BTN_HEIGHT
            );
            backBtn.setText(GUIString.BACK);
        }


        // it should be attached last.
        add(backImageLabel);
        if (backgroundImage != null) {
            backImageLabel.setBounds(
                    0, 0,
                    backgroundImage.getWidth(), backgroundImage.getHeight()
            );
        }
    }

    private void attachComponentsForTicket() {
        add(legoSumo1kgBtn);
        if (legoSumo1kgImage != null) {
            legoSumo1kgBtn.setBounds(
                    GUIValue.TICKET_BTN_1_X, GUIValue.TICKET_BTN_Y,
                    legoSumo1kgIcon.getIconWidth(), legoSumo1kgIcon.getIconHeight()
            );
        }
        else {
            legoSumo1kgBtn.setBounds(
                    GUIValue.TICKET_BTN_1_X, GUIValue.TICKET_BTN_Y,
                    GUIValue.TICKET_SECTION_BTN_WIDTH, GUIValue.TICKET_SECTION_BTN_HEIGHT
            );
            legoSumo1kgBtn.setText(Sections.LegoSumo1kg.toString());
        }

        add(legoSumo3kgBtn);
        if (legoSumo3kgImage != null) {
            legoSumo3kgBtn.setBounds(
                    GUIValue.TICKET_BTN_1_X, GUIValue.TICKET_BTN_Y + GUIValue.TICKET_SECTION_BTN_HEIGHT + GUIValue.TICKET_BTN_INTERVAL,
                    legoSumo3kgIcon.getIconWidth(), legoSumo3kgIcon.getIconHeight()
            );
        }
        else {
            legoSumo3kgBtn.setBounds(
                    GUIValue.TICKET_BTN_1_X, GUIValue.TICKET_BTN_Y + GUIValue.TICKET_SECTION_BTN_HEIGHT + GUIValue.TICKET_BTN_INTERVAL,
                    GUIValue.TICKET_SECTION_BTN_WIDTH, GUIValue.TICKET_SECTION_BTN_HEIGHT
            );
            legoSumo3kgBtn.setText(Sections.LegoSumo3kg.toString());
        }

        add(lineFollowingEBtn);
        if (lineFollowingEImage != null) {
            lineFollowingEBtn.setBounds(
                    GUIValue.TICKET_BTN_2_X, GUIValue.TICKET_BTN_Y,
                    lineFollowingEIcon.getIconWidth(), lineFollowingEIcon.getIconHeight()
            );
        }
        else {
            lineFollowingEBtn.setBounds(
                    GUIValue.TICKET_BTN_2_X, GUIValue.TICKET_BTN_Y,
                    GUIValue.TICKET_SECTION_BTN_WIDTH, GUIValue.TICKET_SECTION_BTN_HEIGHT
            );
            lineFollowingEBtn.setText(Sections.LineFollowingE.toString());
        }

        add(lineFollowingJHBtn);
        if (lineFollowingJHImage != null) {
            lineFollowingJHBtn.setBounds(
                    GUIValue.TICKET_BTN_2_X, GUIValue.TICKET_BTN_Y + GUIValue.TICKET_SECTION_BTN_HEIGHT + GUIValue.TICKET_BTN_INTERVAL,
                    lineFollowingJHIcon.getIconWidth(), lineFollowingJHIcon.getIconHeight()
            );
        }
        else {
            lineFollowingJHBtn.setBounds(
                    GUIValue.TICKET_BTN_2_X, GUIValue.TICKET_BTN_Y + GUIValue.TICKET_SECTION_BTN_HEIGHT + GUIValue.TICKET_BTN_INTERVAL,
                    GUIValue.TICKET_SECTION_BTN_WIDTH, GUIValue.TICKET_SECTION_BTN_HEIGHT
            );
            lineFollowingJHBtn.setText(Sections.LineFollowingJH.toString());
        }

        add(legoFolkraceEBtn);
        if (legoFolkraceEImage != null) {
            legoFolkraceEBtn.setBounds(
                    GUIValue.TICKET_BTN_3_X, GUIValue.TICKET_BTN_Y,
                    legoFolkraceEIcon.getIconWidth(), legoFolkraceJHIcon.getIconHeight()
            );
        }
        else {
            legoFolkraceEBtn.setBounds(
                    GUIValue.TICKET_BTN_3_X, GUIValue.TICKET_BTN_Y,
                    GUIValue.TICKET_SECTION_BTN_WIDTH, GUIValue.TICKET_SECTION_BTN_HEIGHT
            );
            legoFolkraceEBtn.setText(Sections.LegoFolkraceE.toString());
        }

        add(legoFolkraceJHBtn);
        if (legoFolkraceJHImage != null) {
            legoFolkraceJHBtn.setBounds(
                    GUIValue.TICKET_BTN_3_X, GUIValue.TICKET_BTN_Y + GUIValue.TICKET_SECTION_BTN_HEIGHT + GUIValue.TICKET_BTN_INTERVAL,
                    legoFolkraceJHIcon.getIconWidth(), legoFolkraceJHIcon.getIconHeight()
            );
        }
        else {
            legoFolkraceJHBtn.setBounds(
                    GUIValue.TICKET_BTN_3_X, GUIValue.TICKET_BTN_Y + GUIValue.TICKET_SECTION_BTN_HEIGHT + GUIValue.TICKET_BTN_INTERVAL,
                    GUIValue.TICKET_SECTION_BTN_WIDTH, GUIValue.TICKET_SECTION_BTN_HEIGHT
            );
            legoFolkraceJHBtn.setText(Sections.LegoFolkraceJH.toString());
        }

        add(roboLeagueBtn1);
        if (roboLeagueImage1 != null) {
            roboLeagueBtn1.setBounds(
                    GUIValue.TICKET_BTN_4_X, GUIValue.TICKET_BTN_Y,
                    roboLeagueIcon1.getIconWidth(), roboLeagueIcon1.getIconHeight()
            );
        }
        else {
            roboLeagueBtn1.setBounds(
                    GUIValue.TICKET_BTN_4_X, GUIValue.TICKET_BTN_Y,
                    GUIValue.TICKET_SECTION_BTN_WIDTH, GUIValue.TICKET_SECTION_BTN_HEIGHT
            );
            roboLeagueBtn1.setText(Sections.RoboLeague.toString());
        }

        add(roboLeagueBtn2);
        if (roboLeagueImage2 != null) {
            roboLeagueBtn2.setBounds(
                    GUIValue.TICKET_BTN_4_X, GUIValue.TICKET_BTN_Y + GUIValue.TICKET_SECTION_BTN_HEIGHT + GUIValue.TICKET_BTN_INTERVAL,
                    roboLeagueIcon2.getIconWidth(), roboLeagueIcon2.getIconHeight()
            );
        }
        else {
            roboLeagueBtn2.setBounds(
                    GUIValue.TICKET_BTN_4_X, GUIValue.TICKET_BTN_Y + GUIValue.TICKET_SECTION_BTN_HEIGHT + GUIValue.TICKET_BTN_INTERVAL,
                    GUIValue.TICKET_SECTION_BTN_WIDTH, GUIValue.TICKET_SECTION_BTN_HEIGHT
            );
            roboLeagueBtn2.setText(Sections.RoboLeague.toString());
        }

        add(backBtn);
        if (homeBtnImage != null) {
            backBtn.setBounds(
                    GUIValue.HOME_BTN_X, GUIValue.HOME_BTN_Y,
                    homeBtnIcon.getIconWidth(), homeBtnIcon.getIconHeight()
            );
        }
        else {
            backBtn.setBounds(
                    GUIValue.HOME_BTN_X, GUIValue.HOME_BTN_Y,
                    GUIValue.HOME_BTN_WIDTH, GUIValue.HOME_BTN_HEIGHT
            );
            backBtn.setText(GUIString.BACK);
        }


        // it should be attached last.
        add(backImageLabel);
        if (backgroundImage != null) {
            backImageLabel.setBounds(
                    0, 0,
                    backgroundImage.getWidth(), backgroundImage.getHeight()
            );
        }
    }

    private void initComponents() {
        backImageLabel = new JLabel();
        legoSumo1kgBtn = new JButton();
        legoSumo3kgBtn = new JButton();
        lineFollowingEBtn = new JButton();
        lineFollowingJHBtn = new JButton();
        legoFolkraceEBtn = new JButton();
        legoFolkraceJHBtn = new JButton();
        backBtn = new JButton();

        if (backgroundImage != null) {
            backImageLabel.setIcon(new ImageIcon(backgroundImage));
        }

        if (legoSumo1kgImage != null) {
            legoSumo1kgBtn.setIcon(legoSumo1kgIcon = new ImageIcon(legoSumo1kgImage));
        }

        if (legoSumo3kgImage != null) {
            legoSumo3kgBtn.setIcon(legoSumo3kgIcon = new ImageIcon(legoSumo3kgImage));
        }

        if (lineFollowingEImage != null) {
            lineFollowingEBtn.setIcon(lineFollowingEIcon = new ImageIcon(lineFollowingEImage));
        }

        if (lineFollowingJHImage != null) {
            lineFollowingJHBtn.setIcon(lineFollowingJHIcon = new ImageIcon(lineFollowingJHImage));
        }

        if (legoFolkraceEImage != null) {
            legoFolkraceEBtn.setIcon(legoFolkraceEIcon = new ImageIcon(legoFolkraceEImage));
        }

        if (legoFolkraceJHImage != null) {
            legoFolkraceJHBtn.setIcon(legoFolkraceJHIcon = new ImageIcon(legoFolkraceJHImage));
        }

        legoSumo1kgBtn.setBorderPainted(false);
        legoSumo3kgBtn.setBorderPainted(false);
        lineFollowingEBtn.setBorderPainted(false);
        lineFollowingJHBtn.setBorderPainted(false);
        legoFolkraceEBtn.setBorderPainted(false);
        legoFolkraceJHBtn.setBorderPainted(false);

        legoSumo1kgBtn.setBackground(new Color(255, 255, 255, 0));
        legoSumo3kgBtn.setBackground(new Color(255, 255, 255, 0));
        lineFollowingEBtn.setBackground(new Color(255, 255, 255, 0));
        lineFollowingJHBtn.setBackground(new Color(255, 255, 255, 0));
        legoFolkraceEBtn.setBackground(new Color(255, 255, 255, 0));
        legoFolkraceJHBtn.setBackground(new Color(255, 255, 255, 0));

        legoSumo1kgBtn.setBorder(null);
        legoSumo3kgBtn.setBorder(null);
        lineFollowingEBtn.setBorder(null);
        lineFollowingJHBtn.setBorder(null);
        legoFolkraceEBtn.setBorder(null);
        legoFolkraceJHBtn.setBorder(null);

        legoSumo1kgBtn.setOpaque(false);
        legoSumo3kgBtn.setOpaque(false);
        lineFollowingEBtn.setOpaque(false);
        lineFollowingJHBtn.setOpaque(false);
        legoFolkraceEBtn.setOpaque(false);
        legoFolkraceJHBtn.setOpaque(false);

        //backBtn.setText(GUIString.BACK);
        if (homeBtnImage != null) {
            backBtn.setIcon(homeBtnIcon = new ImageIcon(homeBtnImage));
        }
        backBtn.setBorderPainted(false);
        backBtn.setBackground(new Color(255, 255, 255, 0));
        backBtn.setBorder(null);
        backBtn.setOpaque(false);

        if (fun == ProgramFunctions.Ticket) {
            roboLeagueBtn1 = new JButton();
            roboLeagueBtn2 = new JButton();

            if (roboLeagueImage1 != null) {
                roboLeagueBtn1.setIcon(roboLeagueIcon1 = new ImageIcon(roboLeagueImage1));
            }
            if (roboLeagueImage2 != null) {
                roboLeagueBtn2.setIcon(roboLeagueIcon2 = new ImageIcon(roboLeagueImage2));
            }

            roboLeagueBtn1.setBorderPainted(false);
            roboLeagueBtn2.setBorderPainted(false);
            roboLeagueBtn1.setBorder(null);
            roboLeagueBtn2.setBorder(null);
            roboLeagueBtn1.setBackground(new Color(255, 255, 255, 0));
            roboLeagueBtn2.setBackground(new Color(255, 255, 255, 0));
            roboLeagueBtn1.setOpaque(false);
            roboLeagueBtn2.setOpaque(false);
        }
    }

    private void initPanel() {
        setLayout(null);
        loadImages();
    }

    private void loadImages() {
        if (fun == ProgramFunctions.Formation) {
            backgroundImage = ImageLoader.loadImage(Constants.FORMATION_BACKGROUND_PATH);
            legoSumo1kgImage = ImageLoader.loadImage(Constants.FORMATION_LEGO_SUMO_1KG_BTN_PATH);
            legoSumo3kgImage = ImageLoader.loadImage(Constants.FORMATION_LEGO_SUMO_3KG_BTN_PATH);
            lineFollowingEImage = ImageLoader.loadImage(Constants.FORMATION_LINE_FOLLOWING_E_BTN_PATH);
            lineFollowingJHImage = ImageLoader.loadImage(Constants.FORMATION_LINE_FOLLOWING_JH_BTN_PATH);
            legoFolkraceEImage = ImageLoader.loadImage(Constants.FORMATION_LEGO_FOLKRACE_E_BTN_PATH);
            legoFolkraceJHImage = ImageLoader.loadImage(Constants.FORMATION_LEGO_FOLKRACE_JH_BTN_PATH);

            homeBtnImage = ImageLoader.loadImage(Constants.HOME_RED_PATH);

            if (backgroundImage == null) loadFailSet.add(Constants.FORMATION_BACKGROUND_PATH);
            if(legoSumo1kgImage == null) loadFailSet.add(Constants.FORMATION_LEGO_SUMO_1KG_BTN_PATH);
            if(legoSumo3kgImage == null) loadFailSet.add(Constants.FORMATION_LEGO_SUMO_3KG_BTN_PATH);
            if(lineFollowingEImage == null) loadFailSet.add(Constants.FORMATION_LINE_FOLLOWING_E_BTN_PATH);
            if(lineFollowingJHImage == null) loadFailSet.add(Constants.FORMATION_LINE_FOLLOWING_JH_BTN_PATH);
            if(legoFolkraceEImage == null) loadFailSet.add(Constants.FORMATION_LEGO_FOLKRACE_E_BTN_PATH);
            if(legoFolkraceJHImage == null) loadFailSet.add(Constants.FORMATION_LEGO_FOLKRACE_JH_BTN_PATH);
            if (homeBtnImage == null) loadFailSet.add(Constants.HOME_RED_PATH);
        }
        else if (fun == ProgramFunctions.Ticket) {
            backgroundImage = ImageLoader.loadImage(Constants.TICKET_BACKGROUND_PATH);
            legoSumo1kgImage = ImageLoader.loadImage(Constants.TICKET_LEGO_SUMO_1KG_BTN_PATH);
            legoSumo3kgImage = ImageLoader.loadImage(Constants.TICKET_LEGO_SUMO_3KG_BTN_PATH);
            lineFollowingEImage = ImageLoader.loadImage(Constants.TICKET_LINE_FOLLOWING_E_BTN_PATH);
            lineFollowingJHImage = ImageLoader.loadImage(Constants.TICKET_LINE_FOLLOWING_JH_BTN_PATH);
            legoFolkraceEImage = ImageLoader.loadImage(Constants.TICKET_LEGO_FOLKRACE_E_BTN_PATH);
            legoFolkraceJHImage = ImageLoader.loadImage(Constants.TICKET_LEGO_FOLKRACE_JH_BTN_PATH);
            roboLeagueImage1 = ImageLoader.loadImage(Constants.TICKET_ROBO_LEAGUE_BTN_1_PATH);
            roboLeagueImage2 = ImageLoader.loadImage(Constants.TICKET_ROBO_LEAGUE_BTN_2_PATH);

            homeBtnImage = ImageLoader.loadImage(Constants.HOME_WHITE_PATH);

            if (backgroundImage == null) loadFailSet.add(Constants.TICKET_BACKGROUND_PATH);
            if(legoSumo1kgImage == null) loadFailSet.add(Constants.TICKET_LEGO_SUMO_1KG_BTN_PATH);
            if(legoSumo3kgImage == null) loadFailSet.add(Constants.TICKET_LEGO_SUMO_3KG_BTN_PATH);
            if(lineFollowingEImage == null) loadFailSet.add(Constants.TICKET_LINE_FOLLOWING_E_BTN_PATH);
            if(lineFollowingJHImage == null) loadFailSet.add(Constants.TICKET_LINE_FOLLOWING_JH_BTN_PATH);
            if(legoFolkraceEImage == null) loadFailSet.add(Constants.TICKET_LEGO_FOLKRACE_E_BTN_PATH);
            if(legoFolkraceJHImage == null) loadFailSet.add(Constants.TICKET_LEGO_FOLKRACE_JH_BTN_PATH);
            if(roboLeagueImage1 == null) loadFailSet.add(Constants.TICKET_ROBO_LEAGUE_BTN_1_PATH);
            if(roboLeagueImage2 == null) loadFailSet.add(Constants.TICKET_ROBO_LEAGUE_BTN_2_PATH);
            if (homeBtnImage == null) loadFailSet.add(Constants.HOME_WHITE_PATH);
        }
        else {
            //System.out.println("ERROR");
        }


    }

    public void addButtonActionListener(ActionListener listener) {
        legoSumo1kgBtn.addActionListener(listener);
        legoSumo3kgBtn.addActionListener(listener);
        lineFollowingEBtn.addActionListener(listener);
        lineFollowingJHBtn.addActionListener(listener);
        legoFolkraceEBtn.addActionListener(listener);
        legoFolkraceJHBtn.addActionListener(listener);
        backBtn.addActionListener(listener);

        if (fun == ProgramFunctions.Ticket) {
            roboLeagueBtn1.addActionListener(listener);
            roboLeagueBtn2.addActionListener(listener);
        }
    }
}
