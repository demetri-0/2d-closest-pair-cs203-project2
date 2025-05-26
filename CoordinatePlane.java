import java.util.ArrayList;
import java.util.Random;

public class CoordinatePlane {

    final int numPoints;
    final int bounds;
    ArrayList<Point> points = new ArrayList<>();

    public CoordinatePlane(int numPoints, int bounds) {

        this.numPoints = numPoints;
        this.bounds = bounds;
        buildPlane();
    }

    private void buildPlane() {
        for (int i = 0; i < numPoints; i++) {
            points.add(getUniqueRandomPoint());
        }
    }

    private Point getUniqueRandomPoint() {
        Random rand = new Random();
        int x = rand.nextInt(bounds * -1, bounds + 1);
        int y = rand.nextInt(bounds * -1, bounds + 1);

        Point point = new Point(x, y);
        if (points.contains(point)) {
            return getUniqueRandomPoint();
        }
        return point;
    }

    public String toString() {

        String result = "";

        for (int i = 0; i < numPoints; i++) {
            result = result.concat(points.get(i).toString() + " ");
        }

        return result;
    }

    class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Point) {
                if (((Point) obj).x == this.x && ((Point) obj).y == this.y) {
                    return true;
                }
            }
            return false;
        }

        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }
}
