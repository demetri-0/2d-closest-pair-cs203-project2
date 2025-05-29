import java.util.*;

public class CoordinatePlane {

    final int numPoints;
    final int bounds;
    private final List<Point> pointsSortedNonDecrByX = new ArrayList<>();
    private final List<Point> pointsSortedNonDecrByY = new ArrayList<>();

    public CoordinatePlane(int numPoints, int bounds) throws Exception {

        if (numPoints > Math.pow((bounds * 2) + 1, 2)) {
            throw new Exception(String.format("%d points cannot fit in a plane with a bounds of %d.", numPoints, bounds));
        }

        this.numPoints = numPoints;
        this.bounds = bounds;
        buildPlane();
    }

    private void buildPlane() {

        Point point;

        for (int i = 0; i < numPoints; i++) {
            point = getUniqueRandomPoint();
            pointsSortedNonDecrByX.add(point);
            pointsSortedNonDecrByY.add(point);
        }

        pointsSortedNonDecrByX.sort((p1, p2) -> Integer.compare(p1.x, p2.x));
        pointsSortedNonDecrByY.sort((p1, p2) -> Integer.compare(p1.y, p2.y));
    }

    private Point getUniqueRandomPoint() {
        Random rand = new Random();
        int x = rand.nextInt(bounds * -1, bounds + 1);
        int y = rand.nextInt(bounds * -1, bounds + 1);

        Point point = new Point(x, y);
        if (pointsSortedNonDecrByX.contains(point)) {
            return getUniqueRandomPoint();
        }
        return point;
    }

    double findClosestPair(List<Point> pointsSortedNonDecrByX, List<Point> pointsSortedNonDecrByY) {

        Point[] closestPair = new Point[2];
        List<Point> p, pLeft, pRight;
        List<Point> q, qLeft, qRight;
        List<Point> s;
        double minDistance, minDistanceSquared = 0, minDistanceLeft, minDistanceRight;
        int midpointX;

        p = pointsSortedNonDecrByX;

        if (p.size() <= 3) {
            return findClosestPairByBruteForce(p);
        }
        else {
            q = pointsSortedNonDecrByY;
            pLeft = p.subList(0, p.size() / 2);
            qLeft = q.stream().filter(point -> pLeft.contains(point)).toList();
            pRight = p.subList(p.size() / 2, p.size());
            qRight = q.stream().filter(point -> pRight.contains(point)).toList();

//            System.out.println(Arrays.toString(p.toArray()));
//            System.out.println(Arrays.toString(q.toArray()));
//            System.out.println();
//
//            System.out.println(Arrays.toString(pLeft.toArray()));
//            System.out.println(Arrays.toString(qLeft.toArray()));
//            System.out.println();
//
//            System.out.println(Arrays.toString(pRight.toArray()));
//            System.out.println(Arrays.toString(qRight.toArray()));
//            System.out.println();

            minDistanceLeft = findClosestPair(pLeft, qLeft);
            minDistanceRight = findClosestPair(pRight, qRight);
            minDistance = Math.min(minDistanceLeft, minDistanceRight);

            midpointX = p.get(p.size() / 2 - 1).x;

            s = q.stream().filter(point -> Math.abs(point.x - midpointX) < minDistance).toList();

            minDistanceSquared = minDistance * minDistance;
            for (int i = 0; i < s.size() - 2; i++) {
                int k = i +1;
                while (k <= s.size() - 1 && (Math.pow(s.get(k).y - s.get(i).y, 2) < minDistanceSquared)) {
                    minDistanceSquared = Math.min(Math.pow(s.get(k).x - s.get(i).x, 2) + Math.pow(s.get(k).y - s.get(i).y, 2), minDistanceSquared);
                    k++;
                }
            }
        }

        return Math.sqrt(minDistanceSquared);
    }

    private double findClosestPairByBruteForce(List<Point> pointsSortedNonDecrByX) {

        List<Point> p = pointsSortedNonDecrByX;

        if (p.size() == 3) {
            double d1 = getDistance(p.get(0), p.get(1));
            double d2 = getDistance(p.get(1), p.get(2));
            double d3 = getDistance(p.get(0), p.get(2));

            List<Double> distances = new ArrayList<>();
            distances.add(d1); distances.add(d2); distances.add(d3);

            return Collections.min(distances);
        }
        else if (p.size() == 2) {
            return getDistance(p.get(0), p.get(1));
        }
        else {
            return 0;
        }
    }

    private double getDistance(Point p1, Point p2) {

        int d1 = p2.x - p1.x;
        int d2 = p2.y - p1.y;

        return Math.sqrt(Math.pow(d1, 2) + Math.pow(d2, 2));
    }

    public List<Point> getPointsSortedNonDecrByX() {
        return pointsSortedNonDecrByX;
    }

    public List<Point> getPointsSortedNonDecrByY() {
        return pointsSortedNonDecrByY;
    }

    public String toString() {

        String result = "";

        for (int i = 0; i < numPoints; i++) {
            result = result.concat(pointsSortedNonDecrByX.get(i).toString() + " ");
        }

        return result;
    }

    public class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Point) {
                return ((Point) obj).x == this.x && ((Point) obj).y == this.y;
            }
            return false;
        }

        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }
}
