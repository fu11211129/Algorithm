public class FindKClosestPoints2dPlane {
    // Time: O(n) average, O(n + klogk) time if output is sorted;  O(n^2) worst case
    // Space: O(1)

    static Point[] findKClosestPoints(Point[] points, int k, Point target) {
        if (points.length == 0 || k < 1 || k > points.length)   return points;
        int left = 0, right = points.length - 1;
        while (left < right) {
            int pidx = partition(points, left, right, target);
            if (pidx - left + 1 == k)  break;
            else if (pidx - left + 1 > k)  {
                right = pidx - 1;
            }
            else {
                left = pidx + 1;
                k -= (pidx - left + 1);
            }
        }
        Point[] res = new Point[k];
        for (int i = 0; i < k; i++)
            res[i] = points[i];
        return res;
    }

    static int partition(Point[] points, int left, int right, Point target) {
        //shuffle(points);
        int idx = right; // important
        int wall = left;
        Point pivot = points[idx];
        int pDist = getDistance(pivot, target);
        swap(points, idx, right);
        for (int i = left; i < right; i++) {
            int iDist = getDistance(points[i], target);
            if (iDist < pDist) {
                swap(points, i, wall);
                wall += 1;
            }
        }
        swap(points, wall, right);
        return idx;
    }

    static int getDistance(Point p, Point target) {
        return (p.x - target.x) * (p.x - target.x) + (p.y - target.y) * (p.y - target.y);
    }

    static void swap(Point[] points, int left, int right) {
         Point temp = points[left];
         points[left] = points[right];
         points[right] = temp;
    }

    public static void main(String[] args) {
        FindKClosestPoints2dPlane sol = new FindKClosestPoints2dPlane();
        Point p1 = new Point(1, 2);
        Point p2 = new Point(0, 1);
        Point p3 = new Point(1, 0);
        Point p4 = new Point(1, 1);
        Point p5 = new Point(0, 0);
        Point target = new Point(2, 3);
        int k = 3;

        Point[] points = new Point[5];
        points[0] = p1;
        points[1] = p2;
        points[2] = p3;
        points[3] = p4;
        points[4] = p5;

        Point[] closest = findKClosestPoints(points, k, target);
        for(Point p: closest) {
            System.out.println(p.x + " " + p.y);
        }
    }

    static class Point {
        int x;
        int y;

        public Point(int xx, int yy) {
            x = xx;
            y = yy;
        }
    }
}
