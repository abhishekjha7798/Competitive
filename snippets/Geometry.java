// Geometry Algorithms - Closest Pair, Convex Hull, Polygon Area, etc.
import java.util.*;

public class Geometry {
    static class Point implements Comparable<Point> {
        double x, y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point other) {
            if (this.x != other.x) return Double.compare(this.x, other.x);
            return Double.compare(this.y, other.y);
        }
    }

    // ========== Closest Pair of Points ==========
    static double closestPair(Point[] points) {
        Arrays.sort(points);
        return closestPairHelper(points, 0, points.length - 1);
    }

    static double closestPairHelper(Point[] points, int left, int right) {
        if (right - left <= 2) {
            double minDist = Double.MAX_VALUE;
            for (int i = left; i <= right; i++) {
                for (int j = i + 1; j <= right; j++) {
                    minDist = Math.min(minDist, distance(points[i], points[j]));
                }
            }
            return minDist;
        }

        int mid = (left + right) / 2;
        double midX = points[mid].x;
        double minDist = Math.min(closestPairHelper(points, left, mid),
                                  closestPairHelper(points, mid + 1, right));

        List<Point> strip = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (Math.abs(points[i].x - midX) < minDist) {
                strip.add(points[i]);
            }
        }

        Collections.sort(strip, (p1, p2) -> Double.compare(p1.y, p2.y));

        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size() &&
                 (strip.get(j).y - strip.get(i).y) < minDist; j++) {
                minDist = Math.min(minDist, distance(strip.get(i), strip.get(j)));
            }
        }
        return minDist;
    }

    static double distance(Point p1, Point p2) {
        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) +
                        (p1.y - p2.y) * (p1.y - p2.y));
    }

    // ========== Convex Hull - Graham Scan ==========
    static List<Point> convexHull(Point[] points) {
        int n = points.length;
        if (n <= 3) {
            return Arrays.asList(points);
        }

        Arrays.sort(points);

        List<Point> lower = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            while (lower.size() >= 2 &&
                   ccw(lower.get(lower.size() - 2), lower.get(lower.size() - 1), points[i]) <= 0) {
                lower.remove(lower.size() - 1);
            }
            lower.add(points[i]);
        }

        List<Point> upper = new ArrayList<>();
        for (int i = n - 1; i >= 0; i--) {
            while (upper.size() >= 2 &&
                   ccw(upper.get(upper.size() - 2), upper.get(upper.size() - 1), points[i]) <= 0) {
                upper.remove(upper.size() - 1);
            }
            upper.add(points[i]);
        }

        lower.addAll(upper);
        return lower;
    }

    // Counter-clockwise test (cross product)
    static long ccw(Point p1, Point p2, Point p3) {
        return (long) ((p2.x - p1.x) * (p3.y - p1.y) - (p2.y - p1.y) * (p3.x - p1.x));
    }

    // ========== Polygon Area - Shoelace Formula ==========
    static double polygonArea(Point[] polygon) {
        double area = 0;
        int n = polygon.length;

        for (int i = 0; i < n; i++) {
            int j = (i + 1) % n;
            area += polygon[i].x * polygon[j].y;
            area -= polygon[j].x * polygon[i].y;
        }
        return Math.abs(area) / 2.0;
    }

    // ========== Point in Polygon - Ray Casting ==========
    static boolean pointInPolygon(Point point, Point[] polygon) {
        int n = polygon.length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            Point p1 = polygon[i];
            Point p2 = polygon[(i + 1) % n];

            if ((p1.y <= point.y && point.y < p2.y) ||
                (p2.y <= point.y && point.y < p1.y)) {
                double xinters = (point.y - p1.y) / (p2.y - p1.y) * (p2.x - p1.x) + p1.x;
                if (point.x < xinters) {
                    count++;
                }
            }
        }
        return (count % 2) == 1;
    }

    // ========== Line Segment Intersection ==========
    static boolean segmentsIntersect(Point p1, Point p2, Point p3, Point p4) {
        long d1 = ccw(p3, p4, p1);
        long d2 = ccw(p3, p4, p2);
        long d3 = ccw(p1, p2, p3);
        long d4 = ccw(p1, p2, p4);

        if (((d1 > 0 && d2 < 0) || (d1 < 0 && d2 > 0)) &&
            ((d3 > 0 && d4 < 0) || (d3 < 0 && d4 > 0))) {
            return true;
        }
        return false;
    }

    // ========== Distance Point to Line ==========
    static double pointToLineDistance(Point point, Point lineP1, Point lineP2) {
        double numerator = Math.abs((lineP2.y - lineP1.y) * point.x -
                                   (lineP2.x - lineP1.x) * point.y +
                                   lineP2.x * lineP1.y - lineP2.y * lineP1.x);
        double denominator = distance(lineP1, lineP2);
        return numerator / denominator;
    }

    // ========== Circle Area and Circumference ==========
    static double circleArea(double radius) {
        return Math.PI * radius * radius;
    }

    static double circleCircumference(double radius) {
        return 2 * Math.PI * radius;
    }

    // ========== Circle Intersection ==========
    static int circleIntersection(double x1, double y1, double r1,
                                  double x2, double y2, double r2) {
        double d = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));

        if (d > r1 + r2) return 0;        // No intersection
        if (d < Math.abs(r1 - r2)) return 0; // One inside other
        if (d == 0 && r1 == r2) return -1;   // Same circle
        return 2;                            // Two intersection points
    }
}

