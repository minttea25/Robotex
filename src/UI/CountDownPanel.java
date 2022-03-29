package UI;

import ConstantValues.Constants;
import ConstantValues.GUIValue;
import Util.ImageLoader;
import Util.OptionPaneUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

public class CountDownPanel extends JPanel {
    TicketFrame parent;

    JLabel minLabel;
    JLabel backgroundLabel;

    BufferedImage backgroundImage;

    Set<String> loadFailSet = new HashSet<>();

    Timer timer;
    long startTime = -1;
    long duration = Constants.COUNTDOWN_CLOSE_TIME;

    SimpleDateFormat min = new SimpleDateFormat("s");

    CountDownPanel(TicketFrame frame){
        this.parent = frame;
        loadImages();
        init();

        OptionPaneUtil.showUnloadedImages(loadFailSet, this);

        this.setVisible(true);
    }

    private void loadImages() {
        backgroundImage = ImageLoader.loadImage(Constants.TICKET_COUNTDOWN_BG_PATH);
        if (backgroundImage == null) loadFailSet.add(Constants.TICKET_COUNTDOWN_BG_PATH);
    }

    private void init() {
        this.setLayout(null);

        String m;
        m = String.valueOf(duration/1000);

        minLabel = new JLabel(m);
        minLabel.setHorizontalAlignment(JLabel.CENTER);
        minLabel.setForeground(Color.white);
        minLabel.setFont(GUIValue.COUNT_FONT);

        setTimer();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!timer.isRunning()) {
                    startTime = -1;
                    timer.start();
                }
            }
        });

        add(minLabel);

        if (backgroundImage == null) {
            this.setBackground(Constants.THEME_COLOR);
            minLabel.setBounds(
                    760, 340,
                    GUIValue.COUNTDOWN_LABEL_WIDTH, GUIValue.COUNTDOWN_LABEL_HEIGHT
            );
        }
        else {
            minLabel.setBounds(
                    GUIValue.MAIN_WIDTH/2 - GUIValue.COUNTDOWN_LABEL_WIDTH/2,
                    GUIValue.MAIN_HEIGHT/2 - GUIValue.COUNTDOWN_LABEL_HEIGHT/2,
                    GUIValue.COUNTDOWN_LABEL_WIDTH, GUIValue.COUNTDOWN_LABEL_HEIGHT
            );

            backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
            add(backgroundLabel);
            backgroundLabel.setBounds(
                    0, 0,
                    backgroundImage.getWidth(), backgroundImage.getHeight()
            );
        }
    }

    private void setTimer() {
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (startTime < 0) {
                    startTime = System.currentTimeMillis();
                }
                long now = System.currentTimeMillis();
                long clockTime = now - startTime;

                if (clockTime >= duration) {
                    clockTime = duration;
                    timer.stop();
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    parent.showContentPanel();
                }

                Long tt = duration - clockTime;
                minLabel.setText(min.format(tt));

            }
        });

        timer.setInitialDelay(0);
    }
}
