package UI;

import ConstantValues.Constants;
import ConstantValues.GUIString;
import ConstantValues.GUIValue;
import Util.ImageLoader;
import Util.OptionPaneUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

public class MainPanel extends JPanel{
    private JLabel backImageLabel;
    protected JButton formationBtn;
    protected JButton formationSetupBtn;
    protected JButton ticketBtn;
    protected JButton ticketSetupBtn;

    private ImageIcon formationIcon;
    private ImageIcon formationSetupIcon;
    private ImageIcon ticketIcon;
    private ImageIcon ticketSetupIcon;

    private BufferedImage backgroundImage;
    private BufferedImage formationBtnImage;
    private BufferedImage formationSetupBtnImage;
    private BufferedImage ticketBtnImage;
    private BufferedImage ticketSetupBtnImage;

    private JLabel devLabel;
    private JLabel verLabel;

    Set<String> loadFailSet = new HashSet<>();

    public MainPanel() {
        initPanel();
        initComponents();
        attachComponents();

        OptionPaneUtil.showUnloadedImages(loadFailSet, null);
    }

    private void initPanel() {
        setLayout(null);
        loadImages();
    }

    private void attachComponents() {
        add(formationBtn);
        if (formationBtnImage != null) {
            formationBtn.setBounds(
                    GUIValue.MAIN_FORMATION_BUTTON_LEFT_X, GUIValue.MAIN_BUTTON_Y,
                    formationIcon.getIconWidth(), formationIcon.getIconHeight()
            );
        }
        else {
            formationBtn.setBounds(
                    GUIValue.MAIN_FORMATION_BUTTON_LEFT_X, GUIValue.MAIN_BUTTON_Y,
                    GUIValue.BUTTON_LEFT_WIDTH, GUIValue.BUTTON_LEFT_HEIGHT
            );
            formationBtn.setText("formation");
        }

        add(formationSetupBtn);
        if (formationSetupBtnImage != null && formationBtnImage != null) {
            formationSetupBtn.setBounds(
                    GUIValue.MAIN_FORMATION_BUTTON_LEFT_X + formationIcon.getIconWidth() - 2,
                    GUIValue.MAIN_BUTTON_Y,
                    formationSetupIcon.getIconWidth(), formationSetupIcon.getIconHeight()
            );
        }
        else {
            formationSetupBtn.setBounds(
                    GUIValue.MAIN_FORMATION_BUTTON_LEFT_X + GUIValue.BUTTON_LEFT_WIDTH,
                    GUIValue.MAIN_BUTTON_Y,
                    GUIValue.BUTTON_RIGHT_WIDTH, GUIValue.BUTTON_RIGHT_HEIGHT
            );
            formationSetupBtn.setText("formation setup");
        }

        add(ticketBtn);
        if (ticketBtnImage != null) {
            ticketBtn.setBounds(
                    GUIValue.MAIN_TICKET_BUTTON_LEFT_X, GUIValue.MAIN_BUTTON_Y,
                    ticketIcon.getIconWidth(), ticketIcon.getIconHeight()
            );
        }
        else {
            ticketBtn.setBounds(
                    GUIValue.MAIN_TICKET_BUTTON_LEFT_X, GUIValue.MAIN_BUTTON_Y,
                    GUIValue.BUTTON_LEFT_WIDTH, GUIValue.BUTTON_LEFT_HEIGHT
            );
            ticketBtn.setText("world ticket");
        }

        add(ticketSetupBtn);
        if (ticketSetupBtnImage != null && ticketBtnImage != null) {
            ticketSetupBtn.setBounds(
                    GUIValue.MAIN_TICKET_BUTTON_LEFT_X + ticketIcon.getIconWidth() - 2,
                    GUIValue.MAIN_BUTTON_Y,
                    ticketSetupIcon.getIconWidth(), ticketSetupIcon.getIconHeight()
            );
        }
        else {
            ticketSetupBtn.setBounds(
                    GUIValue.MAIN_TICKET_BUTTON_LEFT_X + GUIValue.BUTTON_LEFT_WIDTH,
                    GUIValue.MAIN_BUTTON_Y,
                    GUIValue.BUTTON_RIGHT_WIDTH, GUIValue.BUTTON_RIGHT_HEIGHT
            );
            ticketSetupBtn.setText("world ticket setup");
        }

        initAdditionalLabel();


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
        formationBtn = new JButton();
        formationSetupBtn = new JButton();
        ticketBtn = new JButton();
        ticketSetupBtn = new JButton();

        if (backgroundImage != null) {
            backImageLabel.setIcon(new ImageIcon(backgroundImage));
        }
        if (formationBtnImage != null) {
            formationBtn.setIcon(formationIcon = new ImageIcon(formationBtnImage));
        }
        if (formationSetupBtnImage != null) {
            formationSetupBtn.setIcon(formationSetupIcon = new ImageIcon(formationSetupBtnImage));
        }
        if (ticketBtnImage != null) {
            ticketBtn.setIcon(ticketIcon = new ImageIcon(ticketBtnImage));
        }
        if (ticketSetupBtnImage != null) {
            ticketSetupBtn.setIcon(ticketSetupIcon = new ImageIcon(ticketSetupBtnImage));
        }

        formationBtn.setBorderPainted(false);
        formationSetupBtn.setBorderPainted(false);
        ticketBtn.setBorderPainted(false);
        ticketSetupBtn.setBorderPainted(false);

        formationBtn.setBackground(new Color(255, 255, 255, 0));
        formationSetupBtn.setBackground(new Color(255, 255, 255, 0));
        ticketBtn.setBackground(new Color(255, 255, 255, 0));
        ticketSetupBtn.setBackground(new Color(255, 255, 255, 0));

        formationBtn.setBorder(null);
        formationSetupBtn.setBorder(null);
        ticketBtn.setBorder(null);
        ticketSetupBtn.setBorder(null);

        formationBtn.setOpaque(false);
        formationSetupBtn.setOpaque(false);
        ticketBtn.setOpaque(false);
        ticketSetupBtn.setOpaque(false);

        formationBtn.setToolTipText(GUIString.FORMATION_TOOLTIP);
        formationSetupBtn.setToolTipText(GUIString.FORMATION_SETUP_TOOLTIP);
        ticketBtn.setToolTipText(GUIString.TICKET_TOOLTIP);
        ticketSetupBtn.setToolTipText(GUIString.TICKET_SETUP_TOOLTIP);
    }

