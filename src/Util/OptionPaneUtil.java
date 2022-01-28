package Util;

import ConstantValues.ErrorMsg;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class OptionPaneUtil {
    public static void showUnloadedImages(
            Set<String> set,
            Component component) {
        if (set.size() != 0) {
            StringBuilder errMsg = new StringBuilder(ErrorMsg.e041Msg);
            for (String s : set) {
                errMsg.append(s).append("\n");
            }

            JOptionPane.showConfirmDialog(
                    component,
                    errMsg,
                    ErrorMsg.error041,
                    JOptionPane.DEFAULT_OPTION
            );
        }
    }
}
