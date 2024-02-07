package jediGalaxy;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int[][] galaxy = createGalaxyMatrix(scan);
        fillGalaxyMatrix(galaxy);

        String command = scan.nextLine();
        long sum = 0;
        while (!command.equals("Let the Force be with you")) {
            int[] jediPosition = Arrays.stream(command.split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] evilPosition = Arrays.stream(scan.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            sum += calculateSum(galaxy, new Coords(jediPosition[0], jediPosition[1]), new Coords(evilPosition[0], evilPosition[1]));

            command = scan.nextLine();
        }

        System.out.println(sum);
    }

    private static long calculateSum(int[][] galaxy, Coords jediPosition, Coords evilPosition) {
        destroysStars(galaxy, evilPosition);

        return collectStars(galaxy, jediPosition);
    }

    private static long collectStars(int[][] galaxy, Coords jediPosition) {
        long sum = 0;
        while (canJediMove(galaxy, jediPosition)) {
            if (isInGalaxy(galaxy, jediPosition)) {
                sum += galaxy[jediPosition.row][jediPosition.col];
            }

            jediPosition.col++;
            jediPosition.row--;
        }
        return sum;
    }
    private static void destroysStars(int[][] galaxy, Coords evilPosition) {
        while (canEvilMove(evilPosition)) {
            if (isInGalaxy(galaxy, evilPosition)) {
                galaxy[evilPosition.row][evilPosition.col] = 0;
            }
            evilPosition.row--;
            evilPosition.col--;
        }
    }
    private static boolean canJediMove(int[][] galaxy, Coords coords) {
        return coords.row >= 0 && coords.col < galaxy[0].length;
    }
    private static boolean canEvilMove(Coords coords) {
        return coords.row >= 0 && coords.col >= 0;
    }
    private static boolean isInGalaxy(int[][] galaxy, Coords coords) {
        return coords.row >= 0 && coords.row < galaxy.length && coords.col >= 0 && coords.col < galaxy[0].length;
    }
    private static void fillGalaxyMatrix(int [][] matrix) {
        int value = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = value++;
            }
        }
    }

    private static int [][] createGalaxyMatrix(Scanner scan) {
        int[] dimensions = Arrays.stream(scan.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int x = dimensions[0];
        int y = dimensions[1];

        return new int[x][y];
    }
}
