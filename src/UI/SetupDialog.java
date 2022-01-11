package UI;

import ConstantValues.*;
import Model.ProgramFunctions;
import Model.SetupDataModel;
import Setup.Setup;
import Util.ExcelFileChooser;
import Util.GUIUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Objects;

public class SetupDialog extends JDialog{
    ProgramFunctions fun;
    Setup setup;
    Component parent;

    JPanel filePanel;
    JLabel labelFile;
    JButton buttonFile;
    JTextField textFieldFile;

    JPanel valuePanel;
    JLabel labelNumberOfEntries;
    JLabel labelLegoSumo1kg;
    JLabel labelLegoSumo3kg;
    JLabel labelLineFollowingE;
    JLabel labelLineFollowingJH;
    JLabel labelFolkraceE;
    JLabel labelFolkraceJH;
    JTextField tfLegoSumo1kg;
    JTextField tfLegoSumo3kg;
    JTextField tfLineFollowingE;
    JTextField tfLineFollowingJH;
    JTextField tfFolkraceE;
    JTextField tfFolkraceJH;

    JPanel buttonPanel;
    JButton btnExcelLoad;
    JButton btnOK;

    public SetupDialog(ProgramFunctions fun, Component parent, Setup setup) {
        this.fun = fun;
        this.parent = parent;
        this.setup = setup;

        this.setup.loadSetupFile();

        if (!(new File(this.setup.getSetupFilePath()).exists())) {
            int ok = JOptionPane.showConfirmDialog(
                    getSetupDialog(),
                    ErrorMsg.e004Msg + this.setup.getSetupFilePath(),
                    ErrorMsg.error004,
                    JOptionPane.DEFAULT_OPTION
            );
            if (ok == JOptionPane.OK_OPTION) {
                getSetupDialog().dispose();
            }
            return;
        }

        if (!this.setup.isSetupFileLoaded()) {
            //System.out.println("The setup file is not loaded successfully");
            //System.out.println("Check: " + this.setup.getSetupFilePath());
            int ok = JOptionPane.showConfirmDialog(
                    getSetupDialog(),
                    ErrorMsg.e005Msg + this.setup.getSetupFilePath(),
                    ErrorMsg.error005,
                    JOptionPane.DEFAULT_OPTION
            );
            if (ok == JOptionPane.OK_OPTION) {
                getSetupDialog().dispose();
            }
            return;

        }

        initDialog();
        initComponents();

        attachComponents();
        setValuesOnTextField();
    }

    private void setValuesOnTextField() {
        SetupDataModel t = setup.getSetupDataModel();

        tfLegoSumo1kg.setText(String.valueOf(t.getLegoSumo1kg()));
        tfLegoSumo3kg.setText(String.valueOf(t.getLegoSumo3kg()));
        tfLineFollowingE.setText(String.valueOf(t.getLineFollowingE()));
        tfLineFollowingJH.setText(String.valueOf(t.getLineFollowingJH()));
        tfFolkraceE.setText(String.valueOf(t.getLegoFolkraceE()));
        tfFolkraceJH.setText(String.valueOf(t.getLegoFolkraceJH()));
    }

