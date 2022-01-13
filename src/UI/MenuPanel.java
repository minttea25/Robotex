package UI;

import ConstantValues.Constants;
import ConstantValues.GUIString;
import ConstantValues.GUIValue;
import ConstantValues.Sections;
import Model.ProgramFunctions;
import Util.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

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

    private BufferedImage backgroundImage;
    private BufferedImage legoSumo1kgImage;
    private BufferedImage legoSumo3kgImage;
    private BufferedImage lineFollowingEImage;
    private BufferedImage lineFollowingJHImage;
    private BufferedImage legoFolkraceEImage;
    private BufferedImage legoFolkraceJHImage;

    public MenuPanel(ProgramFunctions fun) {
        this.fun = fun;
        
        initPanel();
        initComponents();
        attachComponents();
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
                    GUIValue.MENU_BUTTON_Y + GUIValue.SECTION_UP_BTN_HEIGHT,
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
                    GUIValue.MENU_BUTTON_Y + GUIValue.SECTION_UP_BTN_HEIGHT,
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
                    GUIValue.MENU_BUTTON_Y + GUIValue.SECTION_UP_BTN_HEIGHT,
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
        backBtn.setBounds(
                GUIValue.BACK_BTN_X, GUIValue.BACK_BTN_Y,
                GUIValue.BACK_BTN_WIDTH, GUIValue.BACK_BTN_HEIGHT
        );

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

        backBtn.setText(GUIString.BACK);
    }

    private void initPanel() {
        setLayout(null);
        loadImages();
    }

    private void loadImages() {
        if (fun == ProgramFunctions.Formation) {
            backgroundImage = ImageLoader.loadImage(Constants.FORMATION_BACKGROUND_PATH);
            legoSumo1kgImage = ImageLoader.loadImage(Constants.FORMATION_LEGO_SUMO_1KG_PATH);
            legoSumo3kgImage = ImageLoader.loadImage(Constants.FORMATION_LEGO_SUMO_3KG_PATH);
            lineFollowingEImage = ImageLoader.loadImage(Constants.FORMATION_LINE_FOLLOWING_E_PATH);
            lineFollowingJHImage = ImageLoader.loadImage(Constants.FORMATION_LINE_FOLLOWING_JH_PATH);
            legoFolkraceEImage = ImageLoader.loadImage(Constants.FORMATION_LEGO_FOLKRACE_E_PATH);
            legoFolkraceJHImage = ImageLoader.loadImage(Constants.FORMATION_LEGO_FOLKRACE_JH_PATH);
        }
        else if (fun == ProgramFunctions.Ticket) {
            backgroundImage = ImageLoader.loadImage(Constants.TICKET_BACKGROUND_PATH);
            legoSumo1kgImage = ImageLoader.loadImage(Constants.TICKET_LEGO_SUMO_1KG_PATH);
            legoSumo3kgImage = ImageLoader.loadImage(Constants.TICKET_LEGO_SUMO_3KG_PATH);
            lineFollowingEImage = ImageLoader.loadImage(Constants.TICKET_LINE_FOLLOWING_E_PATH);
            lineFollowingJHImage = ImageLoader.loadImage(Constants.TICKET_LINE_FOLLOWING_JH_PATH);
            legoFolkraceEImage = ImageLoader.loadImage(Constants.TICKET_LEGO_FOLKRACE_E_PATH);
            legoFolkraceJHImage = ImageLoader.loadImage(Constants.TICKET_LEGO_FOLKRACE_JH_PATH);
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
    }
}
