public class ConvexPolygon {
    public boolean isConvex(List<List<Integer>> points) {
        int n = points.size();
        boolean negative = false;
        boolean positive = false;

        for(int B=0; B<points.size(); ++B) {
            int A = (B-1+n) % n;
            int C = (B+1+n) % n;

            Vector BA = new Vector(points.get(A).get(0) - points.get(B).get(0), points.get(A).get(1) - points.get(B).get(1));
            Vector BC = new Vector(points.get(C).get(0) - points.get(B).get(0), points.get(C).get(1) - points.get(B).get(1));
            int cp = BA.crossProduct(BC);
            if(cp < 0) negative = true;
            else if(cp > 0) positive = true;

            if(positive && negative) return false;
        }

        return true;
    }

    class Vector {
        public int x;
        public int y;

        public Vector(int xx, int yy) {
            x = xx;
            y = yy;
        }

        public int crossProduct(Vector v) {
            return x * v.y - v.x * y;
        }
    }    
}
