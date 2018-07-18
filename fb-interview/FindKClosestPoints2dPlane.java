public class FindKClosestPoints2dPlane {
    // Time: O(n) average, O(n + klogk) time if output is sorted;  O(n^2) worst case
    // Space: O(1)

    public Point[] findKClosestPoints(Point[] points, int k, Point target) {
        if (points.length == 0 || k < 1 || k > points.length)   return points;
        int left = 0, right = points.length - 1;
        while (true) {
            int pos = partition(points, left, right, target);
            if (pos == k - 1)   break;
            else if (pos > k - 1)   right = pos - 1;
            else    left = pos + 1;
        }
        Point[] res = new Point[k];
        for (int i = 0; i < k; i++)
            res[i] = points[i];
        return res;
    }

    private int partition(Point[] points, int left, int right, Point target) {
        //shuffle(points);
        int idx = right; // important
        Point pivot = points[idx];
        int pDist = getDistance(pivot, target);
        swap(points, idx, right);
        for (int i = left; i < right; i++) {
            int iDist = getDistance(points[i], target);
            if (iDist < pDist)  swap(points, i, idx++);
        }
        swap(points, idx, right);
        return idx;
    }

    private int getDistance(Point p, Point target) {
        return (p.x - target.x) * (p.x - target.x) + (p.y - target.y) * (p.y - target.y);
    }

    private static void swap(Point[] points, int left, int right) {
         Point temp = points[left];
         points[left] = points[right];
         points[right] = temp;
    }
}
