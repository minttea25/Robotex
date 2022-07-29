package Model;

import ConstantValues.Sections;


public class SetupDataModel {
    int LegoSumo1kg;
    int LegoSumo3kg;
    int LineFollowingE;
    int LineFollowingJH;
    int LegoFolkraceE;
    int LegoFolkraceJH;

    public int getValueBySection(Sections s) {
        switch (s) {
            case LegoSumo1kg : return this.LegoSumo1kg;
            case LegoSumo3kg : return this.LegoSumo3kg;
            case LineFollowingE : return this.LineFollowingE;
            case LineFollowingJH : return this.LineFollowingJH;
            case LegoFolkraceE : return this.LegoFolkraceE;
            case LegoFolkraceJH : return this.LegoFolkraceJH;
            default: return -1;
        }

        /*return switch (s) {
            case LegoSumo1kg -> this.LegoSumo1kg;
            case LegoSumo3kg -> this.LegoSumo3kg;
            case LineFollowingE -> this.LineFollowingE;
            case LineFollowingJH -> this.LineFollowingJH;
            case LegoFolkraceE -> this.LegoFolkraceE;
            case LegoFolkraceJH -> this.LegoFolkraceJH;
        };*/
    }

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
        String sb = "[ LegoSumo1kg: " +
                LegoSumo1kg +
                ", LegoSumo3kg: " +
                LegoSumo3kg +
                ", LineFollowingE: " +
                LineFollowingE +
                ", LineFollowingJH: " +
                LineFollowingJH +
                ", LegoFolkraceE: " +
                LegoFolkraceE +
                ", LegoFolkraceJH: " +
                LegoFolkraceJH +
                " ]";
        return sb;
    }
}
