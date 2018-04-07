// http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=345555&highlight=%BB%FA%C6%F7%C8%CB
public class RobotCleaner {

    private final Random rand = new Random();
    private final int[][] dirs = new int[][] {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
    private int[] pos;
    private int currDir;
    private Set<String> used;
    private Stack<int[]> stack;
    private Stack<Integer> actions;
    private Map<String, Set<Integer> > used = new HashMap<> ();
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
            String posStr = (pos[0] + dirs[currDir][0]) + "," + (pos[1] + dirs[currDir][1]);
            if(used.get(posStr) == null || !used.get(posStr).contains(currDir)) {
                pos[0] += dirs[currDir][0];
                pos[1] += dirs[currDir][1];
                return true;
            } else {
                makeTurns();
            }

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
        String pos = sx + "," + sy;
        cellDir.put(pos, new HashSet<> ());

        boolean stuck = false;

        while(!stuck) {
            clean(); // clean the current area

            int nx = pos[0] + dirs[currDir][0];
            int ny = pos[1] + dirs[currDir][1];
            String nxy = nx + "," + ny;
            stuck = true;

            if(used.get(nxy) == null || !used.get(nxy).contains(currDir)) { // this cell has not been visited.
                boolean canMove = move();
                if(canMove) {
                    pos[0] += dirs[currDir][0];
                    pos[1] += dirs[currDir][0];
                    String posStr = pos[0] + "," + pos[1];
                    if(used.get(posStr) == null) used.put(posStr, new HashSet<> ());
                    used.get(posStr).add(currDir);
                    stuck = false;

                } else { // cannot move forward
                    for(int k=1; k<=3; ++k) {
                        int ndir = (currDir + k) % 4;
                        nx = pos[0] + dirs[ndir][0];
                        ny = pos[1] + dirs[ndir][1];
                        nxy = nx + "," + ny;

                        if(used.get(nxy) == null || !used.get(nxy).contains(ndir)) {
                            boolean canMove = move();
                            if(canMove) {
                                currDir = ndir; // change direction.
                                pos[0] += dirs[currDir][0];
                                pos[1] += dirs[currDir][0];
                                String posStr = pos[0] + "," + pos[1];
                                if(used.get(posStr) == null) used.put(posStr, new HashSet<> ());
                                used.get(posStr).add(currDir);
                                stuck = false;
                                break;
                            }
                        }
                    }
                }

            }
        }
    }
}
