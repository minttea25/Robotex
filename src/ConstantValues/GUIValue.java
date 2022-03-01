package ConstantValues;

import java.awt.*;

public class GUIValue {
    // common
    public final static boolean WINDOW_RESIZABLE = false;

    // main frame
    public final static int MAIN_WIDTH = 1920;
    public final static int MAIN_HEIGHT = 1080;

    // main panel
    public final static int MAIN_FORMATION_BUTTON_LEFT_X = 220;
    public final static int MAIN_TICKET_BUTTON_LEFT_X =  MAIN_WIDTH/2 + MAIN_FORMATION_BUTTON_LEFT_X;
    public final static int MAIN_BUTTON_Y = 820;

    // menu panel
    public final static int MENU_BUTTON_INTERVAL = 100;
    public final static int MENU_BUTTON_Y = 520;

    // for Card Layout Name - DO NOT MODIFY THESE VALUES
    public static final String MAIN_CARD_NAME = "main";
    public static final String FORMATION_CARD_NAME = "formation";
    public static final String TICKET_CARD_NAME = "ticket";

    // for ticket card layout name - DO NOT MODIFY THESE VALUES
    public static final String TICKET_1ST_CARD_NAME = "1st";
    public static final String TICKET_2nd_CARD_NAME = "2nd";
    public static final String TICKET_3rd_CARD_NAME = "3rd";
    public static final String FORMATION_BASE_CARD_NAME = "formation";

    // setup
    public final static int SETUP_MARGIN = 5;
    public final static int SETUP_GRID_PADDING = 3;
    public final static int SETUP_GRID_PADDING_LEFT = 10;
    public final static int SETUP_GRID_X_0 = 1;
    public final static int SETUP_GRID_X_1 = 2;
    public final static int SETUP_GRID_Y = 1;
    public final static int SETUP_FILE_TEXT_FIELD_COLUMNS = 20;

    // formation / ticket frame
    public final static int RESULT_BOX_X = 690;
    public final static int RESULT_BOX_Y = 250;
    public final static int RESULT_BOX_WIDTH = 1090;
    public final static int RESULT_BOX_HEIGHT = 690;

    public final static int RESULT_PANEL_WIDTH = 420; //320
    public final static int RESULT_PANEL_HEIGHT = 670;
    public final static int RESULT_PANEL_TITLE_WIDTH = RESULT_PANEL_WIDTH;
    public final static int RESULT_PANEL_TITLE_HEIGHT = 50;
    public final static int RESULT_PANEL_BODY_WIDTH = RESULT_PANEL_WIDTH;
    public final static int RESULT_PANEL_BODY_HEIGHT = RESULT_PANEL_HEIGHT - RESULT_PANEL_TITLE_HEIGHT;
    public final static int RESULT_PANEL_INTERVAL = 20;

    public final static int NEXT_BUTTON_WIDTH = 60;
    public final static int NEXT_BUTTON_HEIGHT = 35;

    public final static int TICKET_PRELIMINARY_LAST_SHOWING_EACH_TEAMS = 10;
    public final static int TICKET_SHOWING_NUMBERS_OF_TEAMS_EACH_PANEL = 2;
    public final static int FORMATION_SHOWING_NUMBERS_OF_TEAMS_EACH_PANEL = 2;

    public final static int RESULT_PANEL_WIDTH_WQ = 700;
    public final static int RESULT_PANEL_HEIGHT_WQ = 670;

    // back btn
    public static final int HOME_BTN_X = 50;
    public static final int HOME_BTN_Y = 60;
    public static final int HOME_BTN_WIDTH = 42;
    public static final int HOME_BTN_HEIGHT = 50;

    // image default value
    public static final int SECTION_UP_BTN_WIDTH = 525;
    public static final int SECTION_UP_BTN_HEIGHT = 254;
    public static final int SECTION_DOWN_BTN_WIDTH = 525;
    public static final int SECTION_DOWN_BTN_HEIGHT = 255;
    public static final int BUTTON_LEFT_WIDTH = 351;
    public static final int BUTTON_LEFT_HEIGHT = 155;
    public static final int BUTTON_RIGHT_WIDTH = 174;
    public static final int BUTTON_RIGHT_HEIGHT = 155;

    // font
    private static final int TITLE_FONT_SIZE = 35;
    private static final int TEXT_FONT_SIZE = 30; //23

    private static final int TEXT_FONT_SIZE_WQ = 35;

    private static final String ONE_STORE_MOBILE_POP = "ONE 모바일POP OTF";
    private static final String CAFE_24_DANGDANG = "카페24 당당해";
    private static final String GYEONGGI_CENTURY = "경기천년바탕 Bold";
    private static final String GYEONGGI_CENTURY_TITLE = "경기천년제목 Bold";
    private static final String NEXON_MAPLESTORY = "메이플스토리";

    public static final Font TITLE_FONT = new Font(NEXON_MAPLESTORY, Font.BOLD, TITLE_FONT_SIZE);
    public static final Font TEXT_FONT = new Font(NEXON_MAPLESTORY, Font.BOLD, TEXT_FONT_SIZE);

    public static final Font TEXT_FONT_WQ = new Font(NEXON_MAPLESTORY, Font.BOLD, TEXT_FONT_SIZE_WQ);

}
