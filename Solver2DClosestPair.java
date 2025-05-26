import java.util.Scanner;

public class Solver2DClosestPair {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println("How many random points should be generated?");
        int numPoints = scan.nextInt();

        System.out.println("What is the bounds of the coordinate plane?");
        int bounds = scan.nextInt();

        CoordinatePlane plane = new CoordinatePlane(numPoints, bounds);

        System.out.println(plane);
    }
}
