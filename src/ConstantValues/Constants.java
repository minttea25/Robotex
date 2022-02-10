package ConstantValues;

import java.awt.*;
import java.nio.file.Paths;

public class Constants {
    // for file location
    public final static String FORMATION_SETUP_FILE_PATH = Paths.get("Setup", "SetupFormation.json").toString();
    public final static String TICKET_SETUP_FILE_PATH = Paths.get("Setup", "SetupTicket.json").toString();

    // for image location
    public final static String MAIN_BACKGROUND_PATH = Paths.get("Images",  "main", "mainframe.png").toString();
    public final static String FORMATION_BUTTON_RIGHT_PATH = Paths.get("Images", "main", "formation_button_right.png").toString();
    public final static String FORMATION_BUTTON_LEFT_PATH = Paths.get("Images", "main","formation_button_left.png").toString();
    public final static String TICKET_BUTTON_RIGHT_PATH = Paths.get("Images", "main","ticket_button_right.png").toString();
    public final static String TICKET_BUTTON_LEFT_PATH = Paths.get("Images", "main","ticket_button_left.png").toString();

    public final static String FORMATION_BACKGROUND_PATH = Paths.get("Images", "formation","formation_panel.png").toString();
    public final static String FORMATION_LEGO_SUMO_1KG_BTN_PATH = Paths.get("Images", "formation", "Formation_LegoSumo1kg_Btn.png").toString();
    public final static String FORMATION_LEGO_SUMO_3KG_BTN_PATH = Paths.get("Images", "formation", "Formation_LegoSumo3kg_Btn.png").toString();
    public final static String FORMATION_LINE_FOLLOWING_E_BTN_PATH = Paths.get("Images", "formation", "Formation_LineFollowingE_Btn.png").toString();
    public final static String FORMATION_LINE_FOLLOWING_JH_BTN_PATH = Paths.get("Images", "formation", "Formation_LineFollowingJH_Btn.png").toString();
    public final static String FORMATION_LEGO_FOLKRACE_E_BTN_PATH = Paths.get("Images", "formation", "Formation_LegoFolkraceE_Btn.png").toString();
    public final static String FORMATION_LEGO_FOLKRACE_JH_BTN_PATH = Paths.get("Images", "formation", "Formation_LegoFolkraceJH_Btn.png").toString();

    public final static String FORMATION_LEGO_SUMO_1KG_BG_PATH = Paths.get("Images", "formation", "Formation_LegoSumo1kg_Bg.png").toString();
    public final static String FORMATION_LEGO_SUMO_3KG_BG_PATH = Paths.get("Images", "formation", "Formation_LegoSumo3kg_Bg.png").toString();
    public final static String FORMATION_LINE_FOLLOWING_E_BG_PATH = Paths.get("Images", "formation", "Formation_LineFollowingE_Bg.png").toString();
    public final static String FORMATION_LINE_FOLLOWING_JH_BG_PATH = Paths.get("Images", "formation", "Formation_LineFollowingJH_Bg.png").toString();
    public final static String FORMATION_LEGO_FOLKRACE_E_BG_PATH = Paths.get("Images", "formation", "Formation_FolkraceE_Bg.png").toString();
    public final static String FORMATION_LEGO_FOLKRACE_JH_BG_PATH = Paths.get("Images", "formation", "Formation_FolkraceJH_Bg.png").toString();


    public final static String TICKET_BACKGROUND_PATH = Paths.get("Images", "ticket","ticket_panel.png").toString();
    public final static String TICKET_LEGO_SUMO_1KG_BTN_PATH = Paths.get("Images", "ticket", "Ticket_LegoSumo1kg_Btn.png").toString();
    public final static String TICKET_LEGO_SUMO_3KG_BTN_PATH = Paths.get("Images", "ticket", "Ticket_LegoSumo3kg_Btn.png").toString();
    public final static String TICKET_LINE_FOLLOWING_E_BTN_PATH = Paths.get("Images", "ticket", "Ticket_LineFollowingE_Btn.png").toString();
    public final static String TICKET_LINE_FOLLOWING_JH_BTN_PATH = Paths.get("Images", "ticket", "Ticket_LineFollowingJH_Btn.png").toString();
    public final static String TICKET_LEGO_FOLKRACE_E_BTN_PATH = Paths.get("Images", "ticket", "Ticket_LegoFolkraceE_Btn.png").toString();
    public final static String TICKET_LEGO_FOLKRACE_JH_BTN_PATH = Paths.get("Images", "ticket", "Ticket_LegoFolkraceJH_Btn.png").toString();

