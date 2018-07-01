import java.util.*;

public class MergeTwoSortedIntervalLists {

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
