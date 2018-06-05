public class MeetingRoomII {
    public int minMeetingRooms(Interval[] intervals) {
        if(intervals.length <= 1) return intervals.length;

        Arrays.sort(intervals, new Comparator<Interval> () {
            @Override
            public int compare(Interval i1, Interval i2) {
                // if(i1.start != i2.start) return i1.end - i2.end;
                return i1.start - i2.start;
            }
        });

        PriorityQueue<Interval> pq = new PriorityQueue<> (new Comparator<Interval> () {
            @Override
            public int compare(Interval i1, Interval i2) {
                return i1.end - i2.end;
            }
        });

        pq.offer(intervals[0]);
        int room = 1;

        for(int i=1; i<intervals.length; ++i) {
            Interval prev = pq.poll();
            Interval curr = intervals[i];

            // collision detected, need one more room
            if(curr.start < prev.end) {
                room += 1;
                pq.offer(curr);
                pq.offer(prev);
            } else {
                prev.end = curr.end;
                pq.offer(prev);
            }
        }

        return pq.size();
    }  
}
