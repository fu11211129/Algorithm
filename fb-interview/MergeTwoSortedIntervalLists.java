import java.util.*;

public class MergeTwoSortedIntervalLists {

    // @Follow Up:
    // Intersection of two sorted interval lists, A=[(1,2), (5,7)..] B=[(2,6)...]  return [(5,6)..]
    public List<Interval> getIntersection(List<Interval> list1, List<Interval> list2) {
        if(list2.size() == 0 || list2.size() == 0) return new ArrayList<>;

        LinkedList<Interval> re = new LinkedList<> ();
        List<Interval> intersections = new ArrayList<> ();
        int i = 0, j = 0;
        int m = list1.size(), n = list2.size();

        while(i < m && j < n) {
            Interval first = list1.get(i);
            Interval second = list2.get(j);

            // if first interval is closer to previous one
            // add first interval to result
            if(first.start <= second.start) {
                if(re.size() == 0) re.add(first);
                else if(re.peekLast().end >= first.start) {
                    // take "min" of two ends as intersectional end
                    intersections.add(new Interval(first.start, Math.min(first.end, re.peekLast.end())));
                    re.peekLast().end = Math.max(re.peekLast().end, first.end);
                }
                else re.addLast(first);
                i += 1;
            } else {
                if(re.size() == 0) re.add(second);
                else if(re.peekLast().end >= second.start) {
                    intersections.add(new Interval(second.start, Math.min(second.end, re.peekLast.end())));
                    re.peekLast().end = Math.max(re.peekLast().end, second.end);
                }
                else re.addLast(second);
                j += 1;
            }
        }

        return intersections;
    }

    public List<Interval> mergeTwoInterval(List<Interval> list1, List<Interval> list2) {
        if(list2.size() == 0) return list1;
        else if(list1.size() == 0) return list2;

        LinkedList<Interval> re = new LinkedList<> ();
        int i = 0, j = 0;
        int m = list1.size(), n = list2.size();

        while(i < m && j < n) {
            Interval first = list1.get(i);
            Interval second = list2.get(j);

            // if first interval is closer to previous one
            // add first interval to result
            if(first.start <= second.start) {
                if(re.size() == 0) re.add(first);
                else if(re.peekLast().end >= first.start) re.peekLast().end = Math.max(re.peekLast().end, first.end);
                else re.addLast(first);
                i += 1;
            } else {
                if(re.size() == 0) re.add(second);
                else if(re.peekLast().end >= second.start) re.peekLast().end = Math.max(re.peekLast().end, second.end);
                else re.addLast(second);
                j += 1;
            }
        }

        while(i < m) {
            Interval first = list1.get(i);
            if(re.size() == 0) re.add(first);
            else if(re.peekLast().end >= first.start) re.peekLast().end = Math.max(re.peekLast().end, first.end);
            else re.addLast(first);
            i += 1;
        }

        while(j < n) {
            Interval second = list2.get(j);
            if(re.size() == 0) re.add(second);
            else if(re.peekLast().end >= second.start) re.peekLast().end = Math.max(re.peekLast().end, second.end);
            else re.addLast(second);
            j += 1;
        }

        return re;
    }

    class Interval {
        int start;
        int end;

        public Interval(int s, int e) {
            start = s;
            end = e;
        }
    }
}
