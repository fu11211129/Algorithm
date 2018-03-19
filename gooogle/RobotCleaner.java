// at the very begining, robot stands at anywhere of infinite map
// the default direction is "to-the-top"

public class RobotCleaner implements Robot {

    public Stack<String> actions = new Stack<> ();
    public Stack<String> plans = new Stack<> ();
    public int[] curPosition = new int[2]; // at the beginning, it's [0, 0]
    public Set<String> visted = new HashSet<> ();


    public boolean moveForward() {
        boolean canMoveForward = move();
        if(canMoveForward) curPosition[1] += 1; //()
    }

    public boolean moveLeft() {

        turnLeft(1); // x -= 1
        boolean canMoveForward = move();
        if(canMoveForward) curPosition[0] -= 1;

        // turnRight(1);
        return canMoveForward;
    }

    public boolean moveRight() {
        turnRight(1); // x += 1
        boolean canMoveForward = move();
        if(canMoveForward) curPosition[0] += 1;

        // turnLeft(1);
        return canMoveForward;
    }

    public boolean actuator(String direction) {
        if(direction.equals("forward")) {
            actions.push(direction);
            return moveForward();
        } else if(direction.equals("left")) {
            actions.push(direction);
            return moveLeft();
        } else {
            actions.push(direction);
            return moveRight();
        }
    }

    public boolean rollback() {
        String lastAction = stack.pop();
        // (0, 0) --left-> (-1, 0) -(rollback)-> (0, 0)
        if(lastAction.equals("left")) return moveRight();
        // (0, 0) --right-> (1, 0) -(rollback)-> (0, 0)
        else if(lastAction.equals("right")) return moveLeft();
        else if(lastAction.equals("forward")) return moveBackForward();
    }

    public void dfs(int)

    public boolean solution() {
        String posStr = curPosition[0] + "," + curPosition[1];
        visited.add(posStr);
        clean();

        while(true) {

            String plan = "";
            int numberOfUnVisited = 0;

            for(int i=0; i<3; ++i) {
                if(i == 0) plan = "forward";
                else if(i == 1) plan = "turnLeft";
                else plan = "turnRight";

                if(actuator(plan)) {
                    posStr = curPosition[0] + "," + curPosition[1];
                    if(!visited.contains(posStr)) {
                        numberOfUnVisited += 1;
                        clean();
                        visited.add(posStr); // mark this place as visited
                    }
                } else {
                    rollback();
                }
            }

            if(numberOfUnVisited == 0) break;
        }
    }
}