    public final static String TICKET_LEGO_SUMO_1KG_BG_PATH = Paths.get("Images", "ticket", "Ticket_LegoSumo1kg_Bg.png").toString();
    public final static String TICKET_LEGO_SUMO_3KG_BG_PATH = Paths.get("Images", "ticket", "Ticket_LegoSumo3kg_Bg.png").toString();
    public final static String TICKET_LINE_FOLLOWING_E_BG_PATH = Paths.get("Images", "ticket", "Ticket_LineFollowingE_Bg.png").toString();
    public final static String TICKET_LINE_FOLLOWING_JH_BG_PATH = Paths.get("Images", "ticket", "Ticket_LineFollowingJH_Bg.png").toString();
    public final static String TICKET_LEGO_FOLKRACE_E_BG_PATH = Paths.get("Images", "ticket", "Ticket_FolkraceE_Bg.png").toString();
    public final static String TICKET_LEGO_FOLKRACE_JH_BG_PATH = Paths.get("Images", "ticket", "Ticket_FolkraceJH_Bg.png").toString();


    public final static String HOME_RED_PATH = Paths.get("Images", "formation", "home_red.png").toString();
    public final static String HOME_WHITE_PATH = Paths.get("Images", "ticket", "home_white.png").toString();
    public final static String NEXT_BUTTON_RED_PATH = Paths.get("Images", "formation", "next_button_red.png").toString();
    public final static String NEXT_BUTTON_WHITE_PATH = Paths.get("Images", "ticket", "next_button_white.png").toString();

    public final static String VERTICAL_CONTOUR_PATH = Paths.get("Images", "formation", "verticalContour.png").toString();

    public final static String COUNT_DOWN_PATH = Paths.get("Images", "ticket", "count.gif").toString();

    // for writing excel file
    public final static String ENTRY = "Entry";
    public final static String EXCEL_SAVE_PATH_FORMATION = Paths.get("CreatedFiles", "Formation").toString();
    public final static String EXCEL_SAVE_PATH_TICKET = Paths.get("CreatedFiles", "Ticket").toString();
    public final static String EXCEL_SAVE_PATH = Paths.get("CreatedFiles").toString();

    public final static String TEAM_NUMBER = "TeamNumber";
    public final static String TEAM_NAME = "TeamName";
    public final static String BELONG = "Belong";
    public final static String COACH = "Coach";
    public final static String COACH_EMAIL = "Coach email";
    public final static String COACH_PHONE = "Coach phone";
    public final static String MEMBER1 = "Member1";
    public final static String MEMBER2 = "Member2";
    public final static String MEMBER3 = "Member3";
    public final static String MEMBER4 = "Member4";
    public final static String MEMBER5 = "Member5";

    // acceptable excel file extension
    public static final String EXCEL_EXTENSION_DESCRIPTION = "xlsx / xls";
    public static final String EXCEL_EXTENSION_XLSX = "xlsx";
    public static final String EXCEL_EXTENSION_XLS = "xls";

    // never edit this value
    public static final String SAVE_FILE_DATE_FORMAT = "HH-mm-ss";

    // theme color - rgb
    public static final Color THEME_COLOR = new Color(193, 29, 56);

    public static final Color TRANSPARENT = new Color(255, 255, 255, 0);

    public static final Color FORMATION_PANEL_BACK = new Color(240, 240, 240);

    public static final int COUNTDOWN_CLOSE_TIME = 5000;
}
