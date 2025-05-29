import java.util.Scanner;

public class Solver2DClosestPair {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println("""
                You are going to create a coordinate plane by specifying the number of points and the bounds.
                Points will be randomly generated within that bounds, and can only take integer values.
                """);

        System.out.print("What is the bounds of the coordinate plane? ");
        int bounds = scan.nextInt();
        System.out.print("How many random points should be generated? ");
        int numPoints = scan.nextInt();
        System.out.println();

        CoordinatePlane plane = null;

        try {
            plane = new CoordinatePlane(numPoints, bounds);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }

        System.out.println(plane);
        System.out.println(plane.findClosestPair(plane.getPointsSortedNonDecrByX(), plane.getPointsSortedNonDecrByY()));
    }
}
