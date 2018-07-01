import java.util.*;

public class TaskScheduler {

    static void scheduleWithoutRelativeOrder(int[] tasks, int cooldown) {
        // count frequency of each task
        Map<Integer, Integer> counts = new HashMap<> ();
        Set<Integer> mostFrequentTasks = new HashSet<> ();
        int maxFreq = -1;
        for(int i=0; i<tasks.length; ++i) {
            counts.putIfAbsent(tasks[i], 0);
            counts.put(tasks[i], counts.get(tasks[i]) + 1);
            if(counts.get(tasks[i]) > maxFreq) {
                maxFreq = counts.get(tasks[i]);
                mostFrequentTasks = new HashSet<> ();
                mostFrequentTasks.add(tasks[i]);
            } else if(counts.get(tasks[i]) == maxFreq) {
                mostFrequentTasks.add(tasks[i]);
            }
        }

        //System.out.println(maxFreq + " ");
        int leastUnits = Math.max(tasks.length, (maxFreq - 1) * (cooldown + 1) + mostFrequentTasks.size());
        System.out.println(leastUnits + " units.");
    }

    static void scheduleWithRelativeOrder(int[] tasks, int cooldown) {
        int ts = 0;
        Map<Integer, Integer> task2Timestamp = new HashMap<> ();

        for(int i=0; i<tasks.length; ++i) {
            // first detection.
            if(!task2Timestamp.containsKey(tasks[i])) {
                task2Timestamp.put(tasks[i], ts);
                System.out.print(tasks[i]);
                ts += 1;
                continue;
            }

            int prevTimeStamp = task2Timestamp.get(tasks[i]);
            int currentTimeStamp = ts;
            // if current ts satisfy the cooldown restriction
            if(currentTimeStamp - prevTimeStamp - 1 < cooldown) {
                task2Timestamp.put(tasks[i], cooldown + prevTimeStamp + 1);
                int dif = cooldown - (currentTimeStamp - prevTimeStamp - 1);
                for(int k=0; k<dif; ++k) System.out.print("_");
                System.out.print(tasks[i]);
                ts = cooldown + prevTimeStamp + 2;
            } else { // if current ts doe not satisfy the cooldown restriction
                task2Timestamp.put(tasks[i], ts);
                System.out.print(tasks[i]);
                ts += 1;
            }
        }

        System.out.println();
        System.out.println(ts + " units.");
    }

    public static void main(String[] args) {

        int[] tasks = new int[] {1, 1, 1, 2, 2, 2};
        int cooldown = 2;
        scheduleWithoutRelativeOrder(tasks, cooldown);

        tasks = new int[] {1, 2, 1, 1, 3, 4};
        cooldown = 2;
        scheduleWithRelativeOrder(tasks, cooldown);

        tasks = new int[] {1,2,1,3,1,4};
        scheduleWithRelativeOrder(tasks, cooldown);

        tasks = new int[] {1, 2, 4 , 1, 3, 2, 1, 4};
        scheduleWithRelativeOrder(tasks, cooldown);
    }
}
