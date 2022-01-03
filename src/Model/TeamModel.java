package Model;

import java.util.ArrayList;
import java.util.List;

public class TeamModel {
    static final int MAX_MEMBERS = 5;
    static final int NUMBERS_OF_ATTRIBUTES = 7;

    String teamNumber;
    String teamName;
    String coach;
    String coachPhone;
    String coachEmail;
    String belong;
    List<String> members = new ArrayList<>();

    public TeamModel() {

    }

    public TeamModel(String teamNumber, String teamName, String coach,
                     String coachPhone, String coachEmail, String belong,
                     List<String> members) {
        this.teamName = teamName;
        this.teamNumber = teamNumber;
        this.coach = coach;
        this.coachPhone = coachPhone;
        this.coachEmail = coachEmail;
        this.belong = belong;
        for (var member : members) {
            this.members.add(member);
        }
    }

    public void setTeamNumber(String teamNumber) {
        this.teamNumber = teamNumber;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public void setCoachPhone(String coachPhone) {
        this.coachPhone = coachPhone;
    }

    public void setCoachEmail(String coachEmail) {
        this.coachEmail = coachEmail;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }

    public void setMembers(List<String> members) {
        for (String m : members) {
            this.members.add(m);
        }
    }

    public void setMembers(String... members) {
        for (String m : members) {
            this.members.add(m);
        }
    }

    public String getTeamNumber() {
        return teamNumber;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getCoach() {
        return coach;
    }

    public String getCoachPhone() {
        return coachPhone;
    }

    public String getCoachEmail() {
        return coachEmail;
    }

    public String getBelong() {
        return belong;
    }

    public List<String> getMembers() {
        return members;
    }

    @Override
    public TeamModel clone() throws CloneNotSupportedException {
        return (TeamModel) super.clone();
    }

    @Override
    public String toString() {
        StringBuilder t = new StringBuilder();
        t.append("teamNumber: " + teamNumber + ", teamName: " + teamName
                + ", coach: " + coach + ", coachPhone: " + coachPhone
                + ", coachEmail: " + coachEmail + ", belong: " + belong);

        if (members.size() == 0) {
            return t.toString();
        }

        t.append(", members: [");

        for (String m : members) {
            t.append(m + ", ");
        }
        t.append("]");
        return t.toString();
    }


}
