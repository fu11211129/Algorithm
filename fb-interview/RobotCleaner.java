public class RobotCleaner {
    public int cnt = 0;
    public int[][] dirs = new int[][] {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public void cleanRoom(Robot robot) {
        Map<String, Set<Integer> > seen = new HashMap<> ();
        int d = 0;
        dfs(robot, 0, 0, d, seen);
        //System.out.println(cnt);
    }

    public void dfs(Robot robot, int x, int y, int d, Map<String, Set<Integer> > seen) {
        robot.clean();
        //cnt += 1;
        // seen.putIfAbsent(x + "," + y, new HashSet<> ());
        // seen.get(x + "," + y).add(d);

        for(int i = 0; i < 4; i += 1) { //try four directions.
            int newlyDir = (d + i) % 4;
            int newlyX = x + dirs[newlyDir][0];
            int newlyY = y + dirs[newlyDir][1];
            String loc = newlyX + "," + newlyY;

            if(!seen.containsKey(loc) || !seen.get(loc).contains(newlyDir)) {
                boolean b = robot.move();
                if(b) {
                    // if can move forward, go to the next cell.
                    seen.putIfAbsent(loc, new HashSet<> ());
                    seen.get(loc).add(newlyDir);
                    dfs(robot, newlyX, newlyY, newlyDir, seen);

                    // in case the robot get stuck get somewhere
                    // we let it go the last previous position.
                    backoff(robot);
                }
                else {
                    // means the next cell is a wall.
                    seen.putIfAbsent(loc, new HashSet<> ());
                    seen.get(loc).addAll(Arrays.asList(0, 1, 2, 3));
                }
            }

            // if the way entering the next cell has been marked.
            // we should try another direction.
            robot.turnLeft();
        }
    }

    public void backoff(Robot robot) {
        robot.turnLeft();
        robot.turnLeft();
        robot.move();
        robot.turnLeft();
        robot.turnLeft();
    }    
}
