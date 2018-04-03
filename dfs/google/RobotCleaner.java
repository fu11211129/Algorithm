// http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=345555&highlight=%BB%FA%C6%F7%C8%CB
public class RobotCleaner {

    private final Random rand = new Random();
    private final int[][] dirs = new int[][] {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
    private int[] pos;
    private int currDir;
    private Set<String> used;
    private Stack<int[]> stack;
    private Stack<Integer> actions;
    // void turnLeft(k)
    // void turnRight(k)
    // void clean()
    // boolean move(), move forward if possible, and return true; return false if not possible.

    // assume the robot what is left, right, up & down.
    public RobotCleaner() {
        currDir = rand.nextInt(dirs.length);
        pos = new int[] {0, 0};
        used = new HashSet<> ();
        stack = new Stack<> ();
        stack.push(new int[] {0, 0, 0});
        actions = new Stack<> ();
    }

    public boolean nextMove() {
        if(move()) {
            pos[0] += dirs[currDir][0];
            pos[1] += dirs[currDir][1];
            return true;
        } else {
            trunLeft(1);
            currDir = (currDir + 1) % 4;
            if(move()) {
                // turn 90 degree based on current direction.
                pos[0] += dirs[currDir][0];
                pos[1] += dirs[currDir][1];
                return true;
            } else {
                trunLeft(1); // turn 180 degree
                currDir = (currDir + 1) % 4;
                if(move()) {
                    pos[0] += dirs[currDir][0];
                    pos[1] += dirs[currDir][1];
                    return true;
                } else {
                    turnLeft(1); // turn 270 degree
                    currDir = (currDir + 1) % 4;
                    if(move()) {
                        pos[0] += dirs[currDir][0];
                        pos[1] += dirs[currDir][1];
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void cleanRoom() {
        int sx = 0, sy = 0;
        
    }
}
