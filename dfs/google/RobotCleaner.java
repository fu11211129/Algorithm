// http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=345555&highlight=%BB%FA%C6%F7%C8%CB
public class RobotCleaner {
    // moving UP is add (moveX[0], moveY[0]) to current position
    // moving LEFT is add (moveX[1], moveY[1]) to current positoin
    //              DOEN          (moveX[2], moveY[2])
    //              RIGHT         (moveX[3], moveY[3])

    public void cleanRoom(Robot robot) {
            Map<Integer, Set<Integer>> map = new HashMap<>();
            cleanNext(robot, map, 0, 0, 0);
        }

    private int[] getNextPos(int direction, int x, int y) {
            int[] moveX = {-1, 0, 1, 0};
            int[] moveY = {0, -1, 0, 1};
            return new int[] {x + moveX[direction], y + moveY[direction]};
        }

    		// after visit a position, keep a journal in Map
        private void markPos(Map<Integer, Set<Integer>> map, int x, int y) {
            if (map.containsKey(x)) {
                Set<Integer> ys = map.get(x);
                ys.add(y);
            } else {
                Set<Integer> ys = new HashSet<>();
                ys.add(y);
                map.put(x, ys);
            }
        }
    		// direction has four value, 0, 1, 2, 3, which means UP, LEFT, DOWN, RIGHT
        private void cleanNext(Robot robot, Map<Integer, Set<Integer>> map, int x, int y, int direction) {
            robot.clean();
            markPos(map, x, y);
            for (int i = 0; i < 4; i ++) {
                int newDir = (direction + i) % 4;
                int[] directions = getNextPos(newDir, x, y);
                int nextX = directions[0];
                int nextY = directions[1];
                if ((!map.containsKey(nextX) || (map.containsKey(nextX) && !map.get(nextX).contains(nextY)))) {
                    if (robot.move()) {
                        cleanNext(robot, map, nextX, nextY, newDir);
                        toPrevious(robot);
                    } else {
                        markPos(map, nextX, nextY);
                    }
                }
                robot.turnLeft();
            }
        }

    		// return to previous position
        private void toPrevious(Robot robot) {
            robot.turnLeft();
            robot.turnLeft();
            robot.move();
            robot.turnLeft();
            robot.turnLeft();
        }
}
