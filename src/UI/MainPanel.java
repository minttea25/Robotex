package UI;

import ConstantValues.Constants;
import ConstantValues.GUIString;
import ConstantValues.GUIValue;
import Util.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

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


    public MainPanel() {
        initPanel();
        initComponents();
        attachComponents();
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

        add(formationSetupBtn);
        if (formationSetupBtnImage != null) {
            formationSetupBtn.setBounds(
                    GUIValue.MAIN_FORMATION_BUTTON_LEFT_X + formationIcon.getIconWidth(),
                    GUIValue.MAIN_BUTTON_Y,
                    formationSetupIcon.getIconWidth(), formationSetupIcon.getIconHeight()
            );
        }

        add(ticketBtn);
        if (ticketBtnImage != null) {
            ticketBtn.setBounds(
                    GUIValue.MAIN_TICKET_BUTTON_LEFT_X, GUIValue.MAIN_BUTTON_Y,
                    ticketIcon.getIconWidth(), ticketIcon.getIconHeight()
            );
        }

        add(ticketSetupBtn);
        if (ticketBtnImage != null) {
            ticketSetupBtn.setBounds(
                    GUIValue.MAIN_TICKET_BUTTON_LEFT_X + ticketIcon.getIconWidth(),
                    GUIValue.MAIN_BUTTON_Y,
                    ticketSetupIcon.getIconWidth(), ticketSetupIcon.getIconHeight()
            );
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
        if (ticketBtnImage != null) {
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
        formationBtnImage = ImageLoader.loadImage(Constants.MAIN_BUTTON_LEFT_PATH);
        formationSetupBtnImage = ImageLoader.loadImage(Constants.MAIN_BUTTON_RIGHT_PATH);
        ticketBtnImage = ImageLoader.loadImage(Constants.MAIN_BUTTON_LEFT_PATH);
        ticketSetupBtnImage = ImageLoader.loadImage(Constants.MAIN_BUTTON_RIGHT_PATH);
    }


    public void addButtonActionListener(ActionListener listener) {
        formationBtn.addActionListener(listener);
        formationSetupBtn.addActionListener(listener);
        ticketBtn.addActionListener(listener);
        ticketSetupBtn.addActionListener(listener);
    }
}
