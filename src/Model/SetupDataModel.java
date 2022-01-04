package Model;

import java.util.HashMap;
import java.util.Map;

public class SetupDataModel {
    int LegoSumo1kg;
    int LegoSumo3kg;
    int LineFollowingE;
    int LineFollowingJH;
    int LegoFolkraceE;
    int LegoFolkraceJH;

    public void setLegoSumo1kg(int legoSumo1kg) {
        LegoSumo1kg = legoSumo1kg;
    }

    public void setLegoSumo3kg(int legoSumo3kg) {
        LegoSumo3kg = legoSumo3kg;
    }

    public void setLineFollowingE(int lineFollowingE) {
        LineFollowingE = lineFollowingE;
    }

    public void setLineFollowingJH(int lineFollowingJH) {
        LineFollowingJH = lineFollowingJH;
    }

    public void setLegoFolkraceE(int legoFolkraceE) {
        LegoFolkraceE = legoFolkraceE;
    }

    public void setLegoFolkraceJH(int legoFolkraceJH) {
        LegoFolkraceJH = legoFolkraceJH;
    }

    public int getLegoSumo1kg() {
        return LegoSumo1kg;
    }

    public int getLegoSumo3kg() {
        return LegoSumo3kg;
    }

    public int getLineFollowingE() {
        return LineFollowingE;
    }

    public int getLegoFolkraceJH() {
        return LegoFolkraceJH;
    }

    public int getLegoFolkraceE() {
        return LegoFolkraceE;
    }

    public int getLineFollowingJH() {
        return LineFollowingJH;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ LegoSumo1kg: ");
        sb.append(LegoSumo1kg);
        sb.append(", LegoSumo3kg: ");
        sb.append(LegoSumo3kg);
        sb.append(", LineFollowingE: ");
        sb.append(LineFollowingE);
        sb.append(", LineFollowingJH: ");
        sb.append(LineFollowingJH);
        sb.append(", LegoFolkraceE: ");
        sb.append(LegoFolkraceE);
        sb.append(", LegoFolkraceJH: ");
        sb.append(LegoFolkraceJH);
        sb.append(" ]");
        return sb.toString();
    }
}
