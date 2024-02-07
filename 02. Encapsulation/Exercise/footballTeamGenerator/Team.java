package footballTeamGenerator;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private List<Player> players;

    public Team(String name) {
        setName(name);
        this.players = new ArrayList<>();
    }
    public String getName() {
        return name;
    }

    private void setName(String name) {
        Validators.validateName(name);
        this.name = name;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void removePlayer(String name) {
        int toRemoveIndex = -1;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getName().equals(name)) {
                toRemoveIndex = i;
                break;
            }
        }

        if (toRemoveIndex == -1) {
            throw new IllegalArgumentException("Player " + name + " is not in " + this.name + " team.");
        }

        this.players.remove(toRemoveIndex);
    }

    public double getRating() {
        if (this.players == null) {
            return 0;
        }
        return Math.round(this.players.stream().mapToDouble(Player::overallSkillLevel).average().orElse(0));
    }
}