    private void loadImages() {
        backgroundImage = ImageLoader.loadImage(Constants.MAIN_BACKGROUND_PATH);
        formationBtnImage = ImageLoader.loadImage(Constants.FORMATION_BUTTON_LEFT_PATH);
        formationSetupBtnImage = ImageLoader.loadImage(Constants.FORMATION_BUTTON_RIGHT_PATH);
        ticketBtnImage = ImageLoader.loadImage(Constants.TICKET_BUTTON_LEFT_PATH);
        ticketSetupBtnImage = ImageLoader.loadImage(Constants.TICKET_BUTTON_RIGHT_PATH);

        if (backgroundImage == null) loadFailSet.add(Constants.MAIN_BACKGROUND_PATH);
        if (formationBtnImage == null) loadFailSet.add(Constants.FORMATION_BUTTON_LEFT_PATH);
        if (formationSetupBtnImage == null) loadFailSet.add(Constants.FORMATION_BUTTON_RIGHT_PATH);
        if (ticketBtnImage == null) loadFailSet.add(Constants.TICKET_BUTTON_LEFT_PATH);
        if (ticketSetupBtnImage == null) loadFailSet.add(Constants.TICKET_BUTTON_RIGHT_PATH);
    }

    private void initAdditionalLabel() {
        devLabel = new JLabel(GUIString.DEV_);
        verLabel = new JLabel(GUIString.VERSION);

        devLabel.setFont(new Font("메이플스토리", Font.PLAIN, 12));
        verLabel.setFont(new Font("메이플스토리", Font.PLAIN, 12));

        add(devLabel);
        devLabel.setBounds(1550, 20, 500, 50);

        add(verLabel);
        verLabel.setBounds(30, 20, 300, 50);
    }


    public void addButtonActionListener(ActionListener listener) {
        formationBtn.addActionListener(listener);
        formationSetupBtn.addActionListener(listener);
        ticketBtn.addActionListener(listener);
        ticketSetupBtn.addActionListener(listener);
    }
}
