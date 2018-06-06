import java.util.*;

public class MyMinQueue {

    public static void main(String[] args) {
        MinQueue minQ = new MinQueue();
        MaxQueue maxQ = new MaxQueue();
        Scanner in = new Scanner(System.in);
        while(in.hasNext()) {
            String[] cmd = in.nextLine().split(" ");
            if(cmd.length == 1) {
                //pop() or getMin()
                if(cmd[0].equals("pop")) {
                    minQ.pop();
                    maxQ.pop();
                }
                else if(cmd[0].equals("peek")) {
                    System.out.println(minQ.peek());
                }
                else if(cmd[0].equals("min")) {
                    System.out.println(minQ.getMin());
                }
                else if(cmd[0].equals("max")){
                    System.out.println(maxQ.getMax());
                }
            } else {
                if(cmd[0].equals("offer")) {
                    minQ.offer(Integer.parseInt(cmd[1]));
                    maxQ.offer(Integer.parseInt(cmd[1]));
                }
            }
        }
    }

    static class MinQueue {
        MinStack in = null;
        MinStack out = null;

        public MinQueue() {
            in = new MinStack();
            out = new MinStack();
        }

        public void offer(int x) {
            in.push(x);
        }

        public void pop() {
            if(out.empty()) {
                while(!in.empty()) {
                    out.push(in.peek());
                    in.pop();
                }
            }
            out.pop();
        }

        public int peek() {
            if(out.empty()) {
                while(!in.empty()) {
                    out.push(in.peek());
                    in.pop();
                }
            }
            return out.peek();
        }

        public int getMin() {
            if(in.empty()) return out.getMin();
            else return in.getMin();
        }
    }

    static class MaxQueue {
        MaxStack in = null;
        MaxStack out = null;

        public MaxQueue() {
            in = new MaxStack();
            out = new MaxStack();
        }

        public void offer(int x) {
            in.push(x);
        }

        public void pop() {
            if(out.empty()) {
                while(!in.empty()) {
                    out.push(in.peek());
                    in.pop();
                }
            }
            out.pop();
        }

        public int peek() {
            if(out.empty()) {
                while(!in.empty()) {
                    out.push(in.peek());
                    in.pop();
                }
            }
            return out.peek();
        }

        public int getMax() {
            if(in.empty()) return out.getMax();
            else return in.getMax();
        }
    }

    static class MinStack {

        private Stack<Integer> stack = null;
        private int min;

        /** initialize your data structure here. */
        public MinStack() {
            stack = new Stack<> ();
        }

        public void push(int x) {
            if(stack.isEmpty()) {
                min = x;
                stack.push(0);
            } else {
                stack.push(x-min);
                min = Math.min(min, x);
            }
        }

        public void pop() {
            int dif = stack.pop();
            // dif = x - min_prev
            // if dif < 0, then min_cur = x;
            // dif = x - min_prev = min_cur - min_prev => min_cur - dif = min_prev;
            if(dif < 0) min = min - dif;

            // if dif >= 0, then min_cur = min_prev;

        }

        public int peek() {
            int dif = stack.peek();
            // dif = x - min_prev;
            // if x < min_prev, then min_cur = x;
            if(dif < 0) return min;
            // otherwise, x >= min_prev, then min_cur = min_prev;
            else return min + dif;
        }

        public int getMin() {
            return min;
        }

        public boolean empty() {
            return stack.empty();
        }
    }

    static class MaxStack {

        private Stack<Integer> stack = null;
        private int max;

        /** initialize your data structure here. */
        public MaxStack() {
            stack = new Stack<> ();
        }

        public void push(int x) {
            if(stack.isEmpty()) {
                max = x;
                stack.push(0);
            } else {
                stack.push(x-max);
                max = Math.max(max, x);
            }
        }

        public void pop() {
            int dif = stack.pop();

            // if x - max_prev > 0, max_current = x, max_current - dif = max_prev
            if(dif > 0) max = max - dif;

        }

        public int peek() {
            int dif = stack.peek();
            // dif = x - min_prev;
            // if x < min_prev, then min_cur = x;
            if(dif > 0) return max;
            // otherwise, x >= min_prev, then min_cur = min_prev;
            else return max + dif;
        }

        public int getMax() {
            return max;
        }

        public boolean empty() {
            return stack.empty();
        }
    }
}