    private void attachComponents() {
        add(filePanel, BorderLayout.NORTH);
        add(valuePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
    }

    public void showDialog() {
        setLocationRelativeTo(parent);
        setVisible(this.setup.isSetupFileLoaded());
    }

    private void initComponents() {
        filePanel = new JPanel();
        labelFile = new JLabel(GUIString.FILE_LABEL);
        textFieldFile = new JTextField(15);
        buttonFile = new JButton(GUIString.CHOOSE_FILE);

        valuePanel = new JPanel();
        labelNumberOfEntries = new JLabel(
                fun == ProgramFunctions.Formation ? GUIString.ENTRY_NUMBER : GUIString.TICKETS_NUMBER
        );
        labelLegoSumo1kg = new JLabel(Sections.LegoSumo1kg.toString());
        labelLegoSumo3kg = new JLabel(Sections.LegoSumo3kg.toString());
        labelLineFollowingE = new JLabel(Sections.LineFollowingE.toString());
        labelLineFollowingJH = new JLabel(Sections.LineFollowingJH.toString());
        labelFolkraceE = new JLabel(Sections.LegoFolkraceE.toString());
        labelFolkraceJH = new JLabel(Sections.LegoFolkraceJH.toString());
        tfLegoSumo1kg = new JTextField();
        tfLegoSumo3kg = new JTextField();
        tfLineFollowingE = new JTextField();
        tfLineFollowingJH = new JTextField();
        tfFolkraceE = new JTextField();
        tfFolkraceJH = new JTextField();

        buttonPanel = new JPanel();
        btnExcelLoad = new JButton(GUIString.LOAD);
        btnOK = new JButton(GUIString.OK);

        btnExcelLoad.setToolTipText(GUIString.SELECT_FILE_FIRST_TOOLTIP);
        btnOK.setToolTipText(GUIString.LOAD_FIRST_TOOLTIP);

        initFilePanel();
        initValuePanel();
        initButtonPanel();
    }

    private void initValuePanel() {
        valuePanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets.top = GUIValue.SETUP_GRID_PADDING;
        gbc.insets.bottom = GUIValue.SETUP_GRID_PADDING;
        gbc.insets.left = GUIValue.SETUP_GRID_PADDING;
        gbc.insets.right = GUIValue.SETUP_GRID_PADDING;

        GUIUtil.setGridBagConstraintsWeight(gbc, 1.0, 1.0);

        int[] t_width = {
                GUIValue.SETUP_GRID_X_0, GUIValue.SETUP_GRID_X_1
        };

        makeRowGridConstraints(gbc,
                0,
                valuePanel, t_width,
                new JPanel(), labelNumberOfEntries);

        makeRowGridConstraints(gbc,
                1,
                valuePanel, t_width,
                labelLegoSumo1kg, tfLegoSumo1kg);

        makeRowGridConstraints(gbc,
                2,
                valuePanel, t_width,
                labelLegoSumo3kg, tfLegoSumo3kg);

        makeRowGridConstraints(gbc,
                3,
                valuePanel, t_width,
                labelLineFollowingE, tfLineFollowingE);

        makeRowGridConstraints(gbc,
                4,
                valuePanel, t_width,
                labelLineFollowingJH, tfLineFollowingJH);

        makeRowGridConstraints(gbc,
                5,
                valuePanel,t_width,
                labelFolkraceE, tfFolkraceE);

        makeRowGridConstraints(gbc,
                6,
                valuePanel, t_width,
                labelFolkraceJH, tfFolkraceJH);
    }

    private void initButtonPanel() {
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        buttonPanel.add(btnExcelLoad);

        btnOK.setEnabled(false);
        buttonPanel.add(btnOK);

        btnExcelLoad.addActionListener(new ButtonEventListener());
        btnOK.addActionListener(new ButtonEventListener());
    }

    private void makeRowGridConstraints(GridBagConstraints gbc, int gridy, JPanel panel, int[] width, Component... components) {
        if (width.length != components.length) {
            return;
        }

        int gridx = 0;
        for (int i=0; i<components.length; i++) {
            GUIUtil.setGridBagConstraints(gbc,
                    gridx , gridy,
                    width[i], GUIValue.SETUP_GRID_Y);
            panel.add(components[i], gbc);

            gridx += width[i];
        }

    }

    private void initFilePanel() {
        filePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        GUIUtil.setPanelMargin(filePanel, GUIValue.SETUP_MARGIN);

        labelFile.setHorizontalAlignment(JLabel.CENTER);
        filePanel.add(labelFile);

        textFieldFile.setEditable(false);
        filePanel.add(textFieldFile);

        buttonFile.addActionListener(new ButtonEventListener());
        filePanel.add(buttonFile);
    }

    private void initDialog() {
        if (fun == ProgramFunctions.Formation)
            setTitle(GUIString.FORMATION_SETUP_TITLE);
        else
            setTitle(GUIString.TICKET_SETUP_TITLE);

        setAlwaysOnTop(true);
        setLayout(new BorderLayout(GUIValue.SETUP_MARGIN, GUIValue.SETUP_MARGIN));
        setResizable(GUIValue.WINDOW_RESIZABLE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private SetupDialog getSetupDialog() {
        return this;
    }

    class ButtonEventListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object obj = e.getSource();

            if (obj == buttonFile) {
                selectFile();
            }
            else if (obj == btnExcelLoad) {
                if (Objects.equals(setup.getExcelFilePath(), "") || setup.getExcelFilePath() == null) {
                    //System.out.println("There is no selected file");
                    JOptionPane.showConfirmDialog(
                            getSetupDialog(),
                            ErrorMsg.e003Msg,
                            ErrorMsg.error003,
                            JOptionPane.DEFAULT_OPTION
                    );
                    return;
                }

                setup.loadExcelFile();

                if (setup.isExcelFileLoaded()) {
                    StringBuilder msg = new StringBuilder(GUIString.EXCEL_LOAD_MSG + setup.getExcelFilePath());
                    for (Sections s : setup.getExcelReadManager().getDataLoaded().keySet()) {
                        if (setup.getExcelReadManager().getDataLoaded(s)) {
                            msg.append("\n").append(s.toString());
                        }
                    }

                    //System.out.println("Load Success!");
                    JOptionPane.showMessageDialog(
                            getSetupDialog(),
                            msg.toString(),
                            GUIString.NOTCIE,
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    btnOK.setEnabled(true);
                    btnOK.setToolTipText(null);
                    btnExcelLoad.setText(GUIString.LOADED);
                    btnExcelLoad.setEnabled(false);
                }
            }
            else if (obj == btnOK) {
                if (saveValues())
                    dispose();
            }
        }
    }

    private void selectFile() {
        ExcelFileChooser chooser = new ExcelFileChooser(getSetupDialog());
        chooser.openDialog();
        String path = chooser.getFilePath();
        if (path != null) {
            setup.setExcelFilePath(path);
            textFieldFile.setText(path);
            btnExcelLoad.setToolTipText(null);
        }
    }

    private boolean saveValues() {

        try {
            int[] arr = new int[6];
            arr[0]= Integer.parseInt(tfLegoSumo1kg.getText());
            arr[1] = Integer.parseInt(tfLegoSumo3kg.getText());
            arr[2] = Integer.parseInt(tfLineFollowingE.getText());
            arr[3] = Integer.parseInt(tfLineFollowingJH.getText());
            arr[4] = Integer.parseInt(tfFolkraceE.getText());
            arr[5] = Integer.parseInt(tfFolkraceJH.getText());

            for (int v : arr) {
                if (v <= 0) {
                    //System.out.println(" v <= 0 ");
                    JOptionPane.showConfirmDialog(
                            getSetupDialog(),
                            ErrorMsg.e002Msg,
                            ErrorMsg.error002,
                            JOptionPane.DEFAULT_OPTION
                    );
                    return false;
                }
            }

            setup.getSetupDataModel().setLegoSumo1kg(arr[0]);
            setup.getSetupDataModel().setLegoSumo3kg(arr[1]);
            setup.getSetupDataModel().setLineFollowingE(arr[2]);
            setup.getSetupDataModel().setLineFollowingJH(arr[3]);
            setup.getSetupDataModel().setLegoFolkraceE(arr[4]);
            setup.getSetupDataModel().setLegoFolkraceJH(arr[5]);

        } catch (NumberFormatException e) {
            // e.printStackTrace();
            JOptionPane.showConfirmDialog(
                    getSetupDialog(),
                    ErrorMsg.e001Msg,
                    ErrorMsg.error001,
                    JOptionPane.DEFAULT_OPTION);
            return false;
        }

        setup.writeSetupFile();
        return true;
    }
}
