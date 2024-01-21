package footballTeamGenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Map<String, Team> teamsByName = new HashMap<>();

        String command = scan.nextLine();
        while (!command.equals("END")) {
            String [] tokens = command.split(";");

            switch (tokens[0]) {
                case "Team":
                    handleAddTeam(tokens[1], teamsByName);
                    break;
                case "Add":
                    handleAddPlayer(tokens, teamsByName);
                    break;
                case "Remove":
                    handleRemovePlayer(tokens[1], tokens[2], teamsByName);
                    break;
                case "Rating":
                    handleRating(tokens[1], teamsByName);
                    break;
                default:
                    throw new IllegalStateException("Unknown command: " + tokens[0]);
            }


            command = scan.nextLine();
        }
    }

    private static void handleRating(String teamName, Map<String, Team> teamsByName) {
        Team team = getTeamOrPrintNotFound(teamName, teamsByName);
        if (team == null) return;

        System.out.println(team.getName() + " - " + (int)team.getRating());
    }

    private static void handleRemovePlayer(String teamName, String playerName, Map<String, Team> teamsByName) {
        Team team = getTeamOrPrintNotFound(teamName, teamsByName);
        if (team == null) return;

        try {
            team.removePlayer(playerName);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }
    }

    private static void handleAddPlayer(String[] tokens, Map<String, Team> teamsByName) {
        Team team = getTeamOrPrintNotFound(tokens[1], teamsByName);
        if (team == null) return;

        String name = tokens[2];
        int endurance = Integer.parseInt(tokens[3]);
        int sprint = Integer.parseInt(tokens[4]);
        int dribble = Integer.parseInt(tokens[5]);
        int passing = Integer.parseInt(tokens[6]);
        int shooting = Integer.parseInt(tokens[7]);

        try {
            team.addPlayer(new Player(name, endurance, sprint, dribble, passing, shooting));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }
    }

    private static void handleAddTeam(String nameTeam, Map<String, Team> teamsByName) {
        try {
            Team team = new Team(nameTeam);
            teamsByName.put(team.getName(), team);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static Team getTeamOrPrintNotFound(String teamName, Map<String, Team> teamsByName) {
        Team team = teamsByName.get(teamName);

        if (team == null) {
            System.out.println("Team" + teamName + "does not exist.");
            return null;
        }

        return team;
    }
}
