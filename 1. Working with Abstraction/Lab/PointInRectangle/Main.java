package PointInRectangle;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int [] points = Arrays.stream(scan.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Point bottomLeft = new Point(points[0], points[1]);
        Point topRight = new Point(points[2], points[3]);
        Rectangle rectangle = new Rectangle(bottomLeft, topRight);

        int pointsCount = Integer.parseInt(scan.nextLine());
        for (int i = 0; i < pointsCount; i++) {
            Point current = PointUtil.parsePoint(scan.nextLine());

            System.out.println(rectangle.contains(current));
        }
    }
}
