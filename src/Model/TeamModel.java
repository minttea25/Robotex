package Model;

import java.util.ArrayList;
import java.util.Collections;
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
        this.members.addAll(members);
    }

    public void addMember(String member) {
        if (this.members.size() >= MAX_MEMBERS) {
            System.out.println("number of max members is " + MAX_MEMBERS + " - now you try add one more.");
            return;
        }
        this.members.add(member);
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
        if (this.members.size() > MAX_MEMBERS) {
            System.out.println("number of max members is " + MAX_MEMBERS + " - now you try to set: " + members.size());
            return;
        }

        this.members.clear();

        this.members.addAll(members);
    }

    public void setMembers(String... members) {
        if (this.members.size() > MAX_MEMBERS) {
            System.out.println("number of max members is " + MAX_MEMBERS + " - now you try to set: " + members.length);
            return;
        }

        this.members.clear();

        Collections.addAll(this.members, members);
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
        t.append("teamNumber: ").append(teamNumber)
                .append(", teamName: ").append(teamName)
                .append(", coach: ").append(coach)
                .append(", coachPhone: ").append(coachPhone)
                .append(", coachEmail: ").append(coachEmail)
                .append(", belong: ").append(belong);

        if (members.size() == 0) {
            return t.toString();
        }

        t.append(", members: [");

        for (String m : members) {
            t.append(m).append(", ");
        }
        t.append("]");
        return t.toString();
    }


}
