// leetcode 359

public class LoggerRateLimiter {
    private int[] buckets = null;
    // map hashing index to a set of messages which has the same timestamp
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
        // if 2 timestamps share the same hashing indices,
        // then the difference between 2 timestamps must >= 10

        int index = timestamp % 10;
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
