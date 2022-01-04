package UI;

import Model.ProgramFunctions;
import Setup.Setup;

import javax.swing.*;
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
    
    private ImageIcon legoSumo1kgIcon;
    private ImageIcon legoSumo3kgIcon;
    private ImageIcon lineFollowingEIcon;
    private ImageIcon lineFollowingJHIcon;
    private ImageIcon legoFolkraceEIcon;
    private ImageIcon legoFolkraceJHIcon;
    
    private BufferedImage legoSumo1kgImage;
    private BufferedImage legoSumo3kgImage;
    private BufferedImage lineFollowingEImage;
    private BufferedImage lineFollowingJHImage;
    private BufferedImage legoFolkraceEImage;
    private BufferedImage legoFolkraceJHImage;

    public MenuPanel(ProgramFunctions fun) {
        this.fun = fun;
        
        initPanel();
    }

    private void initPanel() {
    }

    public void addButtonActionListener(ActionListener listener) {

    }
}
