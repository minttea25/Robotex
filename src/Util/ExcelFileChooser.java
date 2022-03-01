package Util;

import ConstantValues.Constants;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;

public class ExcelFileChooser extends JFileChooser {
    Component parent;
    String filePath;

    public ExcelFileChooser(Component parent) {
        this.parent = parent;
        this.setFileFilter(
                new FileNameExtensionFilter(
                        Constants.EXCEL_EXTENSION_DESCRIPTION,
                        Constants.EXCEL_EXTENSION_XLSX, Constants.EXCEL_EXTENSION_XLS
                )
        );
        this.setMultiSelectionEnabled(false);
    }

    public void openDialog() {
        if (showOpenDialog(parent) != JFileChooser.APPROVE_OPTION) {
            //System.out.println("file not selected");
            return;
        }

        if (checkChosenFile(getSelectedFile().getPath())) {
            filePath = getSelectedFile().getPath();
        }
    }

    public String getFilePath() {
        if (filePath == null) {
            //System.out.println("File is not chosen");
            return null;
        }
        return filePath;
    }

    private boolean checkChosenFile(String path) {
        String extension = "";
        int i = path.lastIndexOf('.');
        if (i > 0) {
            extension = path.substring(i+1);
        }

        if (extension.equals(Constants.EXCEL_EXTENSION_XLSX)
                || extension.equals(Constants.EXCEL_EXTENSION_XLS)
                || extension.equals(Constants.EXCEL_EXTENSION_XLS.toUpperCase())
                || extension.equals(Constants.EXCEL_EXTENSION_XLSX.toUpperCase())) {
            return true;
        }
        else {
            System.out.println("Extension error");
            return false;
        }

    }
}
