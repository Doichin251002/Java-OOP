package footballTeamGenerator;

public class Player {
    private String name;
    private int endurance;
    private int sprint;
    private int dribble;
    private int passing;
    private int shooting;

    public Player(String name, int endurance, int sprint, int dribble, int passing, int shooting) {
        setName(name);
        setEndurance(endurance);
        setSprint(sprint);
        setDribble(dribble);
        setPassing(passing);
        setShooting(shooting);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        Validators.validateName(name);
        this.name = name;
    }

    private void setEndurance(int endurance) {
        Validators.validateStat("Endurance", endurance);
        this.endurance = endurance;
    }

    private void setSprint(int sprint) {
        Validators.validateStat("Sprint", sprint);
        this.sprint = sprint;
    }

    private void setDribble(int dribble) {
        Validators.validateStat("Dribble", dribble);
        this.dribble = dribble;
    }

    private void setPassing(int passing) {
        Validators.validateStat("Passing", passing);
        this.passing = passing;
    }

    private void setShooting(int shooting) {
        Validators.validateStat("Shooting", shooting);
        this.shooting = shooting;
    }

    public double overallSkillLevel() {
        return (this.endurance + this.sprint + this.dribble + this.passing + this.shooting) / 5.0;
    }
}
