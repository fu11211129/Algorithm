public class LoggerRateLimiter {
    private int[] buckets = null;
    private Set[] sets = null;

    /** Initialize your data structure here. */
    public Logger() {
        buckets = new int[10];
        sets = new HashSet[10];
        for(int i=0; i<10; ++i) {
            sets[i] = new HashSet<> ();
        }
    }

    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        int index = timestamp % 10;
        // if this index has been visited before, update the associated timestamp
        //
        if(timestamp != buckets[index]) {
            buckets[index] = timestamp;
            sets[index].clear();
        }

        for(int i=0; i<10; ++i) {
            int prev = buckets[i];
            if(timestamp - prev < 10) {
                // if the message has been printed within 10 seconds
                // reject this message.
                if(sets[i].contains(message)) return false;
            }
        }
        sets[index].add(message);
        return true;
    }    
}
