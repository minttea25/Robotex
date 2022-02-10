package UI;

import ConstantValues.GUIValue;
import Model.ProgramFunctions;
import Model.TeamModel;
import Util.GUIUtil;

import java.util.List;

public class ResultPanelWorldQualification extends ResultPanel{

    public ResultPanelWorldQualification(String title, List<TeamModel> data, ProgramFunctions pf) {
        super(title, data, pf);
    }

    @Override
    protected void initFont() {
        titleFont = GUIValue.TITLE_FONT;
        font = GUIValue.TEXT_FONT_WQ;
    }

    @Override
    protected void initPanel() {
        setLayout(null);
        GUIUtil.setSize(this,
                GUIValue.RESULT_PANEL_WIDTH_WQ, GUIValue.RESULT_PANEL_HEIGHT_WQ);
    }

    @Override
    protected void attachComponents() {
        add(upPanel);
        upPanel.setBounds(
                0, 0,
                GUIValue.RESULT_PANEL_WIDTH_WQ, GUIValue.RESULT_PANEL_TITLE_HEIGHT
        );

        add(downPanel);
        downPanel.setBounds(
                0, GUIValue.RESULT_PANEL_TITLE_HEIGHT,
                GUIValue.RESULT_PANEL_WIDTH_WQ, GUIValue.RESULT_PANEL_BODY_HEIGHT
        );
    }
}
