public class RobotCleaner {
    public int[][] dirs = new int[][] {{-1, 0}, {0, -1}, {1, 0}, {0, 1}}; // anti-clockwise.

    public void cleanRoom(Robot robot) {
        int x = 0;
        int y = 0;
        int d = 0; // face up
        Set<String> seen = new HashSet<> (); // mark those visited place & obstacle.

        dfs(robot, x, y, d, seen);
    }

    public void dfs(Robot robot, int x, int y, int d, Set<String> seen) {
        robot.clean();

        for(int i = 0; i < 4; i += 1) {
            int nd = (d + i) % 4; // roate 0, 90, 180, 270 degrees
            int nx = x + dirs[nd][0];
            int ny = y + dirs[nd][1];
            String loc = nx + "," + ny;

            if(seen.contains(loc) == false) { // have never enter this cell
                // mark this cell no matter it is obstacle or empty space
                seen.add(loc);

                if(robot.move()) { // in case it is a empty space.
                    dfs(robot, nx, ny, nd, seen);
                    backoff(robot);
                }
            }

            // in case robot cannot move to next cell
            // or robot just finishes its cleaning on pervious direction.
            // try a different direction
            robot.turnLeft();
        }
    }

    public void backoff(Robot robot) {
        robot.turnLeft();
        robot.turnLeft();
        robot.move(); // move to previous cell
        robot.turnLeft();
        robot.turnLeft(); // set the direction to previous direction.
    }    
}
