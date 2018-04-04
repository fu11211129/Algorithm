import java.util.*;

public class RectIntersection {

    public List<Rectangle> blocks = new ArrayList<> ();
    public Random rand = new Random();

    public static void main(String[] args) {
        RectIntersection ri = new RectIntersection();
        List<Rectangle> list = new ArrayList<> ();
        list.add(new Rectangle(0.0, 0.0, 10.0, 10.0));
        list.add(new Rectangle(5.0, -2.0, 12.0, 12.0));
        ri.getRandomPointsFromRectangles(list);
        //for(double v: randPoint) System.out.print(v + " ");
    }

    public void getRandomPointsFromRectangles(List<Rectangle> list) {
        int n = list.size();
        Line[] line = new Line[2*n + 1];
        double[] y = new double[2*n + 1];
        int index = 1;
        for(int i=0; i<n; ++i) {
            Rectangle rect = list.get(i);
            y[index] = rect.y1;
            line[index++] = new Line(rect.x1, rect.y1, rect.y2, (i+1), 1);
            y[index] = rect.y2;
            line[index++] = new Line(rect.x2, rect.y1, rect.y2, (i+1), -1);
        }

        Arrays.sort(y, 1, index);
        Arrays.sort(line, 1, index, new Comparator<Line> () {
            @Override
            public int compare(Line la, Line lb) {
                return new Double(la.x).compareTo(new Double(lb.x));
            }
        });

        Node root = build(1, index-1, y);
        for(int i=1; i<index; ++i) {
            Counter co = new Counter();
            insert(root, line[i].x, line[i].yd, line[i].yu, line[i].flag, co);
            // System.out.println(String.format("rect[%d] => %d\n", line[i].rectId, co.cc));
        }

        for(int i=0; i<blocks.size(); ++i) {
            Rectangle rect = blocks.get(i);
            double area = (rect.x2 - rect.x1) * (rect.y2 - rect.y1);
            System.out.println(rect.x1 + " " + rect.y1 + " " + rect.x2 + " " + rect.y2 + " => " + area);
        }
    }

    public Node build(int l, int r, double[] y) {
        if(l >= r) return null;

        Node root = new Node(y[l], y[r], false);
        if(l + 1 == r) {
            root.isLeaf = true;
            return root;
        }

        int mid = l + ((r - l) >> 1);
        root.left = build(l, mid, y);
        root.right = build(mid, r, y);
        return root;
    }

    // [yd: yu]
    public double insert(Node p, double x, double yd, double yu, int flag, Counter co) {
        // [yd: yu] - [p.yd : p.yu] or [p.yd : p.yu] - [yd:yu]
        if(p == null || p.yd >= yu || p.yu <= yd) return 0.0;

        if(p.isLeaf) {
            double area = 0.0;
            if(p.cover > 0) {
                double prev = p.x;
                area = (x - prev) * (p.yu - p.yd);
                // System.out.print(area + " ");
                blocks.add(new Rectangle(prev, p.yd, x, p.yu));
                co.cc += 1;
            }
            p.cover += flag;
            p.x = x;
            return area;
        }

        return insert(p.left, x, yd, yu, flag, co) + insert(p.right, x, yd, yu, flag, co);
    }

    class Line {
        double x, yu, yd; // yu: y_down, yd: y_up
        int flag; // flag = 1: coming edge, flag = -1: exit edge
        int rectId;

        public Line(double xx, double ydd, double yuu, int rid, int f) {
            x = xx;
            yd = ydd;
            yu = yuu;
            rectId = rid;
            flag = f;
        }
    }

    class Node {
        double x, yu, yd;
        int cover;
        int coverChange;
        boolean isLeaf;
        Node left;
        Node right;

        public Node(double ydd, double yuu, boolean isl) {
            x = -1;
            yu = yuu;
            yd = ydd;
            isLeaf = isl;
            left = right = null;
        }
    }

    static class Rectangle {
        double x1, y1, x2, y2;

        public Rectangle(double x11, double y11, double x22, double y22) {
            x1 = x11;
            x2 = x22;
            y1 = y11;
            y2 = y22;
        }
    }

    class Counter {
        int cc;
        public Counter() {
            cc = 0;
        }
    }
}
